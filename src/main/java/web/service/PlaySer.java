package web.service;

import web.idao.DAOFactory;
import web.idao.iPlayDAO;
import web.model.Play;

import java.util.List;

public class PlaySer {
    iPlayDAO iPlayDAO = DAOFactory.creatPlayDAO();
    public List<Play> fetchAll(){
        return iPlayDAO.select("");
    }
}
