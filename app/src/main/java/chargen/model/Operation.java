package chargen.model;

/**
 * Describes how a modifier changes its target.
 * Expand or rename the entries as your rules engine grows.
 */
public enum Operation {
    ADD,          // +x
    SUBTRACT,     // –x
    MULTIPLY,     // ×x
    DIVIDE,       // ÷x
    SET,          // set value directly
    AT_MOST,          // cap at a maximum
    AT_LEAST           // floor at a minimum
}
