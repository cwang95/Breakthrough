/**
 * Created by Cwang on 10/17/16.
 */
public class Coordinates {

    public int x;
    public int y;

    public Coordinates( int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void printCoordinates() {
        System.out.println("("+ this.x + ", " + this.y + ")");
    }
}
