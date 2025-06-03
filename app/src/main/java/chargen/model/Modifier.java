package chargen.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value; // For an immutable class with getters, equals, hashCode, toString

/**
 * An immutable rule that applies a calculable adjustment to a character statistic.
 * Typically used for numerical changes.
 *
 * @param target The broad category of statistic being modified.
 * @param targetQualifier Optional specifier for the target (e.g., "STRENGTH" if target is ABILITY_SCORE). Can be null.
 * @param operation The mathematical or logical operation to perform.
 * @param value The magnitude of the modification. Type depends on operation (e.g., Number for ADD, String for dice).
 *              Can be null if operation doesn't require it (e.g., a future GRANT_ADVANTAGE).
 * @param sourceId A unique identifier for the game element (race, feat, item) that grants this modifier.
 * @param description Optional player-facing text describing this specific modifier instance. Can be null.
 */
@Value // Makes class final, all fields private final, generates getters, equals, hashCode, toString
@Builder(toBuilder = true) // Generates a builder pattern
public class Modifier {

    @NonNull ModifierTarget target;
    String targetQualifier; // Nullable
    @NonNull Operation operation;
    Object value;           // Nullable, using Object for flexibility (Number, String for dice)
    @NonNull String sourceId;
    String description;     // Nullable

    // Lombok's @Builder handles constructor creation.
    // Custom validation can be done in a private constructor called by the builder,
    // or by static factory methods if not using @Builder, or sometimes within builder methods.
    // For simplicity with @Builder, validation might happen when applying the modifier
    // or by ensuring data sources are correct.
    // If strict constructor validation is needed with @Builder, it's a bit more involved.
    // One way is a private all-args constructor that the builder calls, and that constructor does validation.
    private Modifier(@NonNull ModifierTarget target, String targetQualifier, @NonNull Operation operation,
                     Object value, @NonNull String sourceId, String description) {
        // Perform Objects.requireNonNull checks and other validations here if desired
        // This is just an example; Lombok's @NonNull on fields helps too.
        this.target = target;
        this.targetQualifier = targetQualifier;
        this.operation = operation;
        this.value = value;
        // this.max = max;
        this.sourceId = sourceId;
        this.description = description;

        // Similar validation logic as in the record's compact constructor can go here.
    }
}
