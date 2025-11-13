package model.entities.basekits;

import driver.UserInput;
import model.Constants;
import model.entities.Ally;
import model.entities.EntityActionChanges;
import model.entities.EntityActionResult;
import model.entities.EntityAttributes;
import model.gridmap.TilePos;
import model.gridmap.tileactions.DealDamageTileAction;
import model.gridmap.tileactions.HealTileAction;

import java.util.ArrayList;

/**
 * Kit for tutorial character "P".
 */
public class PBase extends Ally {
    final int BASIC_ATK_RANGE = 3;
    final int S_SKILL_HEAL = 100;
    final int C_SKILL_DMG = 200;
    final int LIFE_DMG = 400;
    final int DEATH_DMG = 600;
    final double ULT_DMG_TO_HEAL_RATIO = 1.0;

    public PBase(TilePos pos) {
        super(new EntityAttributes("Char P", 'P', 1000, 50, 50, pos));

        skillCD[0] = skillCD[1] = 2;

        skillMP[0] = skillMP[1] = 10;

        lifeThreshold = deathThreshold = 25;
    }

    public ArrayList<TilePos> basicAtkRange() {
        return TilePos.getQueenAOE(attributes.pos, BASIC_ATK_RANGE, false);
    }

    public ArrayList<TilePos> moveRange() {
        return TilePos.getQueenAOE(attributes.pos, Constants.MAX_RANGE_SIZE, false);
    }

    public ArrayList<TilePos> skill1Range() {
        return TilePos.singleTile(attributes.pos);
    }

    public ArrayList<TilePos> skill2Range() {
        return TilePos.getQueenAOE(attributes.pos, Constants.MAX_RANGE_SIZE, false);
    }

    public ArrayList<TilePos> lifeUltRange() {
        return TilePos.getAllTiles(attributes.pos, false);
    }

    public ArrayList<TilePos> deathUltRange() {
        return lifeUltRange();
    }

    public EntityActionChanges skill1Action(TilePos pos) {
        EntityActionChanges actions = new EntityActionChanges();
        actions.addAction(pos, new HealTileAction(computeOffense(S_SKILL_HEAL)));
        return actions;
    }

    public EntityActionChanges skill2Action(TilePos pos) {
        EntityActionChanges actions = new EntityActionChanges();
        actions.addAction(pos, new DealDamageTileAction(computeOffense(C_SKILL_DMG)));
        return actions;
    }

    protected EntityActionChanges lifeUltAction(TilePos pos) {
        return buildUltAction(pos, true);
    }

    protected EntityActionChanges deathUltAction(TilePos pos) {
        return buildUltAction(pos, false);
    }

    EntityActionChanges buildUltAction(TilePos pos, boolean isLife) {
        EntityActionChanges actions = new EntityActionChanges();
        actions.addAction(pos, new DealDamageTileAction(computeOffense(isLife ? LIFE_DMG : DEATH_DMG)));
        return actions;
    }

    protected void processActionResult(UserInput.InputType move, ArrayList<EntityActionResult> results) {
        if (move == UserInput.InputType.ULTIMATE) {
            for (EntityActionResult result : results) {
                heal((int)( (int)result.params.get(1) * ULT_DMG_TO_HEAL_RATIO ));
            }
        }
    }
}
