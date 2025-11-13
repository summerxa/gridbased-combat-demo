package model.entities;

import java.util.ArrayList;

public class EntityActionResult {
    public enum ActionResult {  // params value:
        TAKE_DAMAGE,            // entity object that took dmg, amount of dmg taken
        RECOVER_HP,             // entity that healed, amount of actual hp recovered, amount of overflow
        MOVEMENT                // entity that moved, starting tilePos, ending tilePos
    }

    public ActionResult resultType;

    public ArrayList<Object> params;

    public EntityActionResult(ActionResult resultType, ArrayList<Object> params) {
        this.resultType = resultType;
        this.params = params;
    }
}
