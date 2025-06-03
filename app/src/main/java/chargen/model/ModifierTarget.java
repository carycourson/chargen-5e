package chargen.model; // Or your.basepackage.model

/**
 * Defines the broad categories of character statistics or attributes
 * that can be directly affected by a numerical {@link Modifier}.
 * Some targets may require a {@code targetQualifier} in the Modifier
 * to specify the exact instance (e.g., which ability score or speed type).
 */
public enum ModifierTarget {

    // --- Core Attributes ---
    /**
     * Modifies a base ability score (e.g., Strength, Dexterity).
     * Qualifier: String representing the {@link Ability} enum constant name (e.g., "STRENGTH").
     */
    ABILITY_SCORE,

    /**
     * Modifies the character's proficiency bonus.
     * (Rarely modified directly, usually scales with character level).
     */
    PROFICIENCY_BONUS,

    // --- Primary Combat Stats ---
    /**
     * Modifies Armor Class.
     * Qualifier: Not typically needed.
     */
    ARMOR_CLASS,

    /**
     * Modifies the maximum hit points.
     * Qualifier: Not typically needed.
     */
    MAX_HIT_POINTS,

    /**
     * Modifies the initiative bonus (excluding Dexterity modifier, which is added separately).
     * Qualifier: Not typically needed.
     */
    INITIATIVE_BONUS,

    // --- Movement ---
    /**
     * Modifies a specific type of speed (e.g., walking, flying).
     * Qualifier: String representing the movement type (e.g., "WALKING", "FLYING").
     */
    SPEED,

    // --- Skills & Senses (Passive Scores or Direct Bonuses to Checks shown on sheet) ---
    /**
     * Modifies the character's passive Perception score directly.
     * Qualifier: Not typically needed.
     */
    PASSIVE_PERCEPTION, // Could also be PASSIVE_SKILL with qualifier "PERCEPTION"

    // PASSIVE_INSIGHT, PASSIVE_INVESTIGATION (could be added if you want to calculate these)

    /**
     * Modifies the range of a specific sensory ability.
     * The character sheet would display each sense and its effective range.
     * Qualifier: A String uniquely identifying the sense (e.g., "NORMAL_VISION", "DARKVISION", 
     *              "BLINDSIGHT", "TREMORSENSE").
     *            It's recommended to use constants or an internal enum for these qualifier strings if possible
     *            on the consuming side for consistency.
     */
    SENSE_RANGE,

    // --- Saving Throws (Direct bonuses shown on sheet) ---
    /**
     * Applies a bonus or penalty directly to saving throws made with a specific ability.
     * This is for flat bonuses, not for granting proficiency (which is handled separately).
     * Qualifier: String representing the {@link Ability} enum constant name (e.g., "DEXTERITY_SAVE").
     */
    // SAVING_THROW_BONUS, // Decided against this for now, as usually save bonuses come from proficiency + ability mod.
                           // Specific item/feat bonuses often say "+X to all saving throws" or 
                           //   "+X to Dexterity saving throws".
                           // If "+X to ALL saving throws", could be a target. If specific, needs qualifier.
                           // Let's hold on this unless a clear V1 need arises beyond base calculation.

    // --- Spellcasting (Calculable numerical aspects) ---
    /**
     * Modifies the spell attack bonus (beyond ability mod + proficiency).
     * Qualifier: Optional String if bonus is for a specific class or spell type (e.g., "WIZARD_SPELLS", "FIRE_SPELLS").
     */
    SPELL_ATTACK_BONUS,

    /**
     * Modifies the spell save DC (beyond 8 + ability mod + proficiency).
     * Qualifier: Optional String if DC change is for a specific class.
     */
    SPELL_SAVE_DC,

    /**
     * Modifies the maximum number of spell slots for a given spell level.
     * Used for *bonus* slots from items/feats, not base class progression.
     * Qualifier: String indicating the spell level (e.g., "LEVEL_1", "LEVEL_5").
     */
    MAX_SPELL_SLOTS,

    // --- Resources / Pools ---
    /**
     * Modifies the maximum number of a specific named resource.
     * Qualifier: String naming the resource (e.g., "LUCK_POINTS", "KI_POINTS", "SORCERY_POINTS").
     */
    RESOURCE_MAX, // This is generic. Your Feats.LUCKY used "luckPoints" directly.
                  // We could have specific targets like LUCK_POINTS_MAX or use this generic one.
                  // Let's go with specific for now if the list is small.

    LUCK_POINTS_MAX, // From your Lucky feat example

    // --- Attack & Damage Rolls (Flat bonuses shown on sheet, NOT situational choices like GWM/SS penalty/bonus) ---
    /**
     * Applies a flat bonus/penalty to attack rolls under certain broad conditions.
     * Qualifier: String describing context (e.g., "MELEE_WEAPON_ATTACKS", "RANGED_SPELL_ATTACKS", "ANY").
     */
    ATTACK_ROLL_FLAT_BONUS,

    /**
     * Applies a flat bonus/penalty to damage rolls under certain broad conditions.
     * Qualifier: String describing context (e.g., "MELEE_WEAPON_ATTACKS", "FIRE_DAMAGE_SPELLS", "ANY").
     */
    DAMAGE_ROLL_FLAT_BONUS,


    // --- Other Specific Numerical Values from your examples ---
    // From Feats.SPELL_SNIPER's "spellRange" (assuming it's a multiplier to base ranges)
    // Could be handled differently if spell range is a property of each spell object.
    // For now, if there's a general "spell range multiplier" stat on the character:
    SPELL_RANGE_MULTIPLIER, // Qualifier: Not typically needed, applies generally.

    // The boolean flags like "ignoreHalfCover", "immuneToSleep" are NOT ModifierTargets.
    // They are informational aspects of Features/Traits, conveyed by their descriptions.
    // The generator doesn't need to *calculate* with "immuneToSleep" numerically.
}