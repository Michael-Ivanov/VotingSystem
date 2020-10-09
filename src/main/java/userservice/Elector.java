package userservice;

import org.graalvm.compiler.lir.LIRInstruction;

public class Elector extends User {

    private boolean voted;

    public Elector(String name, String login, String password) {
        super(name, login, password);
    }

    public boolean isVoted() {
        return voted;
    }

    public void vote() {

    }
}
