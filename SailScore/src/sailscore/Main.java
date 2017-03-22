package sailscore;

/**
 * Created by Refi on 22.03.2017.
 */
public class Main {

    public static void main(String  args[]){
        Regatta konkurranse = new Regatta();
        Boat ronnyboat = new Boat(42, "Dagros");
        Boat ivarboat = new Boat(142, "D11111");
        Boat xc = new Boat(00, "Uflaks");


        konkurranse.addCompetitor(ronnyboat);
        konkurranse.addCompetitor(ivarboat);
        konkurranse.addCompetitor(xc);

        konkurranse.prepareNewRace();

        konkurranse.registerFinish(ronnyboat);
        konkurranse.registerFinish(ivarboat);
        konkurranse.registerFinish(xc);
        konkurranse.endRace();
        konkurranse.printResults();

    }
}
