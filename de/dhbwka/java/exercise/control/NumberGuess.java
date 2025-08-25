package de.dhbwka.java.exercise.control;

import java.util.Scanner;

public class NumberGuess {
    public static void main(String[] args) {
        boolean correctGuess = false;
        System.out.print("Wie ist dein Name? ");
        int versuch = 1;
        int ranNumber = (int)(Math.random() * 100 + 1);
        Scanner nameQuestion = new Scanner(System.in);
        String name = nameQuestion.next();
        while(correctGuess == false) {
            System.out.printf("Versuch %d: %s, rate eine Zahl [1-100]: ", versuch, name);
            Scanner numberQuestion = new Scanner(System.in);
            int number = numberQuestion.nextInt();
            if(number == ranNumber) {
                correctGuess = true;
                System.out.print(" ist korrekt.");
                break;
            } else if(number <= ranNumber) {
                System.out.print(" ist zu niedrig. ");

            } else {
                System.out.print(" ist zu hoch. ");
            }
            boolean finished = false;
            while(!finished) {
                System.out.println("Was möchtest du tun?");
                System.out.println("0 - Das Spiel beenden");
                System.out.print("1 - Das Spiel fortsetzen ");
                Scanner nextStepsQuestion = new Scanner(System.in);
                int answer = nextStepsQuestion.nextInt();
                if( answer == 0) {
                    finished = true;
                    correctGuess = true;
                } else if(answer == 1) {
                    finished = true;
                    break;
                } else {
                   System.out.println("ungültige Eingabe");
                }
            }
           
            versuch ++;
        }   
    }
}
