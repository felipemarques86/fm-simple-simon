package pt.fm.hm.heuristics.simple;

import pt.fm.hm.heuristics.base.SolvedColumnsMoreWeightHeuristic;
import pt.fm.hm.model.SolitaireCol;

import java.util.List;

import static pt.fm.hm.model.Solitaire.getCardValue;

public class DistanceToExpectedPositionHeuristic extends SolvedColumnsMoreWeightHeuristic {

    @Override
    public double calculate(SolitaireCol column) {
        if(column.isSolved())
            return 0;

        List<String> cards = column.getCards();
        double tot = 0;
        for(int i = 0; i < cards.size(); i++) {
            double cv = getCardValue(cards.get(i));
            double dist = Math.abs(cv - 13 - i);
            tot += dist;

        }
        column.setHeuristic(tot/cards.size());
        return column.getHeuristic();
    }
}
