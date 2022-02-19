package pt.fm.hm.test;

import pt.fm.hm.heuristics.simple.SuitConsistencyHeuristic;
import pt.fm.hm.heuristics.base.Heuristic;

public class TestSolitaireSuitConsistencyHeuristic extends TestSolitaireBase {

    public static final Heuristic HEURISTIC = new SuitConsistencyHeuristic();

    @Override
    public Heuristic getHeuristic() {
        return HEURISTIC;
    }

}
