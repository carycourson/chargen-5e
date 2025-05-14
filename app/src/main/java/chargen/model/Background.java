package chargen.model;

import lombok.NonNull;

import java.util.List;
import java.util.Objects;

import chargen.registry.Feats;
import chargen.registry.Skills;

/**
 * Character backgrounds provide additional proficiencies, languages,
 * equipment, and a special feature trait.
 */
public enum Background {

    ACOLYTE(
        "Acolyte",
        "You have spent your life in the service of a temple to a specific god or pantheon of gods.",
        List.of(Skills.ARCANA, Skills.RELIGION),
        List.of("Calligrapher's Supplies"),
        List.of("Common", "Celestial"),
        Feats.SHELTER_OF_THE_FAITHFUL,
        List.of(
            "Holy Symbol",
            "Prayer Book",
            "5 sticks of incense",
            "Vestments",
            "Common clothes",
            "Belt pouch"
        ),
        15
    ),

    SOLDIER(
        "Soldier",
        "You are trained in the military and have served as a member of an army.",
        List.of(Skills.ATHLETICS, Skills.INTIMIDATION),
        List.of("Land Vehicles"),
        List.of(),
        Feats.MILITARY_RANK,
        List.of(
            "Insignia of rank",
            "Trophy taken from a fallen enemy",
            "Set of bone dice or deck of cards",
            "Common clothes",
            "Belt pouch"
        ),
        10
    );

    @NonNull private final String name;
    @NonNull private final String description;
    @NonNull private final List<Skills> skillProficiencies;
    @NonNull private final List<String> toolProficiencies;
    @NonNull private final List<String> languages;
    @NonNull private final Feature backgroundFeature;
    @NonNull private final List<String> equipment;
    private final int startingGold;

    /**
     * Construct a Background with all its properties.
     */
    Background(
        String name,
        String description,
        List<Skills> skillProficiencies,
        List<String> toolProficiencies,
        List<String> languages,
        Feature backgroundFeature,
        List<String> equipment,
        int startingGold
    ) {
        this.name = Objects.requireNonNull(name, "name");
        this.description = Objects.requireNonNull(description, "description");
        this.skillProficiencies = Objects.requireNonNull(skillProficiencies, "skillProficiencies");
        this.toolProficiencies = Objects.requireNonNull(toolProficiencies, "toolProficiencies");
        this.languages = Objects.requireNonNull(languages, "languages");
        this.backgroundFeature = Objects.requireNonNull(backgroundFeature, "backgroundFeature");
        this.equipment = Objects.requireNonNull(equipment, "equipment");
        this.startingGold = startingGold;
    }

    /**
     * Returns the list of Features granted by this background.
     */
    public List<Feature> getFeatures() {
        return List.of(backgroundFeature);
    }
}
