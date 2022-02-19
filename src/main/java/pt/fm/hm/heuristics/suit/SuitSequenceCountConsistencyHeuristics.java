package pt.fm.hm.heuristics.suit;

import pt.fm.hm.heuristics.base.SolvedColumnsMoreWeightHeuristic;
import pt.fm.hm.model.SolitaireCol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pt.fm.hm.model.Solitaire.getCardValue;
import static pt.fm.hm.model.Solitaire.getKind;

public class SuitSequenceCountConsistencyHeuristics extends SolvedColumnsMoreWeightHeuristic {

    @Override
    public double calculate(SolitaireCol column) {
        if(column.isSolved())
            return 0;

        List<String> cards = column.getCards();
        Map<String, Double> counts = new HashMap<>();
        counts.put("P", 1d); counts.put("E", 1d); counts.put("O", 1d); counts.put("C", 1d);

        double tot = 1;
        for(int i = 0; i < cards.size()-1; i++) {
            double c1 = getCardValue(cards.get(i));
            double c2 = getCardValue(cards.get(i+1));
            if(c1 == (c2+1)) {
                tot += 1;
            }
            String kind = getKind(cards.get(i));
            counts.put(kind, counts.get(kind) + 1);
            kind = getKind(cards.get(i+1));
            counts.put(kind, counts.get(kind) + 1);
        }

        double count = (counts.get("P") * counts.get("E") * counts.get("O") * counts.get("C")) / 13.0d;
        double h = (Math.max(13, cards.size()) - tot) + count;
        column.setHeuristic(h);
        return  column.getHeuristic();
    }
}
