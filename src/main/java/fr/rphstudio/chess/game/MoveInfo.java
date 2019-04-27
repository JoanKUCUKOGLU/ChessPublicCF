// MoveInfo Class for create an object that regroup lot of information about a move //
package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class MoveInfo {
    public Piece initialPiece;  //Variable for the initialPiece before the move
    public IChess.ChessPosition initialPosition; //Variable for the initiale position before the move
    public IChess.ChessPosition finalPosition; //Variable for the final position after the move
    public Piece finalPiece; // Variable for the final Piece after the move
    public Boolean isRemoved;//Variable for indicate if a piece is removed during the move
    public Boolean enPassant;//Variable for indicate if a piece is removed during the "En Passant" move
    public long time; //Variable for get the current time durent the move
    public Board board; //Variable for use the board

    public MoveInfo(IChess.ChessPosition initPos, IChess.ChessPosition finalPos, Board b, long t){ // Constructor with initial position, final position, the board and the time
        this.initialPiece = b.getPiece(initPos.y,initPos.x);
        this.finalPiece = b.getPiece(finalPos.y,finalPos.x);
        this.initialPosition = initPos;
        this.finalPosition = finalPos;
        this.isRemoved = false;
        this.enPassant = false;
        this.time = t;
    }
}
