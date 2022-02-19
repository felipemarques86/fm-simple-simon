package pt.fm.hm.heuristics.simple;

import pt.fm.hm.heuristics.base.SolvedColumnsMoreWeightHeuristic;
import pt.fm.hm.model.SolitaireCol;

import java.util.List;

import static pt.fm.hm.model.Solitaire.getCardValue;

public class SequenceHeuristic extends SolvedColumnsMoreWeightHeuristic {
    @Override
    public double calculate(SolitaireCol column) {
        if(column.isSolved())
            return 0;
        List<String> cards = column.getCards();
        double tot = 0;
        for(int i = 0; i < cards.size()-1; i++) {
            double c1 = getCardValue(cards.get(i));
            double c2 = getCardValue(cards.get(i+1));
            if(c1-c2 == 0) {
                tot += 1;
            }
        }
        tot = Math.max(13, cards.size())-tot;
        column.setHeuristic(tot);
        return tot;
    }
}
