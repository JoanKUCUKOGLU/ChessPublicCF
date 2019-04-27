package fr.rphstudio.chess.interf;

import fr.rphstudio.chess.game.Board;

import java.util.List;

public interface IMove {

    List<IChess.ChessPosition> getPossibleMove(IChess.ChessPosition p, Board board); //An interface method that is called in each Type of move class

}


