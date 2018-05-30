package web.service;

import web.idao.DAOFactory;
import web.idao.iAccountDAO;
import web.model.Account;

import java.util.List;

public class AccountSer {
    private iAccountDAO accDAO = DAOFactory.creatAccountDAO();

    public int add(Account acc){
        return accDAO.insert(acc);
    }
    public int modify(Account acc){
        return accDAO.update(acc);
    }
    public int delete(int uid){
        return accDAO.delete(uid);
    }
    public List<Account> fetch(String condt){
        List<Account> rts = null;
        return accDAO.select(condt);
    }
    public List<Account> fetchAll(){
        return accDAO.select("");
    }


}
