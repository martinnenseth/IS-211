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
    private HashMap<Integer, Boat> competitors;
    private int raceFinish;

    // oppgave 1
    public Regatta() {
        maxRaces = 10;
        currentRace = 0;
        raceFinish = 1;
        competitors = new HashMap<>();

    }


    public void addCompetitor(Boat c) {
        // oppgave 1
        competitors.put(c.getSailNo(), c);
    }


    public Boat getCompetitor(int sailno) {
        // oppgave 1
        return competitors.get(sailno);
    }


    public Collection<Boat> getCompetitors() {
        ArrayList<Boat> comps = new ArrayList<>();
        Set<Integer> keys = competitors.keySet();
        for (int k : keys){
            comps.add(competitors.get(k));
        }
        return comps;
    }


    public void prepareNewRace() {
        this.currentRace ++;

    }


    public void registerFinish(int sailno) {
        competitors.get(sailno).addPlacement(this.currentRace, this.raceFinish);
        raceFinish ++;
    }


    // oppgave 2
    public void endRace() {
        raceFinish = 1;
        updateScore();
    }

    public void updateScore(){
        Set<Integer> keys = competitors.keySet();
        for (int key: keys
             ) {
            competitors.get(key).computeScore();
        }
    }


    public void printResults() {
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
