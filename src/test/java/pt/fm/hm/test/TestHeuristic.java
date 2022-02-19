package pt.fm.hm.test;

import org.junit.jupiter.api.Test;
import pt.fm.hm.heuristics.suit.SuitSequenceCountConsistencyHeuristics;
import pt.fm.hm.model.SolitaireCol;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHeuristic {

    @Test
    public void test() {
        SuitSequenceCountConsistencyHeuristics heuristic = new SuitSequenceCountConsistencyHeuristics();
        String col = "RO,DO,VO,10O,9O,8O,7O,6O,5O,4O,3O,2O,AO";
        SolitaireCol solitaireCol = new SolitaireCol(1);
        solitaireCol.addCards(Arrays.asList(col.split(",")));
        assertEquals(0.0d, heuristic.calculate(solitaireCol));

        String col2 = "RO,DO,VO,10O,9O,8O,7O,6O,5O,4O";
        SolitaireCol solitaireCol2 = new SolitaireCol(1);
        solitaireCol2.addCards(Arrays.asList(col2.split(",")));

        System.out.println(heuristic.calculate(solitaireCol));
        System.out.println(heuristic.calculate(solitaireCol2));

    }
}
