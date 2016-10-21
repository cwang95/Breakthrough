import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cwang on 10/17/16.
 */
public class Board {


    public enum Color {Black, White, Empty}

    public Color winner = Color.Empty;
    public Color turn;

    public int maxDepth = 5;

    public Piece[][] gameBoard;

    public Board() {
        gameBoard = new Piece[8][8];
        turn = Color.Black;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard[i][j] = new Piece(Color.Black, new Coordinates(i, j));
            }
        }
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard[i][j] = new Piece(Color.Empty, new Coordinates(i, j));
            }
        }
        for (int i = 6; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard[i][j] = new Piece(Color.White, new Coordinates(i, j));
            }
        }

    }

    public Board(Board other, Move move) {
        gameBoard = new Piece[8][8];

        if (other.turn == Color.White)
            this.turn = Color.Black;
        if (other.turn == Color.Black)
            this.turn = Color.White;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard[i][j] = new Piece(Color.Empty, new Coordinates(i, j));
            }
        }

        for (final Piece piece : other.getWhitePieces()) {
            gameBoard[piece.coordinates.x][piece.coordinates.y].color = piece.color;
        }

        for (final Piece piece : other.getBlackPieces()) {
            gameBoard[piece.coordinates.x][piece.coordinates.y].color = piece.color;
        }

        Color movePieceColor = removePieceAt(move.old_coordinates);
        addPieceAt(move.new_coordinates, movePieceColor);

        if (move.new_coordinates.y == 7 && movePieceColor == Color.Black) {
            this.winner = Color.Black;
            System.out.println("Reached other side black");
        }
        if (move.new_coordinates.y == 0 && movePieceColor == Color.White) {
            this.winner = Color.White;
            System.out.println("Reached other side white");
        }

        if (this.getWhitePieces().isEmpty()) {
            this.winner = Color.Black;
            System.out.println("Reached empty side black");

        }
        if (this.getBlackPieces().isEmpty()) {
            this.winner = Color.White;
            System.out.println("Reached empty side white");
        }

    }

    public List<Piece> getWhitePieces() {
        List<Piece> listOfPieces = new ArrayList<>();

        for (Piece pieceRow[] : gameBoard) {
            for (Piece piece : pieceRow) {
                if (piece.color == Color.White) {
                    listOfPieces.add(piece);
                }
            }
        }
        return listOfPieces;
    }

    public List<Piece> getBlackPieces() {
        List<Piece> listOfPieces = new ArrayList<>();

        for (Piece pieceRow[] : gameBoard) {
            for (Piece piece : pieceRow) {
                if (piece.color == Color.Black) {
                    listOfPieces.add(piece);
                }
            }
        }
        return listOfPieces;
    }

    public List<Move> getBlackMoves() {
        List<Move> listOfAllMoves = new ArrayList<>();

        List<Piece> blackPieces = getBlackPieces();
        for (Piece piece : blackPieces) {
            List<Move> pieceMoves = piece.calculateMoves(this);
            if (pieceMoves != null)
                listOfAllMoves.addAll(pieceMoves);
        }

        return listOfAllMoves;
    }

    public List<Move> getWhiteMoves() {
        List<Move> listOfAllMoves = new ArrayList<>();

        List<Piece> whitePieces = getWhitePieces();
        for (Piece piece : whitePieces) {
            List<Move> pieceMoves = piece.calculateMoves(this);
            if (pieceMoves != null)
                listOfAllMoves.addAll(pieceMoves);
        }

        return listOfAllMoves;
    }

    public Piece getPieceAt(Coordinates forward) {
        return gameBoard[forward.x][forward.y];
    }

    public Color removePieceAt(Coordinates coordinates) {
        Color pieceColor = gameBoard[coordinates.x][coordinates.y].color;
        gameBoard[coordinates.x][coordinates.y].color = Color.Empty;
        return pieceColor;
    }

    public void addPieceAt(Coordinates coordinates, Color color) {
        gameBoard[coordinates.x][coordinates.y].color = color;
    }


    public boolean isOnBoard(Coordinates coord) {
        return coord.x >= 0 && coord.x < 8 && coord.y >= 0 && coord.y < 8;
    }

    public boolean isTerminal() {
        // TODO: Implement game logic

        if (this.winner != Color.Empty)
            return true;
        return false;
    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard[i][j].printPiece();
            }
            System.out.println();
        }
    }


}
