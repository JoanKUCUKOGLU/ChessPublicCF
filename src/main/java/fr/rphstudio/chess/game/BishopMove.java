// Bishop Move for define the move of the bishop in all directions //
package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class BishopMove implements IMove {
    //@Override

    public List<IChess.ChessPosition> getPossibleMove(IChess.ChessPosition p, Board board) { // Call to this instance method who's return a list of possible move

        List<IChess.ChessPosition> array = new ArrayList<>(); // Instanciation of a new Array

        int y = p.y; // Initialisation of coords
        int x = p.x;

        Piece startPiece = board.getPiece(y, x); // Get a piece

        // For go in right_bottom //

        for (int i = 1; i < 8; i++) {
            if (y + i < 8 && y + i >= 0 && x + i < 8 && x + i >= 0) { //If the potential movement is in the ChessTable
                Piece nextPiece = board.getPiece(y + i, x + i); //Get a destination piece

                if (nextPiece == null) { //If the future place is empty (with no piece, so, null)
                    IChess.ChessPosition pos = new IChess.ChessPosition(x + i, y + i); //Declare a new position
                    array.add(pos); // And Add it in the list of potential movements
                } else if (nextPiece.getColor() != startPiece.getColor()){ //if there is a piece in the future piece and his color is different than our current piece
                    IChess.ChessPosition pos = new IChess.ChessPosition(x + i, y + i); //Declare new position on the enemy piece
                    array.add(pos);
                    break;
                } else { // If there is a piece in the future place and this is a same piece color that the current piece, nothing happend
                    break;
                }
            }
        }

        // For go in right-top //

        for (int i = 1; i < 8; i++) {
            if (y - i < 8 && y - i >= 0 && x + i < 8 && x + i >= 0) {
                Piece nextPiece = board.getPiece(y - i, x + i);

                if (nextPiece == null) {
                    IChess.ChessPosition pos = new IChess.ChessPosition(x + i, y - i);
                    array.add(pos);
                } else if (nextPiece.getColor() != startPiece.getColor()){
                    IChess.ChessPosition pos = new IChess.ChessPosition(x + i, y - i);
                    array.add(pos);
                    break;
                } else {
                    break;
                }
            }
        }

        // For go in left-top //

        for (int i = 1; i < 8; i++) {
            if (y - i < 8 && y - i >= 0 && x - i < 8 && x - i >= 0) {
                Piece nextPiece = board.getPiece(y - i, x - i);

                if (nextPiece == null) {
                    IChess.ChessPosition pos = new IChess.ChessPosition(x - i, y - i);
                    array.add(pos);
                } else if (nextPiece.getColor() != startPiece.getColor()){
                    IChess.ChessPosition pos = new IChess.ChessPosition(x - i, y - i);
                    array.add(pos);
                    break;
                } else {
                    break;
                }
            }
        }

        // For go in left-bottom //

        for (int i = 1; i < 8; i++) {
            if (y + i < 8 && y + i >= 0 && x - i < 8 && x - i >= 0) {
                Piece nextPiece = board.getPiece(y + i, x - i);

                if (nextPiece == null) {
                    IChess.ChessPosition pos = new IChess.ChessPosition(x - i, y + i);
                    array.add(pos);
                } else if (nextPiece.getColor() != startPiece.getColor()){
                    IChess.ChessPosition pos = new IChess.ChessPosition(x - i, y + i);
                    array.add(pos);
                    break;
                } else {
                    break;
                }
            }
        }

        return array; // Return a completed Array of potential coords.

    }

}
