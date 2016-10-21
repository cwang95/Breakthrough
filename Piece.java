import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cwang on 10/17/16.
 */
public class Piece {

    public Board.Color color;
    Coordinates coordinates;
    int direction;

    public Piece( Board.Color color, Coordinates coordinates )
    {
        this.color = color;
        if (color == Board.Color.Black){
            this.direction = 1;
        } else {
            this.direction = -1;
        }
        this.coordinates = coordinates;
    }

    public List<Move> calculateMoves( Board board ){
        List<Move> listOfAvailableMoves = new ArrayList<>();



        Coordinates forwardLeft = new Coordinates( this.coordinates.x + direction , this.coordinates.y + direction);
        if ( board.isOnBoard(forwardLeft)&& board.getPieceAt(forwardLeft).color != this.color) {
            listOfAvailableMoves.add(new Move(this.coordinates, forwardLeft));
        }
        Coordinates forwardRight = new Coordinates( this.coordinates.x + direction , this.coordinates.y - direction);
        if ( board.isOnBoard(forwardRight) && board.getPieceAt(forwardRight).color != this.color) {
            listOfAvailableMoves.add(new Move(this.coordinates, forwardRight));
        }

        Coordinates forward = new Coordinates( this.coordinates.x + direction, this.coordinates.y );
        if ( board.isOnBoard(forward)&& board.getPieceAt(forward).color != this.color){
            if ( board.getPieceAt(forward).color == Board.Color.Empty){
                listOfAvailableMoves.add( new Move(this.coordinates, forward));

            }
        }
        return listOfAvailableMoves;
    }

    public void printPiece(){
        if (this.color == Board.Color.Black)
        {
            System.out.print("B");
        }
        if (this.color == Board.Color.White)
        {
            System.out.print("W");
        }
        if (this.color == Board.Color.Empty)
        {
            System.out.print(".");
        }
    }


}
