package chargen.registry;

import chargen.model.Ability;
import chargen.model.Feature;
import chargen.model.Modifier;
import chargen.model.Operation;

import java.util.List;
import java.util.function.Predicate;

/**
 * A short list of example Feats for demonstration purposes.
 */
public final class Feats {

    public static final Feature SPELL_SNIPER = Feature.builder()
        .name("Spell Sniper")
        .description("Double the range of your ranged spell attacks; ignore half cover.")
        .modifiers(List.of(
            new Modifier("spellRange", Operation.MULTIPLY, 2, null),
            new Modifier("ignoreHalfCover", Operation.SET, 1, null)
        ))
        .prerequisites(List.of(c -> true))
        .build();

    public static final Feature GREAT_WEAPON_MASTER = Feature.builder()
        .name("Great Weapon Master")
        .description("-5 to hit with heavy weapons; +10 damage on hit; bonus attack on crit or kill.")
        .modifiers(List.of(
            new Modifier("meleeAttackRoll", Operation.ADD, -5, null),
            new Modifier("meleeDamage", Operation.ADD, 10, null)
        ))
        .prerequisites(List.of(
            c -> c.getAbilityScore(Ability.STRENGTH).total() >= 15
        ))
        .build();

    public static final Feature LUCKY = Feature.builder()
        .name("Lucky")
        .description("You have 3 luck points; you can reroll an attack roll, saving throw, or ability check.")
        .modifiers(List.of(
            new Modifier("luckPoints", Operation.ADD, 3, null)
        ))
        .prerequisites(List.of(c -> true))
        .build();

    public static final Feature SHARPSHOOTER = Feature.builder()
        .name("Sharpshooter")
        .description("-5 to ranged attacks for +10 damage; ignore cover bonuses.")
        .modifiers(List.of(
            new Modifier("rangedAttackRoll", Operation.ADD, -5, null),
            new Modifier("rangedDamage", Operation.ADD, 10, null),
            new Modifier("ignoreCover", Operation.SET, 1, null)
        ))
        .prerequisites(List.of(c -> true))
        .build();

    public static final Feature MOBILE = Feature.builder()
        .name("Mobile")
        .description("Your speed increases by 10 feet; you ignore difficult terrain when you Dash.")
        .modifiers(List.of(
            new Modifier("speed", Operation.ADD, 10, null),
            new Modifier("ignoreDifficultTerrainOnDash", Operation.SET, 1, null)
        ))
        .prerequisites(List.of(c -> true))
        .build();

    public static final Feature SHELTER_OF_THE_FAITHFUL = Feature.builder()
        .name("Shelter of the Faithful")
        .description("You can find shelter and food for yourself and up to 5 companions.")
        .modifiers(List.of(
            new Modifier("shelter", Operation.SET, 1, null)
        ))
        .prerequisites(List.of(c -> true))
        .build();

    public static final Feature MILITARY_RANK = Feature.builder()
        .name("Military Rank")
        .description("You can exert influence over other soldiers and gain access to military encampments.")
        .modifiers(List.of(
            new Modifier("militaryRank", Operation.SET, 1, null)
        ))
        .prerequisites(List.of(c -> true))
        .build();

    private Feats() { /* no instantiation */ }
}
