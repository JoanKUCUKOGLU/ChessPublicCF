package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import static fr.rphstudio.chess.interf.IChess.ChessColor.CLR_BLACK;
import static fr.rphstudio.chess.interf.IChess.ChessColor.CLR_WHITE;
import static fr.rphstudio.chess.interf.IChess.ChessType.*;

public class Board {

    private Piece[][] tab;

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

        // Setting black and white paws
        for(int i = 0; i<8; i++){
            tab[y_BlackPaws][i] = new Piece(CLR_BLACK, TYP_PAWN);

            tab[y_WhitePaws][i] = new Piece(CLR_WHITE, TYP_PAWN);
        }

        tab[y_BlackPiece][x_LeftRook] = new Piece(CLR_BLACK, TYP_ROOK);
        tab[y_BlackPiece][x_RightRook] = new Piece(CLR_BLACK, TYP_ROOK);
        tab[y_WhitePiece][x_LeftRook] = new Piece(CLR_WHITE, TYP_ROOK);
        tab[y_WhitePiece][x_RightRook] = new Piece(CLR_WHITE, TYP_ROOK);

        tab[y_BlackPiece][x_LeftKnight] = new Piece(CLR_BLACK, TYP_KNIGHT);
        tab[y_BlackPiece][x_RightKnight] = new Piece(CLR_BLACK, TYP_KNIGHT);
        tab[y_WhitePiece][x_LeftKnight] = new Piece(CLR_WHITE, TYP_KNIGHT);
        tab[y_WhitePiece][x_RightKnight] = new Piece(CLR_WHITE, TYP_KNIGHT);

        tab[y_BlackPiece][x_LeftBishop] = new Piece(CLR_BLACK, TYP_BISHOP);
        tab[y_BlackPiece][x_RightBishop] = new Piece(CLR_BLACK, TYP_BISHOP);
        tab[y_WhitePiece][x_LeftBishop] = new Piece(CLR_WHITE, TYP_BISHOP);
        tab[y_WhitePiece][x_RightBishop] = new Piece(CLR_WHITE, TYP_BISHOP);

        tab[y_BlackPiece][x_Queen] = new Piece(CLR_BLACK, TYP_QUEEN);
        tab[y_WhitePiece][x_Queen] = new Piece(CLR_WHITE, TYP_QUEEN);

        tab[y_BlackPiece][x_King] = new Piece(CLR_BLACK, TYP_KING);
        tab[y_WhitePiece][x_King] = new Piece(CLR_WHITE, TYP_KING);
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
}
