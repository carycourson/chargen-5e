package chargen.model;

// import org.junit.jupiter.api.Test;

// import chargen.activity.AbilityScore;

// import static org.junit.jupiter.api.Assertions.*;

class AbilityScoreTest {

//     @Test
//     void totalHandlesAdd() {
//         AbilityScore str = new AbilityScore(Ability.STRENGTH, 16);
//         str.addModifier(new Modifier("strength", Operation.ADD, 2, null));
//         str.addModifier(new Modifier("strength", Operation.MAX, 0, 18));

//         System.out.println("strength.add = " + str.total());
//         assertEquals(18, str.total());
//         assertEquals(4,  str.scoreModifier());
//     }

//     @Test
//     void totalHandlesSubtract() {
//         AbilityScore str = new AbilityScore(Ability.STRENGTH, 16);
//         str.addModifier(new Modifier("strength", Operation.SUBTRACT, 2, null));

//         assertEquals(14, str.total());
//         assertEquals(2,  str.scoreModifier());
//     }

//     @Test
//     void totalHandlesMultiply() {
//         AbilityScore str = new AbilityScore(Ability.STRENGTH, 8);
//         str.addModifier(new Modifier("strength", Operation.MULTIPLY, 2, null));

//         assertEquals(16, str.total());
//         assertEquals(3,  str.scoreModifier());
//     }

//     @Test
//     void totalHandlesDivide() {
//         AbilityScore str = new AbilityScore(Ability.STRENGTH, 16);
//         str.addModifier(new Modifier("strength", Operation.DIVIDE, 2, null));

//         assertEquals(8, str.total());
//         assertEquals(-1,  str.scoreModifier());
//     }

//     @Test
//     void totalHandlesSet() {
//         AbilityScore str = new AbilityScore(Ability.STRENGTH, 16);
//         str.addModifier(new Modifier("strength", Operation.SET, 14, null));

//         assertEquals(14, str.total());
//         assertEquals(2,  str.scoreModifier());
//     }

//     @Test
//     void totalHandlesMax() {
//         AbilityScore str = new AbilityScore(Ability.STRENGTH, 16);
//         str.addModifier(new Modifier("strength", Operation.MAX, str.getBase(), 8));

//         assertEquals(8, str.total());
//         assertEquals(-1,  str.scoreModifier());
//     }

//     @Test
//     void totalHandlesMin() {
//         AbilityScore str = new AbilityScore(Ability.STRENGTH, 16);
//         str.addModifier(new Modifier("strength", Operation.MIN, str.getBase(), 8));

//         assertEquals(16, str.total());
//         assertEquals(3,  str.scoreModifier());
//     }

//     @Test
//     void totalHandlesMin2() {
//         AbilityScore str = new AbilityScore(Ability.STRENGTH, 6);
//         str.addModifier(new Modifier("strength", Operation.MIN, str.getBase(), 8));

//         System.out.println("strength.min2 = " + str.getBase() + " | 8 = " + str.total());
//         assertEquals(6, str.total());
//         assertEquals(-2,  str.scoreModifier());
//     }

//     @Test
//     void totalHandlesRemoveModifier() {
//         AbilityScore str = new AbilityScore(Ability.STRENGTH, 16);
//         str.addModifier(new Modifier("strength", Operation.ADD, 2, null));
//         str.removeModifier(new Modifier("strength", Operation.ADD, 2, null));

//         assertEquals(16, str.total());
//         assertEquals(3,  str.scoreModifier());
//     }
}
