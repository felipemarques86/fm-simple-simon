package pt.fm.hm.test;

import pt.fm.hm.heuristics.suit.SuitSequenceConsistencyHeuristic;
import pt.fm.hm.heuristics.base.Heuristic;

public class TestSolitaireSuitSequenceConsistencyHeuristic extends TestSolitaireBase {

    public static final Heuristic HEURISTIC = new SuitSequenceConsistencyHeuristic();

    @Override
    public Heuristic getHeuristic() {
        return HEURISTIC;
    }

}
