package ohtu;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.Map.Entry;

import org.apache.http.client.fluent.Request;

public class Main {

	public static void main(String[] args) throws IOException {
		// vaihda oma opiskelijanumerosi seuraavaan, ƒLƒ kuitenkaan laita githubiin omaa
		// opiskelijanumeroasi
		String studentNr = "011120775";
		if (args.length > 0) {
			studentNr = args[0];
		}

		String url = "https://studies.cs.helsinki.fi/ohtustats/students/" + studentNr + "/submissions";
		String kurssiUrl = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
		String statsUrl = "https://studies.cs.helsinki.fi/ohtustats/stats";

		String bodyText = Request.Get(url).execute().returnContent().asString();
		String kurssiText = Request.Get(kurssiUrl).execute().returnContent().asString();
		String statsText = Request.Get(statsUrl).execute().returnContent().asString();

		JsonParser parser = new JsonParser();
		JsonObject parsittuData = parser.parse(statsText).getAsJsonObject();

		Gson mapper = new Gson();
		Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
		Course course = mapper.fromJson(kurssiText, Course.class);

		System.out.println("Kurssi: " + course);

		int tehtavia = 0;
		int tunteja = 0;
		System.out.println(studentNr);

		for (Submission submission : subs) {
			// System.out.println(submission);

			System.out.print("Viikko " + submission.getWeek() + ": Tehtyj‰ teht‰vi‰: "
					+ submission.getExercises().length + " (maksimi " + course.getExercises()[course.getWeek() - 1]
					+ ") " + ", aikaa meni " + submission.getHours() + " tuntia, tehdyt teht‰v‰t: ");
			for (int exercise : submission.getExercises()) {
				System.out.print(exercise + " ");
				tehtavia++;
			}
			System.out.print("\n");
			tunteja += submission.getHours();
		}
		System.out.println("Yhteens‰: " + tehtavia + " teht‰v‰‰, joihin meni " + tunteja + " tuntia.");

		int palautuksia = 0;
		int tehtaviaYht = 0;

		for (Entry<String, JsonElement> elementti : parsittuData.entrySet()) {
			JsonElement oikeaElementti = elementti.getValue();
			JsonObject jobject = oikeaElementti.getAsJsonObject();
			palautuksia += jobject.get("students").getAsInt();
			tehtaviaYht += jobject.get("exercise_total").getAsInt();
		}

		System.out.println("Kurssilla yhteens‰ " + palautuksia + " palautusta, palautettuja teht‰vi‰ " + tehtaviaYht);
	}

}