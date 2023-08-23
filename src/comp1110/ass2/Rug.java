package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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
    /**
     * Checks if two positions are adjacent.
     *
     * @param a - The first IntPair position.
     * @param b - The second IntPair position.
     * @return - True if adjacent, false otherwise.
     */
    public static boolean isAdjacent(IntPair a, IntPair b) {
        return (Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY())) == 1;
    }


    /**
     Calculates the payment each player needs to make to others based on rug adjacency.
     *
     * @return - A map where the key is the color, and the value is the total amount to be paid to other players.
     */
    public Map<String, Integer> countPayment() {
        Map<String, Integer> payments = new HashMap<>();

        // Initialize payment counters for each color
        payments.put("Blue", 0);
        payments.put("Red", 0);
        payments.put("Yellow", 0);
        payments.put("Brown", 0);

        // Nested loops for all color combinations
        for (String colorA : payments.keySet()) {
            for (String colorB : payments.keySet()) {
                if (!colorA.equals(colorB)) {
                    List<List<IntPair>> groupA = null; // Initialize to null
                    List<List<IntPair>> groupB = null; // Initialize to null

                    switch (colorA) {
                        case "Blue":
                            groupA = bluePiece;
                            break;
                        case "Red":
                            groupA = redPiece;
                            break;
                        case "Yellow":
                            groupA = yellowPiece;
                            break;
                        case "Brown":
                            groupA = brownPiece;
                            break;
                        //default:
                        //throw new IllegalStateException("Invalid color: " + colorA);
                    }

                    switch (colorB) {
                        case "Blue":
                            groupB = bluePiece;
                            break;
                        case "Red":
                            groupB = redPiece;
                            break;
                        case "Yellow":
                            groupB = yellowPiece;
                            break;
                        case "Brown":
                            groupB = brownPiece;
                            break;
                        //default:
                        //throw new IllegalStateException("Invalid color: " + colorB);
                    }

                    // Make sure the groups are not null before proceeding (you could also throw an exception here)
                    if (groupA != null && groupB != null) {
                        for (List<IntPair> groupFromA : groupA) {
                            for (IntPair tileFromA : groupFromA) {
                                for (List<IntPair> groupFromB : groupB) {
                                    for (IntPair tileFromB : groupFromB) {
                                        if (isAdjacent(tileFromA, tileFromB)) {
                                            payments.put(colorA, payments.get(colorA) + 1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return payments;
        }
    }
}