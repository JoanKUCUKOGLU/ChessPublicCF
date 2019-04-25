package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class Piece {

    private IChess.ChessColor color;
    private IChess.ChessType type;
    private int nbMovement;
    private IMove imove;

    public Piece(
            IChess.ChessColor myColor,
            IChess.ChessType myType,
            IMove im
    ) {
        this.color = myColor;
        this.type = myType;
        this.nbMovement = 0;
        this.imove = im;
    }

    public IChess.ChessColor getColor() {
        return color;
    }

    public IChess.ChessType getType() {
        return type;
    }

    public int getNbMovement() {
        return nbMovement;
    }

    public void increaseNbMovement(int nbMovement) {
        this.nbMovement += 1;
    }

    public void decreaseNbMovement(int nbMovement) { this.nbMovement -= 1; }

    public List<IChess.ChessPosition> getMoves(IChess.ChessPosition p, Board board) {

        return this.imove.getPossibleMove(p, board);

    }


}
