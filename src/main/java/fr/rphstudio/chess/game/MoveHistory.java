package fr.rphstudio.chess.game;

import java.util.ArrayList;
import java.util.List;

public class MoveHistory {

    private List<MoveInfo> ListMoveInfo;

    public MoveHistory(){
        ListMoveInfo = new ArrayList<>();
    }

    public void AddMoveInfo(MoveInfo mi){
        ListMoveInfo.add(mi);
    }

    public List<MoveInfo>getHistory(){
        return ListMoveInfo;
    }
}
