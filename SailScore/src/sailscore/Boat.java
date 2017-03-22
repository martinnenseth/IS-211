/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sailscore;


import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

/**
 *
 * @author even
 */
public class Boat implements Comparable<Boat> {
    // oppgave 1, 2 og 3
    private int sailNo;
    private String name;
    private List <Integer> placements;
    private int score;
    private boolean passedGoal;

    public Boat(int sailNo, String name) {
        // oppgave 1
        this.sailNo = sailNo;
        this.name = name;
        this.placements = new ArrayList<>();
        this.score = 0;
    }

    @Override
    public int compareTo(Boat o) {
        return 0;
    }

    public int getSailNo() {
        return sailNo;
    }

    public void addPlacement(int raceNr, int i){
        placements.add(i);
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String getPlacementsResults(){
        String prefix = "|";
        String infix = "| ";
        String postfix = "|";

        StringJoiner joiner = new StringJoiner(infix, prefix, postfix);
        for (Integer i : placements)
            joiner.add(i.toString());

        return joiner.toString();
    }

    public boolean getPassedGoal() {
        return passedGoal;
    }

    public void setPassedGoal(boolean passedGoal) {
        this.passedGoal = passedGoal;
    }

    public List<Integer> getPlacements() {
        return placements;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPlacements(List<Integer> placements) {
        this.placements = placements;
    }

    public void computeScore(){
        int sum = 0;
        for (int i: placements
             ) {
            sum =+ i;
        }
        this.score = sum;
    }
}
