package votingsystem;

public class Candidate {

    private String name;
    private int voices = 0;

    public Candidate(String name) {
        this.name = name;
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
}
