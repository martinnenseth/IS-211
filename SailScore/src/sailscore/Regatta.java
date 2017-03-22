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


    public void addCompetitor(Boat c) {
        // oppgave 1
        competitors.add(c);
    }

    private void printCompetitors() {
        System.out.format("%15s%15s%15s%15s\n", "Båtnavn", "Seilnr", "Plasseringer", "Sum");
        for (Boat competitor : competitors){
            System.out.format("%15s%15d%15d%15s\n", competitor.getName(), competitor.getSailNo(), competitor.getScore(), competitor.getPlacementsResults());
        }
    }

    public void prepareNewRace() {
        this.currentRace ++;
    }

    public void registerFinish(Boat competitor) {
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
        competitor.addPlacement(this.currentRace, this.raceFinish);
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


    private void sortResult() {
        //Sort all elements in the list from lowest to highest - based on the element's
        for (Boat competitor : competitors) {
                int sum = 0;
                // As long as i is less than the size of the placements list, iterate
            for (int i = 0; i < competitor.getPlacements().size(); i++){
                    sum = sum + competitor.getPlacements().get(i).hashCode();
                    competitor.setScore(sum);
            }
        }
    }

    public void printResults(){
        sortResult();
        competitors.sort(Comparator.comparing(Boat::getScore));
        printCompetitors();
    }


    /**
     * Skriver ut resultattabellen fra oppgaven
     *
     * @param args the command line arguments
     */
    /**
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
    } */
}
