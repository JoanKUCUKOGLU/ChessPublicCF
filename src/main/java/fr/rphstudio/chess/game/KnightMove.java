// Knight Move for define the move of the Knight //
package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class KnightMove implements IMove {
    //@Override
    public List<IChess.ChessPosition> getPossibleMove(IChess.ChessPosition p, Board board) { // Call to this instance method who's return a list of possible move

        List<IChess.ChessPosition> array = new ArrayList<>(); // Instanciation of a new Array
        int y;
        int x;

        // Initialisation of coords for go bottom-right(2-down/1-right) //

        y = p.y + 2;
        x = p.x + 1;
        if(y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) { // If the get piece is empty (null) or if the destination piece color is different thant the current piece
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y); //Declare a new position
                array.add(pos); // And Add it in the list of potential movements
            }
        }

        // Initialisation of coords for go bottom-left(2-down/1-left) //

        y = p.y + 2;
        x = p.x - 1;
        if(y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        // Initialisation of coords for go top-right(2-up/1-right) //

        y = p.y - 2;
        x = p.x + 1;
        if(y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        // Initialisation of coords for go top-left(2-up/1-left) //

        y = p.y - 2;
        x = p.x - 1;
        if(y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        // Initialisation of coords for go bottom-right(1-down/2-right) //

        y = p.y + 1;
        x = p.x + 2;
        if(y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        // Initialisation of coords for go bottom-left(1-down/2-left) //

        y = p.y + 1;
        x = p.x - 2;
        if(y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        // Initialisation of coords for go top-right(1-up/2-right) //

        y = p.y - 1;
        x = p.x + 2;
        if(y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        // Initialisation of coords for go top-left(1-up/2-left) //

        y = p.y - 1;
        x = p.x - 2;
        if(y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        return array; // Return a completed Array of potential coords.
    }

}
