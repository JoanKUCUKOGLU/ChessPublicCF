// Piece Class for create a Piece Object //
package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.List;

public class Piece {

    private IChess.ChessColor color; // Variable for the color of the piece
    private IChess.ChessType type; // Vairable for the type of the piece
    private int nbMovement; //Variable for the number of movement of the piece
    private IMove imove; //Variable for the IMove's Instance in order to assing the Piece's movement (go to IMOVE abstract class)

    public Piece( //Piece constructor with the color, the type and the movement
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

    public void increaseNbMovement() { // Method for increase the number of movement of the piece
        this.nbMovement += 1;
    }

    public void decreaseNbMovement() { this.nbMovement -= 1; } //Reverse Method

    public List<IChess.ChessPosition> getMoves(IChess.ChessPosition p, Board board) { //Method for get the move of the piece

        return this.imove.getPossibleMove(p, board); //Return the result of the call of the IMOVE's fuction (go to IMOVE abstract class)

    }


}
