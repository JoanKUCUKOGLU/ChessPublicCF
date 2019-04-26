package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class MoveInfo {
    public Piece initialPiece;
    public IChess.ChessPosition initialPosition;
    public IChess.ChessPosition finalPosition;
    public Piece finalPiece;
    public Boolean isRemoved;
    public Boolean enPassant;
    public Board board;

    public MoveInfo(IChess.ChessPosition initPos, IChess.ChessPosition finalPos, Board b){
        this.initialPiece = b.getPiece(initPos.y,initPos.x);
        this.finalPiece = b.getPiece(finalPos.y,finalPos.x);
        this.initialPosition = initPos;
        this.finalPosition = finalPos;
        this.isRemoved = false;
        this.enPassant = false;
    }
}
