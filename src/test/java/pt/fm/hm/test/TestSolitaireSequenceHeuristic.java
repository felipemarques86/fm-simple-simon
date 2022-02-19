package pt.fm.hm.test;

import pt.fm.hm.heuristics.base.Heuristic;
import pt.fm.hm.heuristics.simple.SequenceHeuristic;

public class TestSolitaireSequenceHeuristic extends TestSolitaireBase {

    public static final Heuristic HEURISTIC = new SequenceHeuristic();

    @Override
    public Heuristic getHeuristic() {
        return HEURISTIC;
    }

}
