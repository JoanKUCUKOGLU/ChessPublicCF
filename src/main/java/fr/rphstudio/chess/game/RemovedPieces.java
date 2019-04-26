package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import static fr.rphstudio.chess.interf.IChess.ChessColor.CLR_BLACK;
import static fr.rphstudio.chess.interf.IChess.ChessColor.CLR_WHITE;

import java.util.ArrayList;
import java.util.List;

public class RemovedPieces {

    private List<IChess.ChessType> blackPieces;
    private List<IChess.ChessType> whitePieces;

    public RemovedPieces(){
        this.blackPieces = new ArrayList<>();
        this.whitePieces = new ArrayList<>();
    }

    public void addRemovedPieces(Piece rPiece){
        if (rPiece !=  null){
            if (rPiece.getColor() == CLR_WHITE ) {
                whitePieces.add(rPiece.getType());
            } else if (rPiece.getColor() == CLR_BLACK) {
                blackPieces.add(rPiece.getType());
            }
        }
    }

    public List<IChess.ChessType> getRPList(IChess.ChessColor listColor){

        if (listColor == CLR_WHITE) {
            return whitePieces;
        } else if (listColor == CLR_BLACK) {
            return blackPieces;
        }

        return null;
    }

}
