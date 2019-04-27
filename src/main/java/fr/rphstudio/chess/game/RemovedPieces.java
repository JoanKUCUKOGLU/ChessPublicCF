// RemovedPiece class for create and use list of black pieces and white pieces that are removed during the game //

package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import static fr.rphstudio.chess.interf.IChess.ChessColor.CLR_BLACK;
import static fr.rphstudio.chess.interf.IChess.ChessColor.CLR_WHITE;

import java.util.ArrayList;
import java.util.List;

public class RemovedPieces {

    private List<IChess.ChessType> blackPieces; // Variable for a list of Black Piece's Types
    private List<IChess.ChessType> whitePieces; // Variable for a list of White Piece's Types

    public RemovedPieces(){ // Constructor with no parameter
        this.blackPieces = new ArrayList<>();
        this.whitePieces = new ArrayList<>();
    }

    public void addRemovedPieces(Piece rPiece){ // Method for add removed piece to a list
        if (rPiece !=  null){ // If the current piece is not null
            if (rPiece.getColor() == CLR_WHITE ) { // If the color of the piece is White
                whitePieces.add(rPiece.getType()); // Add this piece to the WhitePieces List
            } else if (rPiece.getColor() == CLR_BLACK) { // Idem but with Black's Pieces
                blackPieces.add(rPiece.getType());
            }
        }
    }

    public List<IChess.ChessType> getRPList(IChess.ChessColor listColor){ // Method for get the list of removed Pieces with the color

        if (listColor == CLR_WHITE) { // If the listColor is White
            return whitePieces; // Return the list of white pieces removed
        } else if (listColor == CLR_BLACK) { // Idem but with Black's Pieces
            return blackPieces;
        }

        return null;
    }

}
