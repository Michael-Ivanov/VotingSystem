package votingsystem;

public class Candidate {

    private String name;
    private int voices = 0;

    public Candidate(String name) {
        this.name = name;
    }

    public Candidate(String name, int voices) {
        this.name = name;
        this.voices = voices;
    }

    public String getName() {
        return name;
    }

    public int getVoices() {
        return voices;
    }

    public void addVoice() {
        voices++;
    }

    @Override
    public String toString() {
        return  name + ": " + getVoices() + " voices.";
    }
}
