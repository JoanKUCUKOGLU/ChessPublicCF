package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class PawsMove implements IMove {

    //@Override

    public List<IChess.ChessPosition> getPossibleMove(IChess.ChessPosition p, Board board) {

        List<IChess.ChessPosition> array = new ArrayList<>();

        int y = p.y;
        int x = p.x;
        Piece currentPiece = board.getPiece(y, x);
        IChess.ChessColor color = currentPiece.getColor();
        int nbMove = currentPiece.getNbMovement();



        if (color == IChess.ChessColor.CLR_WHITE){

            Piece nextPiece = board.getPiece(y-1, x);
            IChess.ChessPosition pos;

            if (x+1 <= 7) {
                Piece tryRight = board.getPiece(y-1, x+1);
                Piece passingRight = board.getPiece(y, x+1);

                if (tryRight != null){
                    if (tryRight.getColor() != color){
                        pos = new IChess.ChessPosition(x+1, y-1);
                        array.add(pos);
                    }
                } else if (passingRight != null && tryRight == null) {
                    if (passingRight.getType() == IChess.ChessType.TYP_PAWN && passingRight.getNbMovement() == 1 && passingRight.getColor() == IChess.ChessColor.CLR_BLACK) {
                        pos = new IChess.ChessPosition(x+1, y-1);
                        array.add(pos);
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

        } else {

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


        return array;
    }

}
