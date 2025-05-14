package chargen.registry;

import chargen.model.Modifier;
import chargen.model.Operation;
import chargen.model.Trait;

import java.util.List;

/**
 * Registry of passive traits granted by races or environments.
 */
public final class Traits {

    /**
     * Darkvision: You can see in dim light as if it were bright light
     * within 60 feet of you, and in darkness as if it were dim light.
     */
    public static final Trait DARKVISION = Trait.builder()
        .name("Darkvision")
        .description("You can see in dim light within 60 feet as if it were bright light, " +
                     "and in darkness as if it were dim light.")
        .modifiers(List.of(
            new Modifier("darkvisionRange", Operation.SET, 60, null)
        ))
        .build();

    /**
     * Fey Ancestry: You have advantage on saving throws against being charmed,
     * and magic can’t put you to sleep.
     */
    public static final Trait FEY_ANCESTRY = Trait.builder()
        .name("Fey Ancestry")
        .description("You have advantage on saving throws against being charmed, " +
                     "and magic cannot put you to sleep.")
        .modifiers(List.of(
            new Modifier("advantageOnCharmedSaves", Operation.SET, 1, null),
            new Modifier("immuneToSleep", Operation.SET, 1, null)
        ))
        .build();

    private Traits() {
        // Prevent instantiation
    }
}
