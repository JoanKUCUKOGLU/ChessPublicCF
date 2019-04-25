package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class RookMove implements IMove {

    //@Override
    public List<IChess.ChessPosition> getPossibleMove(IChess.ChessPosition p, Board board) {

        List<IChess.ChessPosition> array = new ArrayList<>();

        int y = p.y;
        int x = p.x;

        Piece startPiece = board.getPiece(y, x);

        for(int i = y + 1; i < 8; i ++){

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

        for(int i = y - 1; i >= 0; i --){

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


        for(int i = x + 1; i < 8; i ++){

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

        for(int i = x - 1; i >= 0; i --){

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

        return array;

    }
}
