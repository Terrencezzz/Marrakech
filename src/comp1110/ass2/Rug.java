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
     *
     * @param positionList - the list of positions for a specific color
     * @return - the combined positions
     */
    public static List<List<IntPair>> combinePosition(List<IntPair> positionList) {
        List<List<IntPair>> combinedList = new ArrayList<>();

        for (IntPair pair : positionList) {
            boolean added = false;

            for (List<IntPair> group : combinedList) {
                for (IntPair inGroup : group) {
                    if (isAdjacent(inGroup, pair)) {
                        group.add(pair);
                        added = true;
                        break;
                    }
                }
                if (added) break;
            }

            if (!added) {
                List<IntPair> newGroup = new ArrayList<>();
                newGroup.add(pair);
                combinedList.add(newGroup);
            }
        }

        return combinedList;
    }

    public static boolean isAdjacent(IntPair a, IntPair b) {
        return (Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY())) == 1;
    }


    /**
     * This function aims to calculate how much u need to pay tor other's.(This is not done yet!!!)
     * @return
     */
    public static int countPayment(){
        return 0;
    }
}

