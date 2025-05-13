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
    MAX,          // cap at a maximum
    MIN           // floor at a minimum
}
