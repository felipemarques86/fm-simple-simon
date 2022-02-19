package pt.fm.hm.heuristics.simple;

import pt.fm.hm.heuristics.base.SolvedColumnsMoreWeightHeuristic;
import pt.fm.hm.model.SolitaireCol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pt.fm.hm.model.Solitaire.getKind;

public class SuitCountConsistencyHeuristics extends SolvedColumnsMoreWeightHeuristic {

    @Override
    public double calculate(SolitaireCol column) {
        if(column.isSolved())
            return 0;

        List<String> cards = column.getCards();
        Map<String, Double> counts = new HashMap<>();
        counts.put("P", 1d); counts.put("E", 1d); counts.put("O", 1d); counts.put("C", 1d);

        for(int i = 0; i < cards.size()-1; i++) {
            String kind = getKind(cards.get(i));
            counts.put(kind, counts.get(kind) + 1);
            kind = getKind(cards.get(i+1));
            counts.put(kind, counts.get(kind) + 1);
        }

        double count = counts.get("P") * counts.get("E") * counts.get("O") * counts.get("C") / 13;
        column.setHeuristic(count);
        return  column.getHeuristic();
    }
}
