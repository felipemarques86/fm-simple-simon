package pt.fm.hm.heuristics.base;

import pt.fm.hm.model.Solitaire;

public abstract class SolvedColumnsMoreWeightHeuristic extends Heuristic {

    @Override
    public double calculate(Solitaire solitaire) {
        double result = super.calculate(solitaire);
        double count = solitaire.getColumns().stream().filter(s -> s.isSolved()).count();
        return result * (10-count) / 10;
    }
}
