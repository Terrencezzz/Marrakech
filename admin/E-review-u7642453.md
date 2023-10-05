## Code Review

Reviewed by: Terrence Wu, u7642453

Reviewing code written by: Attached code (Puzzle.java)

Component:     
        
     /**
     * Shuffle (and rotate) tiles randomly.
     */
    public void shuffle() {
	Random rg = new Random();
	for (int to = 0; to + 1 < tiles.length; to++) {
	    int from = to + rg.nextInt(tiles.length - to);
	    Tile tmp = tiles[to];
	    tiles[to] = tiles[from];
	    tiles[from] = tmp;
	    for (int nr = rg.nextInt(4); nr > 0; nr--)
		tiles[to].rotateClockwise();
	    }
    }

### Comments 

This function use a random number to rotate the tile in clockwise.
1. Objective: This function is an important part of the object "Puzzle".
2. Functionality: It does what it intended, there is no bug for now.
3. Complexity: This function use a for loop to rotate the tile, the complexity should be n here.
4. Good names: "shuffle" is a very good name, it directly shows what it does, the variables' names also represent what it does.
5. Comments: The "Shuffle" function is in Puzzle class which will rotate the tile randomly.
6. Conformance: This is a void function which is widely used in "Puzzle" class.