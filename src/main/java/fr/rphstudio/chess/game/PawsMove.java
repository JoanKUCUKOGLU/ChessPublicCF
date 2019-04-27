// Paws Move for define the move of the Paws //
package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class PawsMove implements IMove {

    //@Override

    public List<IChess.ChessPosition> getPossibleMove(IChess.ChessPosition p, Board board) { // Call to this instance method who's return a list of possible move

        List<IChess.ChessPosition> array = new ArrayList<>(); // Instanciation of a new Array

        int y = p.y; // Initialisation of coords
        int x = p.x;

        Piece currentPiece = board.getPiece(y, x);  // Get a piece
        IChess.ChessColor color = currentPiece.getColor(); // Get the color of this piece
        int nbMove = currentPiece.getNbMovement(); // Get the number of move of this piece



        if (color == IChess.ChessColor.CLR_WHITE){ // If this Piece is White

            Piece nextPiece = board.getPiece(y-1, x); // Get destination piece
            IChess.ChessPosition pos; // Declare a chess position

            if (x+1 <= 7) { // If x+1 is higher or equal to 7
                Piece tryRight = board.getPiece(y-1, x+1); // Get a potential final position to the top-right
                Piece passingRight = board.getPiece(y, x+1); // Get a piece to go right

                if (tryRight != null){ // If the top-right piece is not null
                    if (tryRight.getColor() != color){ // If the color of this piece is different to the color of the currentPiece
                        pos = new IChess.ChessPosition(x+1, y-1); //Declare a new position
                        array.add(pos); // And Add it in the list of potential movements
                    }
                } else if (passingRight != null && tryRight == null) { // If the right piece is not null and the top-right is null
                    if (passingRight.getType() == IChess.ChessType.TYP_PAWN && passingRight.getNbMovement() == 1 && passingRight.getColor() == IChess.ChessColor.CLR_BLACK) {
                        // If the right piece type is a Pawn and his number of movement equals 1 and his color is black
                        pos = new IChess.ChessPosition(x+1, y-1); //Declare a new position
                        array.add(pos); // And Add it in the list of potential movements
                    }
                }


            }

            if (x-1 >= 0) {
                Piece tryLeft = board.getPiece(y-1, x-1);
                Piece passingLeft = board.getPiece(y, x-1);


                if (tryLeft != null){
                    if (tryLeft.getColor() != color){
                        pos = new IChess.ChessPosition(x-1, y-1);
                        array.add(pos);
                    }
                } else if (passingLeft != null && tryLeft == null) {
                    if (passingLeft.getType() == IChess.ChessType.TYP_PAWN && passingLeft.getNbMovement() == 1 && passingLeft.getColor() == IChess.ChessColor.CLR_BLACK) {
                        pos = new IChess.ChessPosition(x-1, y-1);
                        array.add(pos);
                    }
                }
            }

            if(y-1 > -1 && nextPiece == null){
                pos = new IChess.ChessPosition(x, y-1);
                array.add(pos);

                if (nbMove == 0 && board.getPiece(y-2, x) == null) {
                    pos = new IChess.ChessPosition(x, y-2);
                    array.add(pos);
                }
            }

        } else { // If this piece is Black, idem but in reverse

            Piece nextPiece = board.getPiece(y+1, x);

            if(y+1 <= 7 && nextPiece == null){
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y+1);
                array.add(pos);

                if (x+1 <= 7) {
                    Piece tryRight = board.getPiece(y+1, x+1);
                    Piece passingRight = board.getPiece(y, x+1);


                    if (tryRight != null){
                        if (tryRight.getColor() != color){

                            pos = new IChess.ChessPosition(x+1, y+1);
                            array.add(pos);
                        }
                    } else if (passingRight != null && tryRight == null) {
                        if (passingRight.getType() == IChess.ChessType.TYP_PAWN && passingRight.getNbMovement() == 1 && passingRight.getColor() == IChess.ChessColor.CLR_BLACK) {
                            pos = new IChess.ChessPosition(x+1, y+1);
                            array.add(pos);
                        }
                    }
                }

                if (x-1 >= 0) {
                    Piece tryLeft = board.getPiece(y+1, x-1);
                    Piece passingLeft = board.getPiece(y, x-1);


                    if (tryLeft != null){
                        if (tryLeft.getColor() != color){
                            pos = new IChess.ChessPosition(x-1, y+1);
                            array.add(pos);
                        }
                    } else if (passingLeft != null && tryLeft == null) {
                        if (passingLeft.getType() == IChess.ChessType.TYP_PAWN && passingLeft.getNbMovement() == 1 && passingLeft.getColor() == IChess.ChessColor.CLR_BLACK) {
                            pos = new IChess.ChessPosition(x-1, y+1);
                            array.add(pos);
                        }
                    }
                }

                if (nbMove == 0 && board.getPiece(y+2, x) == null) {
                    pos = new IChess.ChessPosition(x, y+2);
                    array.add(pos);
                }
            }

        }


        return array; // Return a completed Array of potential coords.
    }

}
