package chargen.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Synchronized;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

/**
 * A passive trait automatically granted by race or environment,
 * such as Darkvision or Fey Ancestry.
 */
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public final class Trait {

    @EqualsAndHashCode.Include
    @NonNull
    private final String name;

    @NonNull
    private final String description;

    /** Modifiers this trait confers (e.g., darkvision range, speed bonus). */
    @NonNull
    private final List<Modifier> modifiers;

    /**
     * Apply this trait's modifiers to the character.
     */
    @Synchronized
    public void applyTo(@NonNull Character character) {
        Objects.requireNonNull(character, "character");
        for (Modifier mod : modifiers) {
            character.addModifier(mod);
        }
    }
}
