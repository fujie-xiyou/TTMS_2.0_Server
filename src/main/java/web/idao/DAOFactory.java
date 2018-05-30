package web.idao;

import web.dao.AccountDAO;
import web.dao.PlayDAO;
import web.dao.SeatDAO;
import web.dao.StudioDAO;
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
}
