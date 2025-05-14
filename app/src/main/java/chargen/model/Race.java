package chargen.model;

import lombok.NonNull;

import java.util.List;
import java.util.Objects;

import chargen.registry.Feats;
import chargen.registry.Skills;
import chargen.registry.Traits;

/**
 * Character races provide ability score modifiers and racial traits (features).
 */
public enum Race {

    HUMAN(
        "Human",
        "Versatile and ambitious, humans adapt to many roles.",
        List.of(
            new Modifier("strength", Operation.ADD, 1, null),
            new Modifier("dexterity", Operation.ADD, 1, null),
            new Modifier("constitution", Operation.ADD, 1, null),
            new Modifier("intelligence", Operation.ADD, 1, null),
            new Modifier("wisdom", Operation.ADD, 1, null),
            new Modifier("charisma", Operation.ADD, 1, null)
        ),
        // Feats
        List.of(), // No special feats by default,
        // Traits
        List.of() // No special racial features by default
        // TODO: Add Skills, Proficiencies
        // Proficiencies
        // List.of()
        // Skills
        // List.of()
    ),

    ELF(
        "Elf",
        "Elves are swift and perceptive, with keen senses and keen minds.",
        List.of(new Modifier(Ability.DEXTERITY, Operation.ADD, 2, null)),
        // Feats
        List.of(),
        // Traits
        List.of(
            Traits.DARKVISION,
            Traits.FEY_ANCESTRY
        )
        // TODO: Add Skills, Proficiencies
        // Proficiencies
        // List.of()
        // Skills
        // List.of()
    );

    @NonNull private final String name;
    @NonNull private final String description;
    @NonNull private final List<Modifier> abilityModifiers;
    @NonNull private final List<Feats> feats;
    @NonNull private final List<Traits> traits;
    // TODO: Add Skills, Proficiencies
    // @NonNull private final List<Skills> skills;

    /**
     * Construct a Race with its properties.
     */
    Race(
        String name,
        String description,
        List<Modifier> abilityModifiers,
        List<Trait> traits,
        List<Feature> feats
        // TODO: Add Skills, Proficiencies
        // List<Feature> skills
    ) {
        this.name = Objects.requireNonNull(name, "name");
        this.description = Objects.requireNonNull(description, "description");
        this.abilityModifiers = Objects.requireNonNull(abilityModifiers, "abilityModifiers");
        this.traits = Objects.requireNonNull(traits, "traits");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Ability score adjustments granted by this race.
     */
    public List<Modifier> getAbilityModifiers() {
        return abilityModifiers;
    }

    /**
     * Racial traits (features) automatically applied on character creation.
     */
    public List<Feature> getTraits() {
        return traits;
    }

}
