package pt.fm.hm.test;

import pt.fm.hm.heuristics.simple.SuitCountConsistencyHeuristics;
import pt.fm.hm.heuristics.base.Heuristic;

public class TestSolitaireSuitCountConsistencyHeuristic extends TestSolitaireBase {

    public static final Heuristic HEURISTIC = new SuitCountConsistencyHeuristics();

    @Override
    public Heuristic getHeuristic() {
        return HEURISTIC;
    }

}
