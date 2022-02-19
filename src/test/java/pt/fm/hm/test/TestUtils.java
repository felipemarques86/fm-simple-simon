package pt.fm.hm.test;

import pt.fm.hm.heuristics.base.Heuristic;
import pt.fm.hm.model.Solitaire;
import pt.fm.hm.search.AStarSearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUtils {
    static void run(int maxIter, Solitaire initial, Heuristic heuristics, String fileName) {
        System.out.println("Max Iter = " + maxIter);
        System.out.println("H = " + heuristics.calculate(initial));
        System.out.println(initial);

        AStarSearch search = new AStarSearch(maxIter, heuristics);
        Solitaire sol = search.search(initial);

        System.out.println("Open List Size: " + search.getOpenList().size());
        System.out.println("Closed List Size: " + search.getClosedList().size());

        assertTrue(sol.isSolved());

        List<Solitaire> path = new ArrayList<>();
        Solitaire it = sol;
        while(it.getParent() != null) {
            path.add(it.getParent());
            it = it.getParent();
        }
        int i = 0;

        Collections.reverse(path);
        for(Solitaire solitaire : path) {
            System.out.println("================ Movement " + (i++) +  " ==================" );
            System.out.println(solitaire);
            System.out.println("H = " + heuristics.calculate(solitaire));
            System.out.println("================================================");
        }
        System.out.println("================ Final Movement ===================" );
        System.out.println(sol);
        System.out.println("H = " + heuristics.calculate(sol));
        System.out.println("Total Iter = " + search.getIterations());
        System.out.println("================================================");
    }

    static Solitaire getProposedProblem() {
        Solitaire initial = new Solitaire();
        initial.addColumn("10C,4E,2C,8C,8O,10P");
        initial.addColumn("VO,3O,7O");
        initial.addColumn("9E,AE,AO,5E,4C,DO,RC");
        initial.addColumn("4P,VP,5C,9C,8P,VC");
        initial.addColumn("6E,3E,AC,4O,8E,10E,10O");
        initial.addColumn("2O,6C,AP,2E,3C,6O");
        initial.addColumn("DP,7E,DC,2P,7C,RO,RP,DE");
        initial.addColumn("5P,5O");
        initial.addColumn("3P,9P,RE,6P");
        initial.addColumn("7P,9O,VE");
        return initial;
    }

    static Solitaire getGoalSolution() {
        Solitaire initial = new Solitaire();
        initial.addColumn("");
        initial.addColumn("");
        initial.addColumn("RC", "DC", "VC", "10C", "9C", "8C", "7C", "6C", "5C", "4C", "3C", "2C", "AC");
        initial.addColumn("RO", "DO", "VO", "10O", "9O", "8O", "7O", "6O", "5O", "4O", "3O", "2O", "AO");
        initial.addColumn("");
        initial.addColumn("RP", "DP", "VP", "10P", "9P", "8P", "7P", "6P", "5P", "4P", "3P", "2P", "AP");
        initial.addColumn("");
        initial.addColumn("RE", "DE", "VE", "10E", "9E", "8E", "7E", "6E", "5E", "4E", "3E", "2E", "AE");
        initial.addColumn("");
        initial.addColumn("");
        return initial;
    }

    static Solitaire getCloseToGoalSolution1() {
        Solitaire initial = new Solitaire();
        initial.addColumn("");
        initial.addColumn("RC", "DC", "VC");
        initial.addColumn("10C", "9C", "8C", "7C", "6C", "5C", "4C", "3C", "2C", "AC");
        initial.addColumn("RO", "DO", "VO", "10O", "9O", "8O", "7O", "6O", "5O", "4O", "3O", "2O", "AO");
        initial.addColumn("");
        initial.addColumn("RP", "DP", "VP", "10P", "9P", "8P", "7P", "6P", "5P", "4P", "3P", "2P", "AP");
        initial.addColumn("");
        initial.addColumn("RE", "DE", "VE", "8E", "7E", "6E", "5E", "4E", "3E", "2E", "AE");
        initial.addColumn("10E", "9E");
        initial.addColumn("");
        return initial;
    }

    static Solitaire getCloseToGoalSolution2() {
        Solitaire initial = new Solitaire();
        initial.addColumn("3O", "2O", "AO");
        initial.addColumn("RC", "DC", "VC");
        initial.addColumn("10C", "9C",  "5C", "4C", "3C", "2C", "AC");
        initial.addColumn("RO", "DO", "VO", "10O", "9O", "8O", "7O", "6O", "5O", "4O");
        initial.addColumn("8C", "7C", "6C");
        initial.addColumn("RP", "DP", "VP", "10P", "9P", "8P", "7P", "3P", "2P", "AP");
        initial.addColumn("6P", "5P", "4P");
        initial.addColumn("8E", "7E", "6E", "5E", "4E", "3E", "2E", "AE");
        initial.addColumn("10E", "9E");
        initial.addColumn("RE", "DE", "VE");
        return initial;
    }

    static Solitaire getCloseToGoalSolution3() {
        Solitaire initial = new Solitaire();
        initial.addColumn("AO", "2O", "3P");
        initial.addColumn("RC", "DC", "VC");
        initial.addColumn("10C", "9C",  "5C", "4C", "3C", "2C", "AC");
        initial.addColumn("RO", "DO", "VO", "10O", "9O", "8O", "7O", "6O", "5O", "4O");
        initial.addColumn("8C", "7C", "6C");
        initial.addColumn("RP", "DP", "VP", "10P", "9P", "8P", "7P", "3O", "2P", "AP");
        initial.addColumn("6P", "5P", "4P");
        initial.addColumn("8E", "7E", "6E", "5E", "4E", "3E", "2E", "AE");
        initial.addColumn("10E", "9E");
        initial.addColumn("RE", "DE", "VE");
        return initial;
    }

    static Solitaire getCloseToGoalSolution4() {
        Solitaire initial = new Solitaire();
        initial.addColumn("AO", "2O", "3P");
        initial.addColumn("RC", "DC", "5C", "4C","VC");
        initial.addColumn("10C", "9C", "3C", "2C", "AC");
        initial.addColumn( "AP", "RO", "DO", "VO", "10O", "9O", "8O", "7O", "6O", "5O", "4O");
        initial.addColumn("10P","8C", "7C", "6C");
        initial.addColumn("RP", "DP", "VP", "7P", "3O", "2P", "9P", "8P");
        initial.addColumn("6P", "5P", "4P");
        initial.addColumn("8E", "7E", "6E", "5E", "4E", "3E", "2E", "AE");
        initial.addColumn("10E", "9E");
        initial.addColumn("RE", "DE", "VE");
        return initial;
    }

    static Solitaire getCloseToGoalSolution5() {
        Solitaire initial = new Solitaire();
        initial.addColumn("AO", "2O", "3P");
        initial.addColumn("RC", "DC", "5C", "4C","VC");
        initial.addColumn("10C", "9C", "3C", "2C", "AC");
        initial.addColumn( "AP", "RO", "DO", "VO", "10O", "9O", "8O", "7O","4O","5O");
        initial.addColumn("10P","8C", "7C", "6C");
        initial.addColumn("RP", "DP", "VP", "7P", "4E", "3E","3O", "2P", "9P", "6O", "8P");
        initial.addColumn("6P", "5P", "4P");
        initial.addColumn("8E", "7E", "6E", "5E", "2E", "AE");
        initial.addColumn("10E", "9E");
        initial.addColumn("RE", "VE", "DE");
        return initial;
    }

    static Solitaire getCloseToGoalSolution6() {
        Solitaire initial = new Solitaire();
        initial.addColumn("AO", "2O", "3P");
        initial.addColumn("RC", "DC", "4C","5C","VC");
        initial.addColumn("10C","3C",  "9C", "AC", "2C");
        initial.addColumn( "AP", "RO",  "7O", "10O", "9O", "VO", "DO", "8O","4O","5O");
        initial.addColumn("10P", "7C", "8C", "6C");
        initial.addColumn("DP", "VP", "RP", "7P", "4E", "3E","3O", "2P", "9P", "6O", "8P");
        initial.addColumn("6P", "5P", "4P");
        initial.addColumn("AE", "8E", "7E", "6E", "5E", "2E");
        initial.addColumn("10E", "9E");
        initial.addColumn("RE", "VE", "DE");
        return initial;
    }
}
