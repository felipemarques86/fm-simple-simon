package pt.fm.hm.test;

import pt.fm.hm.heuristics.distance.DistanceAndSuitCountConsistencyHeuristic;
import pt.fm.hm.heuristics.base.Heuristic;

public class TestSolitaireDistanceAndSuitCountConsistencyHeuristic extends TestSolitaireBase {

    public static final Heuristic HEURISTIC = new DistanceAndSuitCountConsistencyHeuristic();

    @Override
    public Heuristic getHeuristic() {
        return HEURISTIC;
    }

}
