// Board Class that generate the space for the Chess Game //

package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import org.lwjgl.Sys;
import java.util.ArrayList;
import javax.crypto.spec.PSource;
import java.util.List;

import static fr.rphstudio.chess.interf.IChess.*;
import static fr.rphstudio.chess.interf.IChess.ChessColor.CLR_BLACK;
import static fr.rphstudio.chess.interf.IChess.ChessColor.CLR_WHITE;
import static fr.rphstudio.chess.interf.IChess.ChessType.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Math.abs;

public class Board {

    private Piece[][] tab; // Variable that create a Piece, our Chess Game will be a table wich each box is a Piece

    private RemovedPieces rp; // Variable that return the list of removed pieces

    private MoveInfo mi; // Variable for create a MoveInfo (go to MoveInfo class)

    private MoveHistory mh; // Variable that return the list of Moves of the current game

    private long timeBlack; // Variable that take the time of Black's Player

    private long timeWhite; // Variable that take the time of White's Player

    private long depart; // Variable for timer initialisation

    {// Try to getting list of all time of the current player

        //private List<Long> WhitetimeList;
        //private List<Long> BlacktimeList;
    }


    public Board(        // Board Constructor, with size of the Chess Table and all pieces.
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
        // Insranciations //

        this.tab = new Piece[height][width];  // Instanciation of a new Pieces

        this.rp = new RemovedPieces(); // Instanciation of the list of removed pieces

        this.mh = new MoveHistory(); // Instanciation of the list of moves

        // Setting black and white paws
        for (int i = 0; i < 8; i++) {
            tab[y_BlackPaws][i] = new Piece(CLR_BLACK, TYP_PAWN, new PawsMove());

            tab[y_WhitePaws][i] = new Piece(CLR_WHITE, TYP_PAWN, new PawsMove());
        }

        // Setting Rooks //
        tab[y_BlackPiece][x_LeftRook] = new Piece(CLR_BLACK, TYP_ROOK, new RookMove());
        tab[y_BlackPiece][x_RightRook] = new Piece(CLR_BLACK, TYP_ROOK, new RookMove());
        tab[y_WhitePiece][x_LeftRook] = new Piece(CLR_WHITE, TYP_ROOK, new RookMove());
        tab[y_WhitePiece][x_RightRook] = new Piece(CLR_WHITE, TYP_ROOK, new RookMove());

        // Setting Knight //
        tab[y_BlackPiece][x_LeftKnight] = new Piece(CLR_BLACK, TYP_KNIGHT, new KnightMove());
        tab[y_BlackPiece][x_RightKnight] = new Piece(CLR_BLACK, TYP_KNIGHT, new KnightMove());
        tab[y_WhitePiece][x_LeftKnight] = new Piece(CLR_WHITE, TYP_KNIGHT, new KnightMove());
        tab[y_WhitePiece][x_RightKnight] = new Piece(CLR_WHITE, TYP_KNIGHT, new KnightMove());

        // Setting Bishop //
        tab[y_BlackPiece][x_LeftBishop] = new Piece(CLR_BLACK, TYP_BISHOP, new BishopMove());
        tab[y_BlackPiece][x_RightBishop] = new Piece(CLR_BLACK, TYP_BISHOP, new BishopMove());
        tab[y_WhitePiece][x_LeftBishop] = new Piece(CLR_WHITE, TYP_BISHOP, new BishopMove());
        tab[y_WhitePiece][x_RightBishop] = new Piece(CLR_WHITE, TYP_BISHOP, new BishopMove());

        // Setting Queen //
        tab[y_BlackPiece][x_Queen] = new Piece(CLR_BLACK, TYP_QUEEN, new QueenMove());
        tab[y_WhitePiece][x_Queen] = new Piece(CLR_WHITE, TYP_QUEEN, new QueenMove());

        // Setting Kings //
        tab[y_BlackPiece][x_King] = new Piece(CLR_BLACK, TYP_KING, new KingMove());
        tab[y_WhitePiece][x_King] = new Piece(CLR_WHITE, TYP_KING, new KingMove());

        this.timeBlack = 0; //Setting time of Black's Players
        this.timeWhite = 0; //Setting time of White's Players
        this.depart = System.currentTimeMillis(); //Setting initialisation of the chrono

        {//Try to getting list of all time of the current player

            //this.WhitetimeList= new ArrayList<Long>();
            //this.BlacktimeList= new ArrayList<Long>();
        }

    }

    public Piece[][] getTab() {
        return tab;
    }

    public void setTab(Piece[][] tab) {
        this.tab = tab;
    }

    public Piece getPiece(int y, int x) { // Get Method for Get a Piece with this coords
        return this.tab[y][x];
    }

    public void setPiece(int y, int x, Piece piece) { // Set a Piece whith coords
        this.tab[y][x] = piece;
    }

    public int getNbRemainingPieces(IChess.ChessColor color) { // Method for get the number of pieces on the chess Table of current game

        int count = 0; // Initialize a counter. He'll count each piece

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) { // Double loop for Browse the chess Table, first for lines, second for columns

                if (this.tab[i][j] != null) { // Check if the box of the Chess Table is empty (Here empty mean no Piece)

                    if (this.tab[i][j].getColor() == color) { // If the box has a Piece, check if her color equals the color in parameter
                        count += 1; // If these two colors are the same, increment the counter
                    }
                }
            }
        }
        return count; // Return the total of pieces for each color
    }

    public void movePiece(IChess.ChessPosition pSource, IChess.ChessPosition pDest) { // Method that move a current Piece to a selected destination

        // In the case of a Piece is eaten by another //

        Piece destPiece = this.tab[pDest.y][pDest.x]; // Assignment of the Destination Piece (Where the piece will be)
        Piece srcPiece = this.tab[pSource.y][pSource.x]; // Assignment of the Source Piece (Where the piece is actualy)
        this.mi = new MoveInfo(pSource, pDest, this, this.getPlayerDuration(srcPiece.getColor(), true)); //Create a move Info for save the move of the piece
        this.mh.AddMoveInfo(this.mi); // And Add the move info in the list of move info

        if (destPiece != null && srcPiece != null) { // Check if pieces aren't null, if the Destination Piece is not null, that meaan that there is already a Piece in the destination place
            if (destPiece.getColor() != srcPiece.getColor()) { // If the color of the destination Piece is different to the current piece, it's mean that the destination piece is an enemy of the current piece
                this.rp.addRemovedPieces(destPiece); // So, the Destination Piece will be eat by the current Piece. The dead piece is add to the list of removed pieces
                this.mi.isRemoved = true; // In the moveInfo, we can notice that the piece is eaten
            }
        }

        // In the case of a simple move (Work after the removed piece part too) //

        this.tab[pSource.y][pSource.x].increaseNbMovement(); // The number of the Piece's Movements increase
        this.tab[pDest.y][pDest.x] = getPiece(pSource.y, pSource.x); // The piece in the destination box is replace by the source Piece
        this.tab[pSource.y][pSource.x] = null; // And the box with source Piece is now emptty

        // In the case of the special move "En Passant" //

        if (srcPiece.getType() == TYP_PAWN &&  // Check if the current piece is a Pawn (The En Passant move work only on Pawn)
                (destPiece == null) &&  // Check is the box of destination is empty (With no Piece's Object)
                (abs(pSource.y - pDest.y) == 1 && abs(pSource.x - pDest.x) == 1)) {  // Check if there is a difference between current and destination Piece
            Piece passPiece = this.tab[pSource.y][pDest.x]; // If it's true, get the coords of the enemy piece, that will be eat
            this.rp.addRemovedPieces(passPiece);// The enemy piece is eaten
            this.mi.finalPiece = passPiece;// Re-assignement of the finalMove of the current MoveInfo
            this.mi.enPassant = true;// Notice that the enemy piece is eaten by an "En Passant" Move
            this.tab[pSource.y][pDest.x] = null; // Set the initial position of the current piece to null
        }

        { //Try to getting list of all time of the current player
           /*
        if(srcPiece.getColor() == CLR_WHITE){
            this.WhitetimeList.add(this.timeWhite);
        }else{
            this.BlacktimeList.add(this.timeBlack);*/
        }

        Boolean isMoveRoque = FALSE;

        if (srcPiece != null && destPiece != null) {

            if (srcPiece.getType() == TYP_KING && srcPiece.getNbMovement() == 0 && destPiece.getType() == TYP_ROOK && destPiece.getNbMovement() == 0) {

                isMoveRoque = TRUE;
                if (pSource.x - pDest.x == -3) {


                    this.tab[pSource.y][pSource.x].increaseNbMovement();

                    this.tab[pSource.y][pSource.x + 2] = getPiece(pSource.y, pSource.x);
                    this.tab[pSource.y][pSource.x] = null;

                    this.tab[pSource.y][pSource.x + 1] = getPiece(pSource.y, 7);
                    this.tab[pSource.y][7] = null;

                } else {

                    this.tab[pSource.y][pSource.x].increaseNbMovement();

                    this.tab[pSource.y][pSource.x - 2] = getPiece(pSource.y, pSource.x);
                    this.tab[pSource.y][pSource.x] = null;

                    this.tab[pDest.y][pSource.x - 1] = getPiece(pSource.y, 0);
                    this.tab[pSource.y][0] = null;

                }
                mi.isRoque = true;
            }
        }

        if (isMoveRoque == FALSE) {
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
                this.mi.finalPiece = passPiece;
                this.mi.enPassant = true;
                this.tab[pSource.y][pDest.x] = null;
            }

        }

    }

    public boolean Rewind() { // Method for go back in a case of a bad move (ot for cheat)

        if (this.mh.getHistory().size() > 0) { // Check if the List of Move Infos isn't empty
            // Get each parameter of the current item of the list (here the last one) //
            Boolean isRoque = this.mh.getHistory().get(this.mh.getHistory().size() - 1).isRoque;
            Piece iPiece = this.mh.getHistory().get(this.mh.getHistory().size() - 1).initialPiece; // Initial Piece
            Piece fPiece = this.mh.getHistory().get(this.mh.getHistory().size() - 1).finalPiece; // Final Piece
            Boolean isRemoved = this.mh.getHistory().get(this.mh.getHistory().size() - 1).isRemoved; // IsRemoved
            Boolean enPassant = this.mh.getHistory().get(this.mh.getHistory().size() - 1).enPassant; // En Passant
            IChess.ChessPosition ip = this.mh.getHistory().get(this.mh.getHistory().size() - 1).initialPosition; // Initial Position
            IChess.ChessPosition fp = this.mh.getHistory().get(this.mh.getHistory().size() - 1).finalPosition; // Final Position
            long lastTime = this.mh.getHistory().get(this.mh.getHistory().size() - 1).time;

            { //Try to getting list of all time of the current player

               /*
            if(this.BlacktimeList.size() > 0) {
                bLastTime = this.BlacktimeList.get(this.BlacktimeList.size() - 1);
            }
            if(this.WhitetimeList.size() > 0) {
                wLastTime = this.WhitetimeList.get(this.WhitetimeList.size() - 1);
            }
            */
            }

            this.tab[ip.y][ip.x] = iPiece; //At the initialPosition, pose the initial Piece;

            if (isRemoved) { // Check if the move has an isRemoved element and if it's true
                this.rp.getRPList(fPiece.getColor()).remove(this.rp.getRPList(fPiece.getColor()).size() - 1); // Remove of last move in the Move Historic
                this.tab[fp.y][fp.x] = fPiece; // At the destinationPosition, pose the destination Piece)
            } else if(enPassant){// Check if the move has En en Passant element and if it's true
                this.rp.getRPList(fPiece.getColor()).remove(this.rp.getRPList(fPiece.getColor()).size()-1); // Same comportement that isRemoved
                this.tab[ip.y][fp.x] = fPiece; // At the current stzy, podse the final Piece
                this.tab[fp.y][fp.x] = null;
            } else if (isRoque) {
                this.tab[fp.y][fp.x] = fPiece;

                if (ip.x - fp.x == -3) {
                    this.tab[ip.y][ip.x + 2] = null;
                    this.tab[ip.y][ip.x + 1] = null;
                } else {
                    this.tab[ip.y][ip.x - 2] = null;
                    this.tab[ip.y][ip.x - 1] = null;
                }


            } else {
                this.tab[fp.y][fp.x] = null;
            }

            if(iPiece.getColor() == CLR_BLACK){ // Check If l it's of the current piece
                this.timeWhite += lastTime; // Renit the time of the cronos
                //this.WhitetimeList.remove(this.WhitetimeList.size() - 1); // Test //
            }else{
                this.timeBlack += lastTime; // Same
                //this.BlacktimeList.remove(this.BlacktimeList.size() - 1); // Test //
            }

            this.mh.getHistory().remove(this.mh.getHistory().size() - 1);// Remove the last one move of the list movie
            this.tab[ip.y][ip.x].decreaseNbMovement(); // Decrease the movement of this Piece
            return true;
        }
        return false;
    }

    public List<IChess.ChessType> getRemovePieces(IChess.ChessColor color) { // Methods for getting The list of the Removed Piece

        return rp.getRPList(color); // Return a list
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
    } // Create a Clone of the board.

    public ChessKingState getKingState(ChessColor color) { // Methods for getting the State of the kinf (Safe, In check, checkmated)

        ChessPosition kingPosition = null; // Initialize the KingPosition to null

        for (int i = 0; i < 8 && kingPosition == null; i++) {

            for (int j = 0; j < 8 && kingPosition == null; j++) {// An other double loop for browse the chesseTable

                Piece currentPiece = this.getPiece(i, j); // Take the piece of each box
                if (currentPiece != null) { // If the Piece isn't null
                    if (currentPiece.getType() == ChessType.TYP_KING && currentPiece.getColor() == color) { // Check if the piece is a King and had the same color of the color parameter

                        kingPosition = new ChessPosition(j, i); // Set the King position to new coords

                    }
                }
            }
        }
        if (kingPosition == null) {
            return ChessKingState.KING_SAFE; //If King Position is null, that means that the king is save
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
                        // Get position object for the current piece
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


    public long getPlayerDuration(ChessColor color, boolean isPlaying) { // Methods that's return the Player time for each player
        if(isPlaying){ // If playing is true is for Check who is playing
            if(color == CLR_BLACK){ // If the black Player play
                this.timeBlack = System.currentTimeMillis() - this.depart - this.timeWhite; //Start and Show the time of playing
                return this.timeBlack;
            }
            else{ // If the White Player play
                this.timeWhite = System.currentTimeMillis() - this.depart - this.timeBlack; // Idem
                return this.timeWhite;
            }
        }
        return 0;
    }
}
