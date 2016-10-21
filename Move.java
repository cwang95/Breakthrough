/**
 * Created by Cwang on 10/17/16.
 */
public class Move {

    public Board.Color color;
    public Coordinates old_coordinates;
    public Coordinates new_coordinates;

    public Move( Coordinates old_coordinates, Coordinates new_coordinates)
    {
        this.old_coordinates = old_coordinates;
        this.new_coordinates = new_coordinates;
    }

}
