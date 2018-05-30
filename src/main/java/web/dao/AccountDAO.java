package web.dao;

import web.idao.iAccountDAO;
import web.model.Account;
import web.model.enums.ACCOUNT_TYPE;
import web.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AccountDAO implements iAccountDAO {
    @Override
    public int insert(Account account) {
        try{
            String sql = "insert into accounts(type, username, password ) values('"
                    +account.getType().name()+"','"
                    +account.getUsername()+"','"
                    +account.getPassword() +"')";
            DBUtil db = new DBUtil();
            db.openConnection();
            ResultSet rst = db.getInsertObjectIDs(sql);
            if(rst != null && rst.first()){
                account.setUid(rst.getInt(1));
            }
            db.close(rst);
            db.close();
            return 1;

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Account account) {
        int rtn = 0;
        try{
            String sql = "update accounts set " +
                    "type = '" + account.getType().name()
                    +"', username = '" + account.getUsername()+
                    "', password = '" + account.getPassword()+
                    "' where uid = " + account.getUid();
            DBUtil db = new DBUtil();
            db.openConnection();
            rtn = db.execCommand(sql);
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return rtn;
    }

    @Override
    public int delete(int uid) {

        int rtn = 0;
        try {
            String sql = "delete from accounts where uid = " + uid;
            DBUtil db = new DBUtil();
            db.openConnection();
            rtn = db.execCommand(sql);
            db.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rtn;
    }
    @Override
    public List<Account> select(String condt) {
        List<Account> accounts = new ArrayList<>();
        try {
            String sql = "SELECT uid, type, username, password FROM accounts";
            condt.trim();
            if(!condt.isEmpty()) sql += " where " + condt;
           // System.out.println(sql);
            DBUtil db = new DBUtil();
            db.openConnection();
            ResultSet rst = db.execQuery(sql);
            if(rst != null){
                while (rst.next()){
                    Account account = new Account(
                            rst.getInt("uid"),
                            ACCOUNT_TYPE.valueOf(rst.getString("type")),
                            rst.getString("username"),
                            rst.getString("password")
                            );
                    accounts.add(account);
                }
            }
            db.close(rst);
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return accounts;
    }
}
