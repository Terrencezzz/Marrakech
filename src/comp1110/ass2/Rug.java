package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

/**
 * This class aims to record the status for every players' rugs.
 */
public class Rug {
    private final int MAX_RUG_NUMBER = 15;
    private final String rugType1 = "Blue";
    private final String rugType2 = "Red";
    private final String rugType3 = "Yellow";
    private final String rugType4 = "Brown";
    private String rugType;


    private final String[] twoPeople = {"Blue","Red"};
    private final String[] threePeople = {"Blue","Red","Yellow"};
    private final String[] fourPeople = {"Blue","Red","Yellow","Brown"};
    public String[] playerNumber;


    private List<List<IntPair>> bluePiece;
    private List<List<IntPair>> redPiece;
    private List<List<IntPair>> yellowPiece;
    private List<List<IntPair>> brownPiece;
    private List<List<IntPair>> position;

    public int blueRugNumber = 0;
    public int redRugNumber = 0;
    public int yellowRugNumber = 0;
    public int brownRugNumber = 0;


    public Rug(String rugType, List<List<IntPair>> position){
        this.rugType = rugType;
        this.position = position;
    }


    /**
     * A getter method for rugType.
     * @return
     */
    public String getRugType(){
        return rugType;
    }


    /**
     * If your rug cover other's rug, use this function to delete other's.(This is not done yet!!!)
     * @return
     */
    public List<List<IntPair>> deletePosition(){
        return position;
    }

}
