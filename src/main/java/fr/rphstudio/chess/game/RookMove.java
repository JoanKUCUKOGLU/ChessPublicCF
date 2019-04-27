// Rook Move for define the move of the Rook //

package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class RookMove implements IMove {

    //@Override
    public List<IChess.ChessPosition> getPossibleMove(IChess.ChessPosition p, Board board) { // Call to this instance method who's return a list of possible move

        List<IChess.ChessPosition> array = new ArrayList<>(); // Instanciation of a new Array

        int y = p.y;
        int x = p.x;

        Piece startPiece = board.getPiece(y, x);

        // For go down //

        for(int i = y + 1; i < 8; i ++){ //For each y between 0 and 8

            Piece nextPiece = board.getPiece(i, x);
            if(nextPiece == null){ //If the future place is empty (with no piece, so, null)
                IChess.ChessPosition pos = new IChess.ChessPosition(x, i); //Declare a new position
                array.add(pos); // And Add it in the list of potential movements
            } else if (nextPiece.getColor() != startPiece.getColor()){ //if there is a piece in the future piece and his color is different than our current piece
                IChess.ChessPosition pos = new IChess.ChessPosition(x, i);
                array.add(pos);
                break;
            } else {
                break; // If there is a piece in the future place and this is a same piece color that the current piece, nothing happend
            }
        }

        // For go Up //

        for(int i = y - 1; i >= 0; i --){ //For each y between 8 and 0

            Piece nextPiece = board.getPiece(i, x);
            if(nextPiece == null){
                IChess.ChessPosition pos = new IChess.ChessPosition(x, i);
                array.add(pos);
            } else if (nextPiece.getColor() != startPiece.getColor()){
                IChess.ChessPosition pos = new IChess.ChessPosition(x, i);
                array.add(pos);
                break;
            } else {
                break;
            }
        }


        // For go right //

        for(int i = x + 1; i < 8; i ++){ //For each x between 0 and 8

            Piece nextPiece = board.getPiece(y, i);
            if(nextPiece == null){
                IChess.ChessPosition pos = new IChess.ChessPosition(i, y);
                array.add(pos);
            } else if (nextPiece.getColor() != startPiece.getColor()){
                IChess.ChessPosition pos = new IChess.ChessPosition(i, y);
                array.add(pos);
                break;
            } else {
                break;
            }
        }

        // For go left //

        for(int i = x - 1; i >= 0; i --){ //For each x between 8 and 0

            Piece nextPiece = board.getPiece(y, i);
            if(nextPiece == null){
                IChess.ChessPosition pos = new IChess.ChessPosition(i, y);
                array.add(pos);
            } else if (nextPiece.getColor() != startPiece.getColor()){
                IChess.ChessPosition pos = new IChess.ChessPosition(i, y);
                array.add(pos);
                break;
            } else {
                break;
            }
        }

        return array; // Return a completed Array of potential coords.

    }
}
