package pt.fm.hm.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.fm.hm.heuristics.base.Heuristic;
import pt.fm.hm.model.Solitaire;
import pt.fm.hm.model.SolitaireCol;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public abstract class TestSolitaireBase {


    public abstract Heuristic getHeuristic();

    private static final String PATH = "C:\\temp\\";

    @Test
    public void basicTest() {
        Assertions.assertEquals(Solitaire.getCardValue("AP") + 1, Solitaire.getCardValue("2P"));
        assertEquals(Solitaire.getKind("AP"), Solitaire.getKind("2P"));
        assertEquals(Solitaire.getKind("10E"), Solitaire.getKind("AE"));
        assertEquals(Solitaire.getKind("DP"), Solitaire.getKind("5P"));

        SolitaireCol sol = new SolitaireCol(1);
        sol.addCards(Arrays.asList("AC","2C","3C","4C","5C","6C","7C","8C","9C","10C","VP","DP","RP"));
        assertFalse(sol.isSolved());

        SolitaireCol sol2 = new SolitaireCol(1);
        sol2.addCards(Arrays.asList("RC", "DC", "VC", "10C", "9C", "8C", "7C", "6C", "5C", "4C", "3C", "2C", "AC"));
        assertTrue(sol2.isSolved());

        assertNotEquals(sol.sequenceDistance(), 0.0d);

    }

    @Test
    public void testMovement() {
        Solitaire initial = TestUtils.getProposedProblem();

        System.out.println(initial);

        assertTrue(initial.move("10P;4"));
        assertFalse(initial.move("7O;9"));
    }

    @Test
    public void testSearchProblemInstance() {
        Solitaire initial = TestUtils.getProposedProblem();
        TestUtils.run(-1, initial, getHeuristic(), PATH + "searchProblem_ " + getHeuristic().getClass().getName() + ".csv");
    }

    @Test
    public void testHeuristicsForSolved() {
        Solitaire initial = TestUtils.getGoalSolution();

        assertTrue(initial.isSolved());
        assertEquals(0.0d, getHeuristic().calculate(initial));
        TestUtils.run(2, initial, getHeuristic(), null);
    }

    @Test
    public void testCloseToGoalScenario1() {
        Solitaire initial = TestUtils.getCloseToGoalSolution1();

        TestUtils.run(500, initial, getHeuristic(), PATH + "instance1.csv");
    }

    @Test
    public void testCloseToGoalScenario2() {
        Solitaire initial = TestUtils.getCloseToGoalSolution2();

        TestUtils.run(-1, initial, getHeuristic(), PATH + "instance2.csv");
    }

    @Test
    public void testCloseToGoalScenario3() {
        Solitaire initial = TestUtils.getCloseToGoalSolution3();

        TestUtils.run(-1, initial, getHeuristic(), PATH + "instance3.csv");
    }

    @Test
    public void testCloseToGoalScenario4() {
        Solitaire initial = TestUtils.getCloseToGoalSolution4();

        TestUtils.run(-1, initial, getHeuristic(), PATH + "instance4.csv");
    }

    @Test
    public void testCloseToGoalScenario5() {
        Solitaire initial = TestUtils.getCloseToGoalSolution5();

        TestUtils.run(-1, initial, getHeuristic(), PATH + "instance5.csv");
    }

    @Test
    public void testCloseToGoalScenario6() {
        Solitaire initial = TestUtils.getCloseToGoalSolution6();

        TestUtils.run(-1, initial, getHeuristic(), PATH + "instance6.csv");
    }
}
