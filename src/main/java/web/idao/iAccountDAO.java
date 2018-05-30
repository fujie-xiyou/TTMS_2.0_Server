package web.idao;

import web.model.Account;

import java.util.List;

public interface iAccountDAO {
    public int insert(Account account);
    public int update(Account account);
    public int delete(int uid);
    public List<Account> select(String condt);

}
