package driver;

/**
 * Represents a command provided by the user.
 */
public class UserInput {
    public enum InputType {
        SETUP,                   // set up battlefield
        QUIT,                    // quit app
        // --- combat actions
        BASIC_ATK,
        SKILL1,
        SKILL2,
        MOVEMENT,
        FORFEIT,
        ULTIMATE,
        MP_AOE,                  // either rally or panic
//        INFO,                    // view info about a selected unit
        // ---
        CONTINUE,                // goes to next time step; only available when battlefield not in ALLY_TURN state
        INVALID                  // input was invalid
    }

    InputType inputType;

    public UserInput() {
        this(InputType.SETUP);
    }

    public UserInput(InputType inputType) {
        this.inputType = inputType;
    }
}
