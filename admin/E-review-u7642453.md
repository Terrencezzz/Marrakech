## Code Review

Reviewed by: Terrence Wu, u7642453

Reviewing code written by: Terrence Wu u7642453

Component:     
        
    public static int dfsForPayment(Assam assam, List<Colour> colourList, Set<IntPair> visited) {

        IntPair currentPos = assam.getPosition();

        if (visited.contains(currentPos)) {
            return 0;
        }

        visited.add(currentPos);

        int count = 1;
        List<IntPair> adjacentPositions = Assam.besidePositions(assam);
        Colour currentColour = Board.getColour(colourList, currentPos);
        Direction direction = assam.getDirection();

        for (IntPair adjacentPos : adjacentPositions) {
            if (Board.getColour(colourList, adjacentPos) == currentColour) {
                Assam newAssam = new Assam(adjacentPos, direction);
                count += dfsForPayment(newAssam, colourList, visited);
            }
        }

        return count;
    }

### Comments 

This function use the DFS algorithm to be a helper function for getPaymentAmount() which is in Marrakech class.
1. Objective: This function is an important part of this object.
2. Functionality: It does what it intended, there is no bug for now.
3. Complexity: This function use the DFS algorithm to reach the result instead of hard coding, it may not be the most simplified solution for both time complexity and space complexity, but it's the easiest way to do that.
4. Good names: dfsForPayment means it use DFS algorithm, and it's for counting payment, this is a very good name.
5. Comments: The getPaymentAmount function aims to check the payment, this function is a helper function, is use DFS algorithm to check if any nearby rugs have the same colour with the one which is stood by Assam. It counts how many rugs match this feature.
6. Conformance: This function makes the getPaymentAmount() becomes object orientation style, just like what many other functions are doing.


