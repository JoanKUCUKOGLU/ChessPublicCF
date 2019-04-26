package fr.rphstudio.chess.game;


import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ChessModel implements IChess {

    private static IChess instance;
    private Board board;

    private ChessModel() {
        board = new Board(
                BOARD_WIDTH,
                BOARD_HEIGHT,

                BOARD_POS_Y_WHITE_PIECES,
                BOARD_POS_Y_WHITE_PAWNS,
                BOARD_POS_Y_BLACK_PAWNS,
                BOARD_POS_Y_BLACK_PIECES,

                BOARD_POS_X_QUEENSIDE_ROOK,
                BOARD_POS_X_QUEENSIDE_KNIGHT,
                BOARD_POS_X_QUEENSIDE_BISHOP,
                BOARD_POS_X_QUEEN,
                BOARD_POS_X_KING,
                BOARD_POS_X_KINGSIDE_BISHOP,
                BOARD_POS_X_KINGSIDE_KNIGHT,
                BOARD_POS_X_KINGSIDE_ROOK
        );
    }

    public static IChess getInstance() {
        if (ChessModel.instance == null) {
            ChessModel.instance = new ChessModel();
        }
        return ChessModel.instance;
    }

    //@Overridegit remote add origin https://github.com/JoanKUCUKOGLU/ChessPublicCF.git
    public void reinit() {
    }

    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {

        Piece current_piece = board.getPiece(p.y, p.x);

        if (p.y < 0 || p.y > 7 || p.x < 0 || p.x > 7) {
            throw new OutOfBoardException();
        } else if (current_piece == null) {
            throw new EmptyCellException();
        } else {
            return current_piece.getType();
        }
    }


    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {

        Piece current_piece;

        if (p.y < 0 || p.y > 7 || p.x < 0 || p.x > 7) {
            throw new OutOfBoardException();
        } else {
            current_piece = board.getPiece(p.y, p.x);

            if (current_piece == null) {
                throw new EmptyCellException();
            } else {
                return current_piece.getColor();
            }
        }
    }


    @Override
    public int getNbRemainingPieces(ChessColor color) {
        return board.getNbRemainingPieces(color);
    }

    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {

        Piece currentPiece = board.getPiece(p.y, p.x);

        if(currentPiece != null) {
            return currentPiece.getMoves(p, board);
        }

        return new ArrayList<>();
    }

    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {
        board.movePiece(p0, p1);
    }

    @Override
    public ChessKingState getKingState(ChessColor color) {

        ChessPosition kingPosition = null;

        for (int i = 0; i < 8 && kingPosition == null; i++) {

            for (int j = 0; j < 8 && kingPosition == null; j++) {

                Piece currentPiece = board.getPiece(i, j);
                if(currentPiece != null){
                    if (currentPiece.getType() == ChessType.TYP_KING && currentPiece.getColor() == color){

                        kingPosition = new ChessPosition(j,i);

                    }
                }
            }
        }
        if(kingPosition == null){
            return ChessKingState.KING_SAFE;
        }

        // FOr each row
        for (int i = 0; i < 8; i++) {
            // For each column
            for (int j = 0; j < 8; j++) {
                // Get piece for the current position j,i
                Piece currentPiece = board.getPiece(i, j);
                // If the piece exists (the cell is not empty)
                if(currentPiece != null){
                    // If the color of the current piece is the enemy color
                    if(currentPiece.getColor() != color){
                        // GEt position object for the current piece
                        ChessPosition currentPos = new ChessPosition(j,i);
                        // Get the possible moves of the enemy piece
                        List<ChessPosition> posList = currentPiece.getMoves(currentPos,board);
                        // For each possible move of the enemy
                        for(ChessPosition p: posList){
                            // If the current king position is equals to the enemy destination, that means the king is threaten
                            if(p.equals(kingPosition)){
                                return ChessKingState.KING_THREATEN;
                            }
                        }
                    }
                }
            }
        }

        // no enemy possible move that threats the king has been found
        return ChessKingState.KING_SAFE;
    }

    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {
        return board.getRemovePieces(color);
    }

    @Override
    public boolean undoLastMove() {
        boolean b  = this.board.IsChangement();
        return b;
    }

    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return 0;
    }
}
