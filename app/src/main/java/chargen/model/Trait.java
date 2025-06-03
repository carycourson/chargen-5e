package chargen.model;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

// Assuming Modifier, ModifierTarget, Operation are in the same package or imported

@Value
@Builder(toBuilder = true)
@SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Lombok @Value + " +
        "@Singular ensures an unmodifiable list for 'modifiers'")
public class Trait {
    @NonNull
    String name;
    @NonNull
    String description;
    @NonNull
    @Singular
    List<Modifier> modifiers;
}
