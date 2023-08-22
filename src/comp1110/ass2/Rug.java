package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

/**
 * This class aims to record the status for every players' rugs.
 */
public class Rug {
    public List<List<IntPair>> bluePiece;
    public List<List<IntPair>> redPiece;
    public List<List<IntPair>> yellowPiece;
    public List<List<IntPair>> brownPiece;

    // A constructor for Rug to initialize the fields.
    public Rug() {
        this.bluePiece = new ArrayList<>();
        this.redPiece = new ArrayList<>();
        this.yellowPiece = new ArrayList<>();
        this.brownPiece = new ArrayList<>();
    }

    /**
     * This function aims to combine the same rugs next to each other into a whole piece.
     * This function needs to be fleshed out.
     *
     * @param positionList - the list of positions for a specific color
     * @return - the combined positions
     */
    public static List<List<IntPair>> combinePosition(List<IntPair> positionList) {
        // TODO: Implement the method
        List<List<IntPair>> combinedList = new ArrayList<>();
        // Add logic here
        return combinedList;
    }
}
