package sailscore;

/**
 * Created by Refi on 22.03.2017.
 */
public class Main {

    public static void main(String  args[]){
        Regatta konkurranse = new Regatta();
        Boat ronnyboat = new Boat(42, "Dagros");
        Boat ivarboat = new Boat(142, "D11111");

        konkurranse.addCompetitor(ronnyboat);
        konkurranse.addCompetitor(ivarboat);

        konkurranse.prepareNewRace();

        konkurranse.registerFinish(ronnyboat);
        konkurranse.registerFinish(ivarboat);
        konkurranse.endRace();
        konkurranse.printResults();

    }
}
