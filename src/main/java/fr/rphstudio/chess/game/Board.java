package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.List;

import static fr.rphstudio.chess.interf.IChess.*;
import static fr.rphstudio.chess.interf.IChess.ChessColor.CLR_BLACK;
import static fr.rphstudio.chess.interf.IChess.ChessColor.CLR_WHITE;
import static fr.rphstudio.chess.interf.IChess.ChessType.*;
import static java.lang.Math.abs;

public class Board {

    private Piece[][] tab;

    private RemovedPieces rp;

    private MoveInfo mi;

    private MoveHistory mh;

    public Board(
            int width,
            int height,

            int y_WhitePiece,
            int y_WhitePaws,
            int y_BlackPaws,
            int y_BlackPiece,

            int x_LeftRook,
            int x_LeftKnight,
            int x_LeftBishop,
            int x_Queen,
            int x_King,
            int x_RightBishop,
            int x_RightKnight,
            int x_RightRook
    ) {
        this.tab = new Piece[height][width];

        this.rp = new RemovedPieces();

        this.mh = new MoveHistory();
        // Setting black and white paws
        for (int i = 0; i < 8; i++) {
            tab[y_BlackPaws][i] = new Piece(CLR_BLACK, TYP_PAWN, new PawsMove());

            tab[y_WhitePaws][i] = new Piece(CLR_WHITE, TYP_PAWN, new PawsMove());
        }

        tab[y_BlackPiece][x_LeftRook] = new Piece(CLR_BLACK, TYP_ROOK, new RookMove());
        tab[y_BlackPiece][x_RightRook] = new Piece(CLR_BLACK, TYP_ROOK, new RookMove());
        tab[y_WhitePiece][x_LeftRook] = new Piece(CLR_WHITE, TYP_ROOK, new RookMove());
        tab[y_WhitePiece][x_RightRook] = new Piece(CLR_WHITE, TYP_ROOK, new RookMove());

        tab[y_BlackPiece][x_LeftKnight] = new Piece(CLR_BLACK, TYP_KNIGHT, new KnightMove());
        tab[y_BlackPiece][x_RightKnight] = new Piece(CLR_BLACK, TYP_KNIGHT, new KnightMove());
        tab[y_WhitePiece][x_LeftKnight] = new Piece(CLR_WHITE, TYP_KNIGHT, new KnightMove());
        tab[y_WhitePiece][x_RightKnight] = new Piece(CLR_WHITE, TYP_KNIGHT, new KnightMove());

        tab[y_BlackPiece][x_LeftBishop] = new Piece(CLR_BLACK, TYP_BISHOP, new BishopMove());
        tab[y_BlackPiece][x_RightBishop] = new Piece(CLR_BLACK, TYP_BISHOP, new BishopMove());
        tab[y_WhitePiece][x_LeftBishop] = new Piece(CLR_WHITE, TYP_BISHOP, new BishopMove());
        tab[y_WhitePiece][x_RightBishop] = new Piece(CLR_WHITE, TYP_BISHOP, new BishopMove());

        tab[y_BlackPiece][x_Queen] = new Piece(CLR_BLACK, TYP_QUEEN, new QueenMove());
        tab[y_WhitePiece][x_Queen] = new Piece(CLR_WHITE, TYP_QUEEN, new QueenMove());

        tab[y_BlackPiece][x_King] = new Piece(CLR_BLACK, TYP_KING, new KingMove());
        tab[y_WhitePiece][x_King] = new Piece(CLR_WHITE, TYP_KING, new KingMove());
    }

    public Piece[][] getTab() {
        return tab;
    }

    public void setTab(Piece[][] tab) {
        this.tab = tab;
    }

    public Piece getPiece(int y, int x) {
        return this.tab[y][x];
    }

    public void setPiece(int y, int x, Piece piece) {
        this.tab[y][x] = piece;
    }

    public int getNbRemainingPieces(IChess.ChessColor color) {

        int count = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (this.tab[i][j] != null) {

                    if (this.tab[i][j].getColor() == color) {
                        count += 1;
                    }
                }
            }
        }
        return count;
    }

    public void movePiece(IChess.ChessPosition pSource, IChess.ChessPosition pDest) {
        this.mi = new MoveInfo(pSource, pDest, this, false);
        this.mh.AddMoveInfo(this.mi);

        Piece destPiece = this.tab[pDest.y][pDest.x];
        Piece srcPiece = this.tab[pSource.y][pSource.x];


        if (destPiece != null && srcPiece != null) {
            if (destPiece.getColor() != srcPiece.getColor()) {
                this.rp.addRemovedPieces(destPiece);
                this.mi.isRemoved = true;
            }
        }

        this.tab[pSource.y][pSource.x].increaseNbMovement();
        this.tab[pDest.y][pDest.x] = getPiece(pSource.y, pSource.x);
        this.tab[pSource.y][pSource.x] = null;

        if (srcPiece.getType() == TYP_PAWN &&
                (destPiece == null) &&
                (abs(pSource.y - pDest.y) == 1 && abs(pSource.x - pDest.x) == 1)) {

            Piece passPiece = this.tab[pSource.y][pDest.x];
            this.rp.addRemovedPieces(passPiece);
            this.tab[pSource.y][pDest.x] = null;

        }
    }

    public boolean IsChangement() {
        if (this.mh.getHistory().size() > 0) {
            Piece iPiece = this.mh.getHistory().get(this.mh.getHistory().size() - 1).initialPiece;
            Piece fPiece = this.mh.getHistory().get(this.mh.getHistory().size() - 1).finalPiece;
            Boolean isRemoved = this.mh.getHistory().get(this.mh.getHistory().size() - 1).isRemoved;
            IChess.ChessPosition ip = this.mh.getHistory().get(this.mh.getHistory().size() - 1).initialPosition;
            IChess.ChessPosition fp = this.mh.getHistory().get(this.mh.getHistory().size() - 1).finalPosition;
            this.tab[ip.y][ip.x] = iPiece;
            this.tab[fp.y][fp.x] = fPiece;
            if (isRemoved) {
                this.rp.getRPList(fPiece.getColor()).remove(this.rp.getRPList(fPiece.getColor()).size() - 1);
            }
            this.mh.getHistory().remove(this.mh.getHistory().size() - 1);
            this.tab[ip.y][ip.x].decreaseNbMovement();
            return true;
        }
        return false;
    }

    public List<IChess.ChessType> getRemovePieces(IChess.ChessColor color) {

        return rp.getRPList(color);
    }

    public Board clone() {

        Board brd = new Board(
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

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                brd.setPiece(i, j, getPiece(i, j));
            }
        }

        return brd;
    }

    public ChessKingState getKingState(ChessColor color) {

        ChessPosition kingPosition = null;

        for (int i = 0; i < 8 && kingPosition == null; i++) {

            for (int j = 0; j < 8 && kingPosition == null; j++) {

                Piece currentPiece = this.getPiece(i, j);
                if (currentPiece != null) {
                    if (currentPiece.getType() == ChessType.TYP_KING && currentPiece.getColor() == color) {

                        kingPosition = new ChessPosition(j, i);

                    }
                }
            }
        }
        if (kingPosition == null) {
            return ChessKingState.KING_SAFE;
        }

        // FOr each row
        for (int i = 0; i < 8; i++) {
            // For each column
            for (int j = 0; j < 8; j++) {
                // Get piece for the current position j,i
                Piece currentPiece = this.getPiece(i, j);
                // If the piece exists (the cell is not empty)
                if (currentPiece != null) {
                    // If the color of the current piece is the enemy color
                    if (currentPiece.getColor() != color) {
                        // GEt position object for the current piece
                        ChessPosition currentPos = new ChessPosition(j, i);
                        // Get the possible moves of the enemy piece
                        List<ChessPosition> posList = currentPiece.getMoves(currentPos, this);
                        // For each possible move of the enemy
                        for (ChessPosition p : posList) {
                            // If the current king position is equals to the enemy destination, that means the king is threaten
                            if (p.equals(kingPosition)) {
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
}
