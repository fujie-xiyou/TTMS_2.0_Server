package web.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.model.Account;
import web.model.CustomResp;
import web.model.Result;
import web.model.enums.ACCOUNT_TYPE;
import web.service.AccountSer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(produces = "text/html; charset=UTF-8")
public class LoginController {
    @RequestMapping(value = "/login",method = GET)
    @ResponseBody
    public String test(HttpServletRequest request , HttpServletResponse response){
        try{
            request.setCharacterEncoding("UTF-8");
        }catch (Exception e){

        }
        request.getSession().setAttribute("user",new Account(0,ACCOUNT_TYPE.MANG,"zqn","0"));
        return new CustomResp(new Result(),"{对象}").toString();
    }
    @RequestMapping(value = "/login",method = POST)
    @ResponseBody
    public  String login(@RequestBody String data, HttpServletRequest request){
        Result result = new Result();
        Account account = new Gson().fromJson(data,Account.class);
        AccountSer accountSer = new AccountSer();
        CustomResp res = new CustomResp();
        if(accountSer.fetch("username = '" + account.getUsername() + "'").isEmpty()){
            result.addReason("用户不存在");
            result.setStatus(false);
            res.setResultJSON(result);
            return res.toString();
        }

        List<Account> accounts = accountSer.fetch("username = '" + account.getUsername()+"' AND password = '"+account.getPassword()+"'");

        if(!accounts.isEmpty()){
            //用户名密码验证成功
            account = accounts.get(0);
            request.getSession().setAttribute("user",account);
            res.setResultJSON(result);
            res.setObjectJSON(account);
        }else {
            result.setStatus(false);
            result.addReason("用户名和密码不匹配");
            res.setResultJSON(result);
        }
        return  res.toString();
    }
}
