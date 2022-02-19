package pt.fm.hm.heuristics.suit;

import pt.fm.hm.heuristics.base.SolvedColumnsMoreWeightHeuristic;
import pt.fm.hm.model.SolitaireCol;

import java.util.List;

import static pt.fm.hm.model.Solitaire.getCardValue;
import static pt.fm.hm.model.Solitaire.getKind;

public class SuitSequenceConsistencyHeuristic extends SolvedColumnsMoreWeightHeuristic {

    @Override
    public double calculate(SolitaireCol column) {
        if(column.isSolved())
            return 0;

        List<String> cards = column.getCards();
        double tot = 0;
        double tot2 = 0;
        for(int i = 0; i < cards.size()-1; i++) {
            String c1 = cards.get(i);
            String c2 = cards.get(i+1);
            boolean sameKind = getKind(c1).equals(getKind(c2));
            tot += sameKind ? 0 : (cards.size()-i);
            if(getCardValue(c1) == getCardValue(c2)+1) {
                tot2 += 1;
            }
        }
        double h = Math.max(13, cards.size()) - tot2 + tot;
        column.setHeuristic(h);
        return h;
    }
}
