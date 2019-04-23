package fr.rphstudio.chess.game;

public class Board {

    private static Board boarde;

    Board(){
    }

    public static Board getBoard() {
        if(Board.boarde == null){
            Board.boarde = new Board();
        }
        return Board.boarde;
    }

}
