package chargen.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A character statistic with a base value and dynamic {@link Modifier}s.
 *
 * Thread‑safe read‑only access; modifier mutations are synchronized.
 */
public final class AbilityScore {

    private final String name;          // e.g. "strength"
    private int base;                   // raw, unmodified score

    /** Mutable list; guard with 'this'. */
    private final List<Modifier> modifiers = new ArrayList<>();

    public AbilityScore(String name, int base) {
        if (base < 0) throw new IllegalArgumentException("base must be ≥ 0");
        this.name = Objects.requireNonNull(name, "name");
        this.base = base;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Public read API
    // ─────────────────────────────────────────────────────────────────────────
    public String name()            { return name; }
    public synchronized int base()  { return base; }
    public synchronized List<Modifier> modifiers() {
        return Collections.unmodifiableList(modifiers);
    }

    /** Effective score after all modifiers. */
    public synchronized int total() {
        double value = base;

        for (Modifier m : modifiers) {
            if (!m.target().equalsIgnoreCase(name)) continue;

            value = switch (m.operation()) {
                case ADD       -> value + m.value().doubleValue();
                case SUBTRACT  -> value - m.value().doubleValue();
                case MULTIPLY  -> value * m.value().doubleValue();
                case DIVIDE    -> value / m.value().doubleValue();
                case SET       -> m.value().doubleValue();
                case MAX       -> Math.min(value, m.max().doubleValue());
                case MIN       -> Math.max(value, m.value().doubleValue());
            };
        }
        return (int) Math.round(value);
    }

    /** D&D‑style bonus/penalty: floor((total − 10) / 2). */
    public synchronized int scoreModifier() {
        return (int) Math.floor((total() - 10) / 2.0);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Mutation helpers
    // ─────────────────────────────────────────────────────────────────────────
    public synchronized void setBase(int base) {
        if (base < 0) throw new IllegalArgumentException("base must be ≥ 0");
        this.base = base;
    }

    public synchronized void addModifier(Modifier mod) { modifiers.add(mod); }
    public synchronized void removeModifier(Modifier mod) { modifiers.remove(mod); }
}
