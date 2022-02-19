package pt.fm.hm.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SolitaireCol {
    private List<String> cards = new ArrayList<>();
    public static final List<String> DECK = new ArrayList<>();
    private double heuristic;
    private int col;

    static {
        DECK.add("RP");
        DECK.add("DP");
        DECK.add("VP");
        DECK.add("10P");
        DECK.add("9P");
        DECK.add("8P");
        DECK.add("7P");
        DECK.add("6P");
        DECK.add("5P");
        DECK.add("4P");
        DECK.add("3P");
        DECK.add("2P");
        DECK.add("AP");

        DECK.add("RE");
        DECK.add("DE");
        DECK.add("VE");
        DECK.add("10E");
        DECK.add("9E");
        DECK.add("8E");
        DECK.add("7E");
        DECK.add("6E");
        DECK.add("5E");
        DECK.add("4E");
        DECK.add("3E");
        DECK.add("2E");
        DECK.add("AE");

        DECK.add("RC");
        DECK.add("DC");
        DECK.add("VC");
        DECK.add("10C");
        DECK.add("9C");
        DECK.add("8C");
        DECK.add("7C");
        DECK.add("6C");
        DECK.add("5C");
        DECK.add("4C");
        DECK.add("3C");
        DECK.add("2C");
        DECK.add("AC");

        DECK.add("RO");
        DECK.add("DO");
        DECK.add("VO");
        DECK.add("10O");
        DECK.add("9O");
        DECK.add("8O");
        DECK.add("7O");
        DECK.add("6O");
        DECK.add("5O");
        DECK.add("4O");
        DECK.add("3O");
        DECK.add("2O");
        DECK.add("AO");
    }




    public SolitaireCol(int col) {
        this.col = col;
    }

    public void addCard(String card) {
        if(DECK.contains(card)){
            cards.add(card);
        }
    }



    public void addCards(List<String> cards) {
        cards.forEach(this::addCard);
    }

    public List<String> getCards() {
        return cards;
    }

    public void setCards(List<String> cards) {
        this.cards = cards;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(String card : cards) {
            sb.append(card);
        }
        return sb.toString();
    }

    public String display() {
        StringBuilder sb = new StringBuilder();
        for(String card : cards) {
            sb.append(card);
            sb.append(" ");
        }
        if(getHeuristic() > 0) {
            sb.append("(");
            sb.append(getHeuristic());
            sb.append(")");
        }
        return sb.toString();
    }

    public List<String> from(String card) {
        int i = 0;
        for(; i < cards.size(); i++) {
            if(card.equals(cards.get(i))) {
                break;
            }
        }
        List<String> cardsSubList = cards.subList(i, cards.size());
        if(i>0){
            String cardBeforeSubList = cards.get(i-1);
            if(Solitaire.inSequenceSameKind(Arrays.asList(cardBeforeSubList, cardsSubList.get(0)))) {
                return null;
            }
        }
        return cardsSubList;
    }

    public boolean hasCard(String card) {
        return cards.contains(card);
    }

    public boolean remove(List<String> range) {
        return cards.removeAll(range);
    }

    public boolean add(List<String> range) {
        if(cards.isEmpty()){
            cards.addAll(range);
            return true;
        }
        if(Solitaire.inSequence(Arrays.asList(cards.get(cards.size()-1), range.get(0)))){
            cards.addAll(range);
            return true;
        }
        return false;
    }

    public boolean canAdd(List<String> range) {
        if(cards.isEmpty()){
            return true;
        }
        if(Solitaire.inSequence(Arrays.asList(cards.get(cards.size()-1), range.get(0)))){
            return true;
        }
        return false;
    }


    public SolitaireCol clone() {
        SolitaireCol col = new SolitaireCol(this.col);
        col.getCards().addAll(this.cards);
        return col;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolitaireCol that = (SolitaireCol) o;
        return Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    public int getCol() {
        return col;
    }

    public boolean isSolved() {
        String toString = toString().trim();
        return toString.equals("RPDPVP10P9P8P7P6P5P4P3P2PAP") ||
                toString.equals("REDEVE10E9E8E7E6E5E4E3E2EAE") ||
                toString.equals("RODOVO10O9O8O7O6O5O4O3O2OAO") ||
                toString.equals("RCDCVC10C9C8C7C6C5C4C3C2CAC");
    }

    public double cardsDistance() {
        return cards.size() / 13.0d;
    }

    public double sequenceDistance() {
        double tot = 0;
        for(int i = 0; i < cards.size()-1; i++) {
            String c1 = cards.get(i);
            String c2 = cards.get(i+1);
            int pos = i + 1;
            double cv = Solitaire.getCardValue(cards.get(i));
            double dist = Math.abs(cv - pos) + 1;
            tot += dist;

        }
        tot += Math.abs(Solitaire.getCardValue(cards.get(cards.size()-1)) - cards.size());
        return tot;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public double getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(double heuristic) {
        this.heuristic = heuristic;
    }
}
