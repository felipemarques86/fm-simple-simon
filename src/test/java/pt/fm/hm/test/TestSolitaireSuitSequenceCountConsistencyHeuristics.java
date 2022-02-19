package pt.fm.hm.test;

import pt.fm.hm.heuristics.base.Heuristic;
import pt.fm.hm.heuristics.suit.SuitSequenceCountConsistencyHeuristics;

public class TestSolitaireSuitSequenceCountConsistencyHeuristics extends TestSolitaireBase {

    public static final Heuristic HEURISTIC = new SuitSequenceCountConsistencyHeuristics();

    @Override
    public Heuristic getHeuristic() {
        return HEURISTIC;
    }

}
