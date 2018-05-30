package test;

import com.google.gson.Gson;
import web.idao.DAOFactory;
import web.model.Account;
import web.model.enums.ACCOUNT_TYPE;

import java.util.List;

public class Test {
    public static void main(String[] args){
        Account account = new Account(0,ACCOUNT_TYPE.MANG,"zqn","qwewqeqwe");
        System.out.println(new Gson().toJson((Object)account));

    }
}
