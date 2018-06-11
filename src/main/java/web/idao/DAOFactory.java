package web.idao;

import web.dao.*;
import web.model.Studio;

public class DAOFactory {
    public static iStudioDAO creatStudioDAO(){
        return new StudioDAO();
    }
    public static iAccountDAO creatAccountDAO(){
        return new AccountDAO();
    }
    public static iSeatDAO creatSeatDAO(){
        return new SeatDAO();
    }
    public static iPlayDAO creatPlayDAO(){
        return new PlayDAO();
    }
    public static iScheduleDAO creatScheduleDAO(){
        return new ScheduleDAO();
    }
    public static iTicketDAO createTicketDAO(){
        return null;
    }
}
