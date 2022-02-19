package pt.fm.hm.test;

import pt.fm.hm.heuristics.simple.CardsLeftHeuristic;
import pt.fm.hm.heuristics.base.Heuristic;

public class TestSolitaireCardsLeftHeuristic extends TestSolitaireBase {

    public static final Heuristic HEURISTIC = new CardsLeftHeuristic();

    @Override
    public Heuristic getHeuristic() {
        return HEURISTIC;
    }

}
