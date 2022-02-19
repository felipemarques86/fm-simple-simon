package pt.fm.hm.heuristics.base;

import pt.fm.hm.model.Solitaire;
import pt.fm.hm.model.SolitaireCol;

public abstract class Heuristic {

    private boolean allowEmpty = false;
    private boolean allowSolved = false;
    private double defaultValue = 0.0d;

    public double calculate(Solitaire solitaire) {
        Double result = solitaire.getHeuristic();
        if(result == null) {
            result = solitaire.getColumns().stream().filter(this::filter).map(this::calculate).reduce((c1, c2) -> c1 + c2).orElse(defaultValue);
            solitaire.setHeuristic(result);
        }
        return result;
    }

    private boolean filter(SolitaireCol col) {
        if(!allowSolved && col.isSolved()) {
            return false;
        }
        if(!allowEmpty && col.isEmpty()){
            return false;
        }
        if(allowEmpty && !col.isEmpty()){
            return true;
        }
        if(allowSolved && !col.isSolved()) {
            return true;
        }
        return true;
    }

    public abstract double calculate(SolitaireCol column);
}
