package web.service;

import web.idao.DAOFactory;
import web.idao.iPlayDAO;
import web.model.Play;

import java.util.List;

public class PlaySer {
    iPlayDAO iPlayDAO = DAOFactory.creatPlayDAO();
    public int add(Play play){
        return iPlayDAO.insert(play);
    }
    public int delete(Play play){
        return iPlayDAO.delete(play.getId());
    }
    public int modify(Play play){
        return iPlayDAO.update(play);
    }
    public List<Play> fetch(String condt){
        return iPlayDAO.select(condt);
    }
    public List<Play> fetchAll(){
        return iPlayDAO.select("");
    }
}
