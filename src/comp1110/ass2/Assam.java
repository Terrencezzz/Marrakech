package comp1110.ass2;


/**
 * This class aims to record the status of Assam.
 */
public class Assam {

    public IntPair position;

    public String direction;


    /**
     * Initialize the status of Assam to position (3,3) and direction "Down".
     */
    public Assam(){
        this.position = new IntPair(3,3);
        this.direction = "Down";
    }

    public Assam(IntPair position, String direction){
        this.position = position;
        this.direction = direction;
    }


    /**
     * You can get the current position of Assam by using this function.
     * @return
     */
    public IntPair getPosition(){
        return position;
    }


    /**
     * You can get the current direction of Assam by using this function.
     * @return
     */
    public String getDirection(){
        return direction;
    }


    /**
     * Combine position and direction to a new type Status.
     * @param input
     * @return
     */
    public static Assam createStatus(String input) {
        String[] status = input.split(",");
        IntPair intPair = IntPair.createIntPair(status[0] + "," + status[1]);

        return new Assam(intPair, status[2].trim());
    }
}




