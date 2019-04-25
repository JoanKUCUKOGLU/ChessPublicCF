package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.List;

import static fr.rphstudio.chess.interf.IChess.ChessColor.CLR_BLACK;
import static fr.rphstudio.chess.interf.IChess.ChessColor.CLR_WHITE;
import static fr.rphstudio.chess.interf.IChess.ChessType.*;

public class Board{

    private Piece[][] tab;

    private RemovedPieces rp;

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
    ){
        this.tab = new Piece[height][width];

        this.rp = new RemovedPieces();

        // Setting black and white paws
        for(int i = 0; i<8; i++){
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

    public int getNbRemainingPieces(IChess.ChessColor color) {

        int count = 0;

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++) {

                if(this.tab[i][j] != null) {

                    if (this.tab[i][j].getColor() == color) {
                        count += 1;
                    }

                }

            }
        }

        return count;

    }

    public void movePiece(IChess.ChessPosition pSource, IChess.ChessPosition pDest){
        Piece destPiece = this.tab[pDest.y][pDest.x];
        Piece srcPiece = this.tab[pSource.y][pSource.x];
        if(destPiece != null && srcPiece != null){
            if(destPiece.getColor() != srcPiece.getColor()){
                this.rp.addRemovedPieces(destPiece);
            }
        }
        this.tab[pSource.y][pSource.x].increaseNbMovement();
        if(pDest.y == 0 && getPiece(pSource.y,pSource.x).getType() == TYP_PAWN && getPiece(pSource.y,pSource.x).getColor() == CLR_WHITE ||
                pDest.y == 7 && getPiece(pSource.y,pSource.x).getType() == TYP_PAWN && getPiece(pSource.y,pSource.x).getColor() == CLR_BLACK){
            this.tab[pDest.y][pDest.x] = new Piece(getPiece(pSource.y,pSource.x).getColor(), TYP_QUEEN, new QueenMove());
        }
        else{
            this.tab[pDest.y][pDest.x] = getPiece(pSource.y,pSource.x);
        }
        this.tab[pSource.y][pSource.x] = null;
    }

    public List<IChess.ChessType> getRemovePieces(IChess.ChessColor color){
        return rp.getRPList(color);
    }
}
