import java.util.List;

/**
 * Created by Cwang on 10/17/16.
 */
public class Driver {

    public static void main(String [] args){

        Board board = new Board();
        board.printBoard();

        List<Move> moves = board.getBlackMoves();

        int counter = 1;

        MinimaxAgent agent = new MinimaxAgent();

        Board bestMove = null;
        try {
            bestMove = agent.minMax(board);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bestMove.printBoard();
    }
}
