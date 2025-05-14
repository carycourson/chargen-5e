package chargen.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Synchronized;
import lombok.ToString;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Core character model capturing race, class, background, abilities, and features.
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class Character {

    @NonNull
    private final String name;

    @NonNull
    private final Race race;

    @NonNull
    private final CharacterClass characterClass;

    @NonNull
    private final Background background;

    /** Level of the character */
    @Getter
    private final int level;

    /** Proficiency bonus, derived from level */
    @Getter
    private final int proficiencyBonus;

    /** Base ability scores mapped by Ability */
    @Getter
    private final Map<Ability, AbilityScore> abilityScores;

    /** Applied modifiers from features, spells, etc. */
    @Getter
    private final List<Modifier> modifiers = new ArrayList<>();

    /** Selected feats or class features */
    @Getter
    private final List<Feature> features = new ArrayList<>();

    /**
     * Initialize dynamic state after construction, such as applying race and background features.
     */
    @Synchronized
    public void initialize() {
        // Apply racial ability modifiers
        race.getAbilityModifiers().forEach(this::addModifier);

        // Apply background & class default features
        background.getFeatures().forEach(this::addFeature);
        characterClass.getFeatures(level).forEach(this::addFeature);
    }

    /** Add a modifier and apply to appropriate AbilityScore or derived stat. */
    @Synchronized
    public void addModifier(@NonNull Modifier mod) {
        Objects.requireNonNull(mod, "modifier");
        modifiers.add(mod);
        // If modifier targets an ability, apply to its AbilityScore
        try {
            Ability target = Ability.valueOf(mod.target().toUpperCase());
            abilityScores.get(target).addModifier(mod);
        } catch (IllegalArgumentException | NullPointerException e) {
            // Non-ability modifiers handled elsewhere (e.g. armorClass, speed)
        }
    }

    /** Add and apply a Feature */
    @Synchronized
    public void addFeature(@NonNull Feature feature) {
        Objects.requireNonNull(feature, "feature");
        feature.applyTo(this);
        features.add(feature);
    }

    /** Calculate max hit points: hitDie + con modifier */
    public int calculateMaxHitPoints() {
        int conMod = abilityScores.get(Ability.CONSTITUTION).scoreModifier();
        return characterClass.getHitDie() + conMod + (level - 1) * (characterClass.getHitDie() / 2 + 1 + conMod);
    }

    /** Calculate armor class: 10 + dex modifier + any static modifiers targeting "armorClass" */
    public int calculateArmorClass() {
        int dexMod = abilityScores.get(Ability.DEXTERITY).scoreModifier();
        int baseAc = 10 + dexMod;
        return modifiers.stream()
                .filter(m -> "armorClass".equalsIgnoreCase(m.target()))
                .mapToInt(m -> m.value().intValue())
                .sum() + baseAc;
    }

    // TODO: add methods for initiative, saving throws, skills, movement speed, spells, etc.
}

