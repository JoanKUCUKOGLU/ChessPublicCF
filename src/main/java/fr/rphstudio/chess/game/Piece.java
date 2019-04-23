package fr.rphstudio.chess.game;
import com.sun.deploy.security.ValidationState;
import fr.rphstudio.chess.interf.IChess;

public class Piece {

    private IChess.ChessColor color;
    private IChess.ChessType type;
    private int nb_Movement;

    public Piece(IChess.ChessColor myCOLOR, IChess.ChessType myTYPE){

        this.color = myCOLOR;
        this.type = myTYPE;
        this.nb_Movement = 0;
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
}
