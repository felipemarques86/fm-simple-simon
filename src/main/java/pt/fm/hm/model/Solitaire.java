package pt.fm.hm.model;

import java.util.*;

public class Solitaire {
    private List<SolitaireCol> columns = new ArrayList<>();
    private String movement = "";
    private int fCol = -1;
    private double cost = 0;
    private Solitaire parent;
    private Double heuristic;
    private boolean hasCost = false;

    public SolitaireCol addColumn(){
        SolitaireCol solCol = new SolitaireCol(columns.size());
        columns.add(solCol);
        return solCol;
    }

    public Double getHeuristic() {
        return heuristic;
    }

    public double setHeuristic(Double heuristic) {
        this.heuristic = heuristic;
        return this.heuristic;
    }

    public SolitaireCol addColumn(String values){
        SolitaireCol solitaireCol = this.addColumn();
        solitaireCol.addCards(Arrays.asList(values.split(",")));
        return solitaireCol;
    }

    public SolitaireCol addColumn(String ... values){
        SolitaireCol solitaireCol = this.addColumn();
        solitaireCol.addCards(Arrays.asList(values));
        return solitaireCol;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("m");
        sb.append("("); sb.append(fCol+1); sb.append("): ");
        sb.append(movement);
        sb.append("\n");
        sb.append("c:"); sb.append(cost); sb.append("\n");
        for(int i = 0; i < columns.size(); i++) {
            if(!columns.get(i).getCards().isEmpty()) {
                sb.append(i + 1);
                sb.append(": ");
                sb.append(columns.get(i).display());
                if(columns.get(i).isSolved())
                    sb.append("*");
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public boolean move(String m) {
        String [] ss = m.split(";");
        String card = ss[0];
        int col = Integer.parseInt(ss[1]) - 1;
        SolitaireCol fromCol = columns.stream().filter(c -> c.hasCard(card)).findFirst().orElse(null);
        if(fromCol == null || fromCol.isSolved()) {
            return false;
        }

        // pode mover?
        List<String> range = fromCol.from(card);
        if(range != null && !columns.get(col).isSolved() && inSequenceSameKind(range)) {
            boolean ret = columns.get(col).add(range);
            if(ret)
                fromCol.remove(range);
            fCol = fromCol.getCol();
            return ret;
        }
        return false;
    }


    public boolean canMove(String m) {
        String [] ss = m.split(";");
        String card = ss[0];
        int col = Integer.parseInt(ss[1]) - 1;
        SolitaireCol fromCol = columns.stream().filter(c -> c.hasCard(card)).findFirst().orElse(null);
        if(fromCol == null || fromCol.isSolved()) {
            return false;
        }

        List<String> range = fromCol.from(card);
        if(range == null || (fromCol.getCards().size() == range.size() && columns.get(col).isEmpty())) {
            return false;
        }

        if(!columns.get(col).isSolved() && inSequenceSameKind(range)) {
            return columns.get(col).canAdd(range);
        }
        return false;
    }

    // UTIL

    public static int getCardValue(String card) {
        return Integer.parseInt(card.replace("P", "").replace("E", "").replace("O", "")
                .replace("C", "").replace("A", "1").replace("V", "11")
                .replace("D", "12").replace("R", "13"));
    }

    public static String getKind(String card) {
        return card.substring(card.length()-1);
    }


    public static boolean inSequenceSameKind(List<String> cards) {
        if(cards.isEmpty())
            return false;

        if(cards.size() == 1) {
            return true;
        }

        for(int i = 0; i < cards.size() - 1; i++) {
            String c1 = cards.get(i);
            String c2 = cards.get(i+1);
            if(getCardValue(c1) != getCardValue(c2) + 1) {
                return false;
            }
            if(!getKind(c1).equals(getKind(c2))) {
                return false;
            }
        }
        return true;
    }

    public static boolean inSequence(List<String> cards) {

        if(cards.isEmpty())
            return false;

        if(cards.size() == 1) {
            return true;
        }

        for(int i = 0; i < cards.size() - 1; i++) {
            String c1 = cards.get(i);
            String c2 = cards.get(i+1);
            if(getCardValue(c1)  != getCardValue(c2) +1) {
                return false;
            }
        }
        return true;
    }

    public static boolean sameKind(List<String> cards) {
        for(int i = 0; i < cards.size() - 1; i++) {
            String c1 = cards.get(i);
            String c2 = cards.get(i+1);
            if(!getKind(c1).equals(getKind(c2))) {
                return false;
            }
        }
        return true;
    }

    public Solitaire clone() {
        Solitaire sol = new Solitaire();
        for(SolitaireCol col : columns) {
            sol.columns.add(col.clone());
        }
        return sol;
    }

    public List<Solitaire> successors() {
        Set<Solitaire> ret = new HashSet<>();
        Map<Integer, Integer> selectedMovements = new HashMap<>();
        for(int i = 0; i < columns.size(); i++) {
            SolitaireCol col = columns.get(i);
            for(String card : col.getCards()) {
                for(int j = 0; j < columns.size(); j++) {
                    if(i!=j) {
                        Solitaire clone = this.clone();
                        if(clone.move(card + ";" + (j+1))){
                            clone.movement = card + ";" + (j+1);
                            ret.add(clone);
                            selectedMovements.put(i, j);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(ret);
    }

    public List<String> successorMovements() {
        Set<String> ret = new HashSet<>();
        for(int i = 0; i < columns.size(); i++) {
            SolitaireCol col = columns.get(i);
            for(String card : col.getCards()) {
                for(int j = 0; j < columns.size(); j++) {
                    if(i!=j) {
                        if(this.canMove(card + ";" + (j+1))){
                            ret.add(card + ";" + (j+1));
                        }
                    }
                }
            }
        }
        return new ArrayList<>(ret);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solitaire solitaire = (Solitaire) o;
        return Objects.deepEquals(columns, solitaire.columns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(columns);
    }

    public boolean isSolved() {
        for(SolitaireCol  c: columns) {
            if(c.isEmpty())
                continue;

            if(!c.isSolved()) {
                return false;
            }
        }
        return true;
    }

    public double getCost() {
        return hasCost ? cost : Double.MAX_VALUE;
    }

    public void setCost(double cost) {
        this.cost = cost;
        this.hasCost = true;
    }

    public void addFullSolution(String s) {
        for(String row : s.split("\n")) {
            addColumn(row.trim().split(":")[1].replace(" ", ","));
        }
    }

    public List<SolitaireCol> getColumns() {
        return columns;
    }

    public Solitaire getParent() {
        return parent;
    }

    public void setParent(Solitaire parent) {
        this.parent = parent;
    }

    public String getMovement() {
        return movement;
    }

    public long getSolvedColumns() {
        return getColumns().stream().filter( s -> s.isSolved()).count();
    }
}
