// King Move for define the move of the King//
package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class KingMove implements IMove {
    //@Override
    public List<IChess.ChessPosition> getPossibleMove(IChess.ChessPosition p, Board board) { // Call to this instance method who's return a list of possible move

        List<IChess.ChessPosition> array = new ArrayList<>(); // Instanciation of a new Array
        int y;
        int x;

        // Initialisation of coords for go up //

        y = p.y - 1;
        x = p.x + 0;

        if(y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) { // If the get piece is empty (null) or if the destination piece color is different thant the current piece
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y); //Declare a new position
                array.add(pos); // And Add it in the list of potential movements
            }
        }

        // Initialisation of coords for go down //

        y = p.y + 1;
        x = p.x + 0;
        if (y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        // Initialisation of coords for go left //

        y = p.y - 0;
        x = p.x - 1;
        if (y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        // Initialisation of coords for go right //

        y = p.y - 0;
        x = p.x + 1;
        if (y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }


        // Initialisation of coords for go left-top //

        y = p.y - 1;
        x = p.x - 1;
        if (y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        // Initialisation of coords for go left-right //

        y = p.y - 1;
        x = p.x + 1;
        if (y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        // Initialisation of coords for go bottom-left //

        y = p.y + 1;
        x = p.x - 1;
        if (y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        // Initialisation of coords for go bottom-right //

        y = p.y + 1;
        x = p.x + 1;
        if (y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        // Roque

        int nb = 0;
        int count = 0;
        IChess.ChessColor color = board.getPiece(p.y, p.x).getColor();

        for (int i = 5; i < 7; i++) {
            nb += 1;
            if (board.getPiece(p.y, i) == null) {
                count += 1;
            }
        }

        List<IChess.ChessPosition> posi = new ArrayList<>();

        if (count == nb && board.getPiece(p.y, p.x).getNbMovement() == 0) {
            if (board.getPiece(p.y, 7).getNbMovement() == 0) {

                count = 0;

                for (int i = 5; i < 7; i++) {
                    IChess.ChessPosition pos = new IChess.ChessPosition(i, p.y);
                    posi.add(pos);
                }


                for (IChess.ChessPosition here : posi) {

                    Board brd = board.clone();


                    brd.movePiece(p, here);

                    brd.getPiece(here.y, here.x).decreaseNbMovement();


                    IChess.ChessKingState state = brd.getKingState(color);

                    if (state == IChess.ChessKingState.KING_SAFE) {
                        count += 1;
                    }
                }

                if (count == nb) {
                    IChess.ChessPosition pos = new IChess.ChessPosition(7, p.y);
                    array.add(pos);
                }
            }

        }

        nb = 0;
        count = 0;

        for (int i = 3; i > 0; i--) {
            nb += 1;
            if (board.getPiece(p.y, i) == null) {
                count += 1;
            }
        }

        posi = new ArrayList<>();

        if (count == nb && board.getPiece(p.y, p.x).getNbMovement() == 0) {
            if (board.getPiece(p.y, 0).getNbMovement() == 0) {

                count = 0;

                for (int i = 3; i > 0; i--) {
                    IChess.ChessPosition pos = new IChess.ChessPosition(i, p.y);
                    posi.add(pos);
                }


                for (IChess.ChessPosition here : posi) {

                    Board brd = board.clone();


                    brd.movePiece(p, here);

                    brd.getPiece(here.y, here.x).decreaseNbMovement();


                    IChess.ChessKingState state = brd.getKingState(color);

                    if (state == IChess.ChessKingState.KING_SAFE) {
                        count += 1;
                    }
                }

                if (count == nb) {
                    IChess.ChessPosition pos = new IChess.ChessPosition(0, p.y);
                    array.add(pos);
                }
            }

        }

        return array;

    }

}
