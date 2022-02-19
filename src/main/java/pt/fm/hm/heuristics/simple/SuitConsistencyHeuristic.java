package pt.fm.hm.heuristics.simple;

import pt.fm.hm.heuristics.base.SolvedColumnsMoreWeightHeuristic;
import pt.fm.hm.model.SolitaireCol;

import java.util.List;

import static pt.fm.hm.model.Solitaire.getKind;

public class SuitConsistencyHeuristic extends SolvedColumnsMoreWeightHeuristic {

    @Override
    public double calculate(SolitaireCol column) {
        if(column.isSolved())
            return 0;

        List<String> cards = column.getCards();
        double tot = 0;
        for(int i = 0; i < cards.size()-1; i++) {
            String c1 = cards.get(i);
            String c2 = cards.get(i+1);
            boolean sameKind = getKind(c1).equals(getKind(c2));
            tot += sameKind ? 0 : (cards.size()-i);
        }
        double h = tot;
        column.setHeuristic(h);
        return h;
    }
}
