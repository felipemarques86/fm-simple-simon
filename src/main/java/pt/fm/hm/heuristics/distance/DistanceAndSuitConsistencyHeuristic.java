package pt.fm.hm.heuristics.distance;

import pt.fm.hm.heuristics.base.Heuristic;
import pt.fm.hm.model.SolitaireCol;

import java.util.List;

import static pt.fm.hm.model.Solitaire.getCardValue;
import static pt.fm.hm.model.Solitaire.getKind;

public class DistanceAndSuitConsistencyHeuristic extends Heuristic {

    @Override
    public double calculate(SolitaireCol column) {
        if(column.isSolved())
            return 0;

        List<String> cards = column.getCards();
        double sameKindTot = 1;
        double posTot = 0;
        for(int i = 0; i < cards.size()-1; i++) {
            String c1 = cards.get(i);
            String c2 = cards.get(i+1);
            boolean sameKind = getKind(c1).equals(getKind(c2));
            sameKindTot += sameKind ? 0 : (cards.size()-i);

            double cv = getCardValue(c1);
            double dist = Math.abs(cv - 13 - i);
            posTot += dist;
        }

        double h = (sameKindTot + posTot)/cards.size();
        column.setHeuristic(h);
        return h;


    }
}
