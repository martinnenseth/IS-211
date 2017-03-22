/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sailscore;


import java.util.*;

/**
 *
 * @author even
 */
public class Regatta {
    private int maxRaces;
    private int currentRace;
    private ArrayList<Boat> competitors;
    private int raceFinish;

    // oppgave 1
    public Regatta() {
        maxRaces = 10;
        currentRace = 0;
        raceFinish = 1;
        competitors = new ArrayList<>();
    }


    private void addCompetitor(Boat c) {
        // oppgave 1
        competitors.add(c);
    }

    private void printCompetitors() {
        for (Boat competitor : competitors){
            competitor.getName();
            System.out.println("|");
            competitor.getSailNo();
            System.out.println("|");
            competitor.getPlacementsResults();
            System.out.println("|");
        }
    }


    private void prepareNewRace() {
        this.currentRace ++;

    }

    private void registerFinish(Boat competitor) {
        // Check that the boat is a competitor
        if (!competitors.contains(competitor)) {
            System.out.println(competitor.getName() + "is not a competitor");
            return;
        }
        // Check that the boat has not passed goal this race.
        if (competitor.getPassedGoal()) {
            System.out.println(competitor.getName() + "has already passed goal");
            return;
        }
        addCompetitor(competitor);
        raceFinish ++;

    }


    // oppgave 2
    public void endRace() {
        //set goalPassed on all elements in the list to false.
        for (Boat competitor : competitors){
            competitor.setPassedGoal(false);
        }
        raceFinish = 1;
        updateScore();
    }

    public void updateScore(){
        for (Boat competitor : competitors){
            competitor.computeScore();
        }

    }


    public void printResults(Boat b) {
        // oppgave 3
        Collection <Boat> b = (Collection<Boat>) getCompetitors();
        System.out.println("==========================================================");
        System.out.println();
        System.out.println("After race: " + (currentRace+1));
        System.out.println();
        System.out.println("==========================================================");
        for (Boat boat: b
             ) {
            System.out.println("Boat Name: " + boat.getName() +" SailNr: " + boat.getSailNo() );
            boat.getPlacementsResults();
            System.out.println(" Score:" + boat.getScore());
            System.out.println("==========================================================");
        }
    }

    private void printResults(){
        //Sort all elements in the list from lowest to highest

    }


    /**
     * Skriver ut resultattabellen fra oppgaven
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Regatta nm = new Regatta();

        nm.addCompetitor(new Boat(42, "Dagros"));
        nm.addCompetitor(new Boat(314, "Pi"));
        nm.addCompetitor(new Boat(13, "Uflaks"));
        nm.addCompetitor(new Boat(999, "Sinka"));

        nm.prepareNewRace();
        nm.registerFinish(314);
        nm.registerFinish(999);
        nm.registerFinish(13);
        nm.registerFinish(42);
        nm.endRace();
        nm.printResults();

        nm.prepareNewRace();
        nm.registerFinish(999);
        nm.registerFinish(314);
        nm.registerFinish(13);
        nm.registerFinish(42);
        nm.endRace();
        nm.printResults();

        nm.prepareNewRace();
        nm.registerFinish(999);
        nm.registerFinish(314);
        nm.registerFinish(42);
        nm.registerFinish(13);
        nm.endRace();
        nm.printResults();

        nm.prepareNewRace();
        nm.registerFinish(314);
        nm.registerFinish(42);
        nm.registerFinish(999);
        nm.registerFinish(13);
        nm.endRace();
        nm.printResults();

        nm.prepareNewRace();
        nm.registerFinish(13);
        nm.registerFinish(999);
        nm.registerFinish(42);
        nm.registerFinish(314);
        nm.endRace();
        nm.printResults();
    }
}
