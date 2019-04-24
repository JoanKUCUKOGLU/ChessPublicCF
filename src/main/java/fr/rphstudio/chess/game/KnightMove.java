package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class KnightMove implements IMove {
    //@Override
    public List<IChess.ChessPosition> getPossibleMove(IChess.ChessPosition p, Board board) {

        List<IChess.ChessPosition> array = new ArrayList<>();
        int y = p.y;
        int x = p.x;
        int index = 0;

        y = p.y - 2;
        x = p.x - 1;
        if(y >= 0 && y < 8 && x >= 0 && x < 8) {
            if (board.getPiece(y, x) == null) {
                IChess.ChessPosition pos = new IChess.ChessPosition(x, y);
                array.add(pos);
            }
        }

        return array;
    }

}
