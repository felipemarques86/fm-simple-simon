package pt.fm.hm.heuristics.simple;

import pt.fm.hm.heuristics.base.SolvedColumnsMoreWeightHeuristic;
import pt.fm.hm.model.SolitaireCol;

public class CardsLeftHeuristic extends SolvedColumnsMoreWeightHeuristic {
    @Override
    public double calculate(SolitaireCol column) {
        if(column.isSolved())
            return 0;

        double cardsLeft = Math.abs(13.0d - column.getCards().size());
        column.setHeuristic(cardsLeft);
        return cardsLeft;
    }
}
