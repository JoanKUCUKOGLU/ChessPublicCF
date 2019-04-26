package fr.rphstudio.chess.game;


import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

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
        ChessColor color = currentPiece.getColor();

        List<ChessPosition> posListFinal = new ArrayList<>();

        if (currentPiece != null) {
            List<ChessPosition> posList = currentPiece.getMoves(p, board);

            for (ChessPosition pos : posList) {

                Board brd = board.clone();


                if (brd.getPiece(p.y, p.x) != null && brd.getPiece(pos.y, pos.x) != null) {
                    brd.movePiece(p, pos);

                    brd.getPiece(pos.y, pos.x).decreaseNbMovement();
                }

                ChessKingState state = brd.getKingState(color);

                if (state == ChessKingState.KING_SAFE) {
                    posListFinal.add(pos);
                }

            }

            return posListFinal;
        }

        return new ArrayList<>();
    }

    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {
        board.movePiece(p0, p1);
    }

    @Override
    public ChessKingState getKingState(ChessColor color) {

        return this.board.getKingState(color);
    }

    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {
        return board.getRemovePieces(color);
    }

    @Override
    public boolean undoLastMove() {
        return false;
    }

    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return 0;
    }
}
