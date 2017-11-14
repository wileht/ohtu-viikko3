package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ƒLƒ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "011120775";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        
        System.out.println("Oliot:");
        
        int tehtavia = 0;
        int tunteja = 0;
        System.out.println(studentNr);
        
        for (Submission submission : subs) {
            //System.out.println(submission);
        	
        	System.out.print("Viikko " + submission.getWeek() + ": Tehtyj‰ teht‰vi‰: "
        			+ submission.getExercises().length + ", aikaa meni " + submission.getHours()
        			 + " tuntia, tehdyt teht‰v‰t: ");
        	for (int exercise : submission.getExercises()) {
        		System.out.print(exercise + " ");
        		tehtavia++;
        	}
        	System.out.print("\n");
        	tunteja += submission.getHours();
        }
        
        System.out.println("Yhteens‰: " + tehtavia + " teht‰v‰‰, joihin meni " + tunteja + " tuntia.");
    }
}