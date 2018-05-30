package web.service;

import web.model.Seat;

import java.util.List;

public class SeatSer {
    public int add(Seat seat){
        return 1;
    }
    public int addAll(List<Seat> seats){
        //批量添加座位
        return 1;
    }
    public int delete(Seat seat){
        return 0;
    }
    public int deleteByStudioID(int studioID){
        return 1;
    }
    public int modify(Seat seat){
        return 0;
    }
    public Seat[][] fetchByStudioID(int studioID){
        return null;
    }

}
