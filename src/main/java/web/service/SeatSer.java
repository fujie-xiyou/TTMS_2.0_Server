package web.service;

import web.idao.DAOFactory;
import web.idao.iSeatDAO;
import web.idao.iStudioDAO;
import web.model.Seat;

import java.util.List;

public class SeatSer {
    iSeatDAO iSeatDAO = DAOFactory.creatSeatDAO();
    public int add(Seat seat){
        return iSeatDAO.insert(seat);
    }
    public int addAll(List<Seat> seats){
        //批量添加座位
        return iSeatDAO.insert(seats);
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
