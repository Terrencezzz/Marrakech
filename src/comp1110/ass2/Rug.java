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


    private List<IntPair> bluePiece;
    private List<IntPair> redPiece;
    private List<IntPair> yellowPiece;
    private List<IntPair> brownPiece;
    private List<IntPair> position;
    private List<List<IntPair>> combinePosition;

    public int blueRugNumber = 0;
    public int redRugNumber = 0;
    public int yellowRugNumber = 0;
    public int brownRugNumber = 0;


    public Rug(String rugType, List<IntPair> position){
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
    public List<IntPair> replacePosition(){
        return position;
    }


    /**
     * This function aims to combine the same rugs next to each other in to a whole piece.
     * You can use the length of one of the element in this list to calculate the money that one player
     * needs to pay for another.(This is not done yet!!!)
     * @return
     */
    public List<List<IntPair>> combinePosition(){
        return combinePosition;
    }
}
