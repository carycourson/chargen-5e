package chargen.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.Synchronized;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * A selectable feat or trait (e.g. Spell Sniper, Great Weapon Master) that
 * can apply modifiers to a Character and may have prerequisites.
 */
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public final class Feature {

    @EqualsAndHashCode.Include
    @NonNull
    private final String name;

    @NonNull
    private final String description;

    /** Modifiers this feature grants when applied. */
    @NonNull
    private final List<Modifier> modifiers;

    /** Predicates that must all pass for this feature to be selectable. */
    @NonNull
    private final List<Predicate<Character>> prerequisites;

    /**
     * Check whether this feature is available to the given character.
     */
    public boolean isAvailable(Character character) {
        Objects.requireNonNull(character, "character");
        return prerequisites.stream()
                .allMatch(pred -> pred.test(character));
    }

    /**
     * Apply this feature's modifiers to the character if prerequisites are met.
     */
    @Synchronized
    public void applyTo(@NonNull Character character) {
        if (!isAvailable(character)) {
            throw new IllegalStateException(
                "Prerequisites not met for feature '" + name + "'");
        }
        modifiers.forEach(character::addModifier);
    }
}
