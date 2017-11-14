package ohtu;

public class Course {
    private String name;
    private String term;
    private String url;
    private int week;
    private int[] exercises;

    public Course() {
    }
   

    @Override
    public String toString() {
        return name;
    }

	public int[] getExercises() {
		return exercises;
	}

	public void setExercises(int[] exercises) {
		this.exercises = exercises;
	}


	public String getTerm() {
		return term;
	}


	public void setTerm(String term) {
		this.term = term;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getWeek() {
		return week;
	}


	public void setWeek(int week) {
		this.week = week;
	}
    
}