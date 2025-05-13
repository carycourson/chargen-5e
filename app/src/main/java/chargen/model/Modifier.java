package chargen.model;

import lombok.NonNull;

/**
 * An immutable rule that alters a game value.
 *
 * @param target    what is affected (e.g., "strength", "armorClass")
 * @param operation how the value changes (see {@link Operation})
 * @param value     by how much (or the exact value for SET)
 * @param max       optional cap; null if no cap applies
 */
public record Modifier(
        @NonNull String target,
        @NonNull Operation operation,
        Number value,
        Number max
) {}
