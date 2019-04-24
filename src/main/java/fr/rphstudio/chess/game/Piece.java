package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class Piece {

    private IChess.ChessColor color;
    private IChess.ChessType type;
    private int nb_Movement;
    private IMove imove;

    public Piece(
            IChess.ChessColor myColor,
            IChess.ChessType myType,
            IMove im
    ){
        this.color = myColor;
        this.type = myType;
        this.nb_Movement = 0;
        this.imove = im;
    }

    public IChess.ChessColor getColor() {
        return color;
    }

    public IChess.ChessType getType() {
        return type;
    }

    public int getNb_Movement() {
        return nb_Movement;
    }

    public void setNb_Movement(int nb_Movement) {
        this.nb_Movement = nb_Movement;
    }

    public List<IChess.ChessPosition> getMoves(IChess.ChessPosition p, Board board) {

        return this.imove.getPossibleMove(p, board);

    }

    

}
