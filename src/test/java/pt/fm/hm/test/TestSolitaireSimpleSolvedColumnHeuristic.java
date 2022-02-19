package pt.fm.hm.test;

import pt.fm.hm.heuristics.simple.SimpleSolvedColumnHeuristic;
import pt.fm.hm.heuristics.base.Heuristic;

public class TestSolitaireSimpleSolvedColumnHeuristic extends TestSolitaireBase {

    public static final Heuristic HEURISTIC = new SimpleSolvedColumnHeuristic();

    @Override
    public Heuristic getHeuristic() {
        return HEURISTIC;
    }

}
