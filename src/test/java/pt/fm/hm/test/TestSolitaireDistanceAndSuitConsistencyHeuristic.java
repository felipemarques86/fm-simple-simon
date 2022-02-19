package pt.fm.hm.test;

import pt.fm.hm.heuristics.distance.DistanceAndSuitConsistencyHeuristic;
import pt.fm.hm.heuristics.base.Heuristic;

public class TestSolitaireDistanceAndSuitConsistencyHeuristic extends TestSolitaireBase {

    public static final Heuristic HEURISTIC = new DistanceAndSuitConsistencyHeuristic();

    @Override
    public Heuristic getHeuristic() {
        return HEURISTIC;
    }

}
