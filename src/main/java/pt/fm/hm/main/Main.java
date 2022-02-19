package pt.fm.hm.main;

import pt.fm.hm.model.Solitaire;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String ... args) {
        Stack<String> movements = new Stack<>();
        Stack<Solitaire> steps = new Stack<>();

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

        boolean terminate = false;
        do {

            Scanner scanner = new Scanner(System.in);
            System.out.println(initial);
            System.out.println("["+ Arrays.toString(initial.successors().stream().map(s -> s.getMovement()).toArray())+"]");
            System.out.print("Movement: ");
            String movement = scanner.nextLine();

            try {
                if (movement == null || movement.equals("") || movement.equalsIgnoreCase("exit")) {
                    terminate = true;
                } else if(movement.equals("undo")) {
                    initial = steps.pop();
                    movements.pop();
                } else if (!initial.move(movement)) {
                    System.err.println("Illegal movement: " + movement);
                } else {
                    System.out.println("Movement: " + movement);
                    System.out.println(initial);
                    steps.add(initial);
                    movements.add(movement);
                    if (initial.isSolved()) {
                        System.out.println("Problem solved!");
                        terminate = true;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } while(!terminate);

        System.out.println("***************************************");
        System.out.println("Movements: ");
        for(String mov : movements) {
            System.out.println(mov);
        }



    }
}
