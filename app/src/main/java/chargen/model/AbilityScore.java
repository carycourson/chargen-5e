package chargen.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Synchronized;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A character statistic with a base value and dynamic {@link Modifier}s.
 *
 * Provides thread-safe access and mutation via synchronized methods.
 */
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class AbilityScore {

    @EqualsAndHashCode.Include
    @NonNull
    private final Ability ability;

    @Getter(onMethod_=@Synchronized)
    private int base;

    /** Mutable list of modifiers; guarded by synchronization. */
    @Getter(onMethod_=@Synchronized)
    private final List<Modifier> modifiers = new ArrayList<>();

    public AbilityScore(Ability ability, int base) {
        if (base < 0) {
            throw new IllegalArgumentException("Ability score base must be ≥ 0");
        }
        this.ability = Objects.requireNonNull(ability, "ability");
        this.base = base;
    }

    /** Effective score after applying all matching modifiers. */
    @Synchronized
    public int total() {
        double value = base;
        for (Modifier m : modifiers) {
            if (!m.target().equalsIgnoreCase(ability.toString())) {
                continue;
            }
            switch (m.operation()) {
                case ADD -> value += m.value().doubleValue();
                case SUBTRACT -> value -= m.value().doubleValue();
                case MULTIPLY -> value *= m.value().doubleValue();
                case DIVIDE -> {
                    double divisor = m.value().doubleValue();
                    if (divisor == 0) {
                        throw new ArithmeticException("Cannot divide by zero");
                    }
                    value /= divisor;
                }
                case SET -> value = m.value().doubleValue();
                case MAX -> {
                    Number cap = m.max();
                    if (cap == null) {
                        throw new IllegalStateException("MAX operation requires a max value");
                    }
                    value = Math.min(value, cap.doubleValue());
                }
                case MIN -> value = Math.max(value, m.value().doubleValue());
            }
        }
        return (int) Math.round(value);
    }

    /** D&D-style bonus/penalty: floor((total - 10) / 2). */
    @Synchronized
    public int scoreModifier() {
        return (int) Math.floor((total() - 10) / 2.0);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Mutation helpers
    // ─────────────────────────────────────────────────────────────────────────
    @Synchronized
    public void setBase(int base) {
        if (base < 0) {
            throw new IllegalArgumentException("Base must be ≥ 0");
        }
        this.base = base;
    }

    @Synchronized
    public void addModifier(@NonNull Modifier mod) {
        Objects.requireNonNull(mod, "modifier");
        if (!mod.target().equalsIgnoreCase(ability.toString())) {
            throw new IllegalArgumentException("Cannot apply modifier for '" + mod.target() + "' to ability " + ability);
        }
        modifiers.add(mod);
    }

    @Synchronized
    public void removeModifier(@NonNull Modifier mod) {
        Objects.requireNonNull(mod, "modifier");
        modifiers.remove(mod);
    }

    /** Clears all modifiers from this ability score. */
    @Synchronized
    public void clearModifiers() {
        modifiers.clear();
    }
}

