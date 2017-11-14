package ohtu;

public class Submission {
    private int week;
    private int hours;
    private int[] exercises;

    public Submission() {
    }
    
    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    @Override
    public String toString() {
        return ""+week;
    }

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int[] getExercises() {
		return exercises;
	}

	public void setExercises(int[] exercises) {
		this.exercises = exercises;
	}
    
}