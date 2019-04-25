package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class KingMove implements IMove {
    //@Override
    public List<IChess.ChessPosition> getPossibleMove(IChess.ChessPosition p, Board board) {

        List<IChess.ChessPosition> array = new ArrayList<>();
        int y;
        int x;

        y = p.y - 1;
        x = p.x + 0;
        if(y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        y = p.y + 1;
        x = p.x + 0;
        if(y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        y = p.y - 0;
        x = p.x - 1;
        if(y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        y = p.y - 0;
        x = p.x + 1;
        if(y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }


        y = p.y - 1;
        x = p.x - 1;
        if(y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        y = p.y - 1;
        x = p.x + 1;
        if(y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        y = p.y + 1;
        x = p.x - 1;
        if(y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        y = p.y + 1;
        x = p.x + 1;
        if(y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null || board.getPiece(y, x).getColor() != board.getPiece(p.y, p.x).getColor()) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        return array;
    }

}
