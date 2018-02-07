/*(Game: bean machine) The bean machine, also known as a quincunx or the Galton box, is a device for statistics experiments
named after English scientist Sir Francis Galton. It consists of an upright board with evenly spaced nails (or pegs)
in a triangular form.Balls are dropped from the opening of the board. Every time a ball hits a nail, it
has a 50% chance of falling to the left or to the right. The piles of balls are accumulated in the slots at the bottom
of the board. Write a program that simulates the bean machine. Your program should prompt the user to enter the number
of the balls and the number of the slots in the machine. Simulate the falling of each ball by printing its path.
Display the final buildup of the balls in the slots in a histogram. Here is a sample run of the program:

Enter the number of balls to drop: 5
Enter the number of slots in the bean machine: 8
LRLRLRR - 4
RRLLLRR - 4
LLRLLRR - 3
RRLLLLL - 2
LRLRRLR - 4

----O---
----O---
--OOO---                   */

package Various;
import java.util.Scanner;
public class BeanMachine {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // prompt number of balls to drop
        System.out.print("Enter number of balls to drop: ");
        int numberOfBalls = input.nextInt();

        // prompt number of slots
        System.out.print("Enter number of slots in bean machine: ");
        int numberOfSlots = input.nextInt();
        System.out.println("------------------------------");

        // run the bean machine
        runBeanMachine(numberOfBalls, numberOfSlots);
    }

    /** Run game */
    private static void runBeanMachine(int numberOfBalls, int numberOfSlots){
        // initialize machine with provided number of slots
        int[] slots = new int[numberOfSlots];

        // run balls and get path for each ball
        for (int i = 0; i < numberOfBalls; i++) {
            StringBuilder currentPath = getPath(numberOfSlots); // get path for ball
            int slotNumber = getSlotNumber(currentPath);      // get slot number that ball dropped into
            System.out.println(currentPath + " - slot " + slotNumber);
                /* additional check - if ball falls to right more times
                then number of slots, if true increase counter of last slot
                 */
                if (slotNumber >= numberOfSlots){
                    slots[numberOfSlots - 1]++;
                } else {
                    slots[slotNumber]++;
                }
        }

        // print summary for slots - how many balls in each one
        System.out.println("------------------------------");
        printSummary(slots);
        System.out.println("------------------------------");
        System.out.println("Histogram of results:\n");

        // print histogram
        printHistogram(slots);
    }

    /** Returns direction of ball falling (L - left, R - right) */
    private static String getDirection(){
        double randomNum;
        randomNum = (Math.random());
        if (randomNum <= 0.5){
            return "L";
        }
        return "R";
    }

    /** Returns whole path of ball's falling */
    private static StringBuilder getPath(int numberOfSlots){
        StringBuilder path = new StringBuilder();
        String singleHit;
        for (int i = 0; i < numberOfSlots - 1; i++) {
            singleHit = getDirection();
            path.append(singleHit);
        }
        return path;
    }

    /** Returns slot's number that ball drops in */
    private static int getSlotNumber(StringBuilder path){
        int slotNumber = 0;
        for (int i = 0; i < path.length(); i++) {
             if(path.charAt(i) == 'R'){
                 slotNumber++;
             }
        }
        return slotNumber;
    }

    /** Print histogram of results */
    private static void printHistogram(int[] slots){
        int numberOfRows = getBiggestStack(slots);
        int maxStack = getBiggestStack(slots);
        // loop through number of rows
        for (int i = 0; i <= numberOfRows - 1; i++) {
            // loop through number of slots
            for (int j = 0; j <= slots.length - 1; j++) {
                // for each iteration print if balls number reaches top of stack
                if(slots[j] < maxStack){
                    System.out.print("-");
                } else if(slots[j] >= maxStack) {
                    System.out.print("O");
                }
            }
            maxStack--;             // need to decrease stack for each iteration
            System.out.println(""); // go to next line
        }
    }

    /** Prints summary for slots - how many balls in each one */
    private static void printSummary(int[] slots){
        for (int i = 0; i < slots.length; i++) {
            System.out.println("Slot " + i + " - " + slots[i] + " balls");
        }
    }

    /** Find biggest stack of balls */
    private static int getBiggestStack(int[] slots){
        int max = slots[0];
        for (int i = 0; i < slots.length - 1; i++) {
            if(slots[i + 1] > max){
                max = slots[i+1];
            }
        }
        return max;
    }
}
