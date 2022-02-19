package pt.fm.hm.search;

import org.apache.commons.lang3.time.StopWatch;
import pt.fm.hm.model.Solitaire;
import pt.fm.hm.heuristics.base.Heuristic;
import pt.fm.hm.model.SolitaireCol;

import java.util.*;

public class AStarSearch {

    private static final boolean FILTER = true;
    private static final long LIMIT = 120000;
    private final Heuristic heuristics;
    private int maxIter;
    private PriorityQueue<Solitaire> openList;
    private Set<Solitaire> closedList = new HashSet<>();
    private int  iterations = 0;

    public AStarSearch(int maxIter, Heuristic heuristics) {
        this.maxIter = maxIter;
        this.heuristics = heuristics;
        openList = new PriorityQueue<>(10, (o1, o2) -> {
            if((heuristics.calculate(o1) + o1.getCost()) < (heuristics.calculate(o2)  + o2.getCost())) {
                return -1;
            } else if((heuristics.calculate(o1)  + o1.getCost()) > (heuristics.calculate(o2)  + o2.getCost())) {
                return 1;
            }
            return 0;
        });
    }

    public Solitaire search(Solitaire initial) {

        StopWatch watch = new StopWatch();
        watch.start();

        initial.setCost(0.0d);

        openList.add(initial);

        while(!openList.isEmpty()) {
            iterations++;
            Solitaire current = openList.peek();

            if(current.isSolved()) {
                watch.stop();
                System.out.println("Problem solved! Time elapsed: " + watch.getTime() + "ms");
                return current;
            }

            if(maxIter > 0 && iterations >= maxIter){
                watch.stop();
                System.out.println("Max iterations reached: " + maxIter + " - Time elapsed: " + watch.getTime() + "ms");
                return current;
            }

            if(watch.getTime() > LIMIT) {
                watch.stop();
                System.out.println("Timeout! Time elapsed: " + watch.getTime() + "ms");
                return current;
            }


            List<String> successorMovements = current.successorMovements();
            Set<Solitaire> successors = filterMovesAndGenerateSolutions(successorMovements, current);
            for(Solitaire successor : successors) {
                double successorEvaluation = heuristics.calculate(successor) + current.getCost() + 1;

                if(!openList.contains(successor) && !closedList.contains(successor)) {
                    successor.setParent(current);
                    successor.setCost(current.getCost() + 1);
                    openList.add(successor);
                } else {
                    if(successorEvaluation < successor.getCost() ) {
                        successor.setParent(current);
                        successor.setCost(current.getCost() + 1);

                        if(closedList.contains(successor)) {
                            closedList.remove(successor);
                            openList.add(successor);
                        }
                    }
                }

            }

            openList.remove(current);
            closedList.add(current);
        }

        return null;
    }

    private Set<Solitaire> filterMovesAndGenerateSolutions(List<String> moves, Solitaire current) {
        if(!FILTER)
            return move(moves, current);
        Set<Solitaire> excellent = new HashSet<>();
        Set<Solitaire> good = new HashSet<>();
        Set<Solitaire> average = new HashSet<>();
        for(String move :moves) {
            String [] moveSpl = move.split(";");
            SolitaireCol solitaireCol = current.getColumns().get(Integer.parseInt(moveSpl[1]) - 1);
            Solitaire sol = current.clone();
            long solvedCols = current.getSolvedColumns();
            sol.move(move);
            if (sol.isSolved() || solvedCols < sol.getSolvedColumns() || solitaireCol.isEmpty()) {
                excellent.add(sol);
            } else {

                if(Solitaire.sameKind(solitaireCol.getCards()) && !Solitaire.sameKind(sol.getColumns().get(Integer.parseInt(moveSpl[1])-1).getCards()) ){
                    average.add(sol);
                } else {
                    String lastCard = solitaireCol.getCards().get(solitaireCol.getCards().size() - 1);
                    if (Solitaire.inSequenceSameKind(Arrays.asList(lastCard, moveSpl[0]))) {
                        good.add(sol);
                    } else {
                        average.add(sol);
                    }
                }
            }
        }
        return excellent.isEmpty() ? (good.isEmpty() ? average : good) : excellent;
    }

    private Set<Solitaire> move(List<String> moves, Solitaire current) {
        Set<Solitaire> ret = new HashSet<>();
        for(String move :moves) {
            Solitaire clone = current.clone();
            if(clone.move(move)) {
                ret.add(clone);
            }
        }
        return ret;
    }

    public int getMaxIter() {
        return maxIter;
    }

    public PriorityQueue<Solitaire> getOpenList() {
        return openList;
    }

    public Set<Solitaire> getClosedList() {
        return closedList;
    }

    public int getIterations() {
        return iterations;
    }
}
