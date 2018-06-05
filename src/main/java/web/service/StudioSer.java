package web.service;

import web.idao.DAOFactory;
import web.idao.iStudioDAO;
import web.model.Seat;
import web.model.Studio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StudioSer {
    private iStudioDAO stuDAO=DAOFactory.creatStudioDAO();
    private SeatSer seatSer = new SeatSer();
    public int add(Studio stu){
        Seat[][] seatss = stu.getSeats();
        List<Seat> seats = new ArrayList<>();
        for(Seat[] seats1 : seatss){
            seats.addAll(Arrays.asList(seats1));
        }
        if(stuDAO.insert(stu) > 0){
            for(Seat seat : seats){
                seat.setstudioID(stu.getId());
            }
            return seatSer.addAll(seats);
        }
        return 0;
    }

    public int modify(Studio stu){
        return stuDAO.update(stu);
    }

    public int delete(int ID){
        return stuDAO.delete(ID) * seatSer.deleteByStudioID(ID);
    }

    public List<Studio> fetch(String condt){
        return stuDAO.select(condt);
    }

    public List<Studio> fetchAll(){
        return stuDAO.select("");
    }
}
