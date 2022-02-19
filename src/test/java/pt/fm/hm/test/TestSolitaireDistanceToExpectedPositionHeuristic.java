package pt.fm.hm.test;

import pt.fm.hm.heuristics.simple.DistanceToExpectedPositionHeuristic;
import pt.fm.hm.heuristics.base.Heuristic;

public class TestSolitaireDistanceToExpectedPositionHeuristic extends TestSolitaireBase {

    public static final Heuristic HEURISTIC = new DistanceToExpectedPositionHeuristic();

    @Override
    public Heuristic getHeuristic() {
        return HEURISTIC;
    }

}
