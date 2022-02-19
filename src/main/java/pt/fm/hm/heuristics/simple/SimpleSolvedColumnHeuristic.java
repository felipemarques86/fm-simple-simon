package pt.fm.hm.heuristics.simple;

import pt.fm.hm.heuristics.base.SolvedColumnsMoreWeightHeuristic;
import pt.fm.hm.model.SolitaireCol;

public class SimpleSolvedColumnHeuristic extends SolvedColumnsMoreWeightHeuristic {

    @Override
    public double calculate(SolitaireCol column) {
        double h = column.isSolved() ? 0: 1;
        column.setHeuristic(h);
        return h;
    }
}
