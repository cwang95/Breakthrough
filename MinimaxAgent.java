import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cwang on 10/17/16.
 */
public class MinimaxAgent {

    public boolean isTerminal( Board board )
    {
        return board.isTerminal();
    }

    public Board minMax( Board board ) throws Exception {

        Board bestBoard = null;
        int currDepth = board.maxDepth +1;

        while (bestBoard == null && currDepth>0)
        {
            currDepth--;

            bestBoard = maxMove( board, currDepth );

        }
        return bestBoard;
    }

    private Board maxMove( Board maxBoard, int currDepth ) throws Exception {
        return maxMinBoard(maxBoard, currDepth - 1, -1*Integer.MAX_VALUE );
    }

    private Board minMove( Board minBoard, int currDepth ) throws Exception {
        return maxMinBoard( minBoard, currDepth - 1, Integer.MAX_VALUE );
    }

    private Board maxMinBoard( Board board, int currDepth, Integer bestMoveScore ) throws Exception {
        if ( board.isTerminal())
        {
            System.out.println(board.winner);
            return board;
        }

        if (currDepth <= 0)
        {
            System.out.println("Too Deep");
            return board;
        }

        int currBestScore = bestMoveScore;
        Board currBestBoard = null;


        if ( bestMoveScore == -1*Integer.MAX_VALUE )
        {
            List<Move> movesList = board.getBlackMoves();
            for ( Move move: movesList)
            {
                System.out.println("Looking for a new best score! max ");

                Board maxState = new Board( board, move);
                int value = staticEvaluation( minMove( maxState, currDepth -1) );

                if ( value > currBestScore )
                {
                    System.out.println("Found a new best score! max: " + currBestScore);
                    currBestScore = value;
                    currBestBoard = maxState;
                }
            }
        } else if ( bestMoveScore == Integer.MAX_VALUE )
        {
            List<Move> movesList = board.getWhiteMoves();
            for ( Move move: movesList)
            {
                Board maxState = new Board( board, move);
                int value = staticEvaluation( maxMove( maxState, currDepth -1));

                if ( value < currBestScore )
                {
                    currBestScore = value;
                    currBestBoard = maxState;
                }
            }
        }

        return currBestBoard;
    }

    private int staticEvaluation( Board board )
    {
        if ( board == null ){
            System.out.println("Returned 0\n\n\n");
        }

        if ( board.winner == Board.Color.Black ) {
            System.out.println("FOund black winenr\n\n\n");
            return Integer.MAX_VALUE;
        }
        else if ( board.winner == Board.Color.White )
            return -1*Integer.MAX_VALUE;

        int score = 0;
        int turn = 0;
        List<Piece> pieces = new ArrayList<Piece>();

        if ( board.turn == Board.Color.White )
        {
            turn = -1;
            pieces = board.getWhitePieces();
        } else {
            turn = 1;
            pieces = board.getBlackPieces();
        }

        return pieces.size()*turn;
    }

}
