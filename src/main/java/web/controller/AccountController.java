package web.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.model.Account;
import web.model.CustomResp;
import web.model.Result;
import web.service.AccountSer;

@Controller
@ResponseBody
@RequestMapping(value = "/account",produces = "text/html; charset=UTF-8")
public class AccountController {
    Gson json = new Gson();
    private static AccountSer accountSer = new AccountSer();
    @RequestMapping("/fetchAll")
    public String fetchAll(){
        return new CustomResp(new Result(),accountSer.fetchAll()).toString();
    }
    @RequestMapping("/add")
    public String add(@RequestBody String data){
        CustomResp res = new CustomResp();
        Account account = json.fromJson(data,Account.class);
        if(!accountSer.fetch("username = '"+account.getUsername()+"'").isEmpty()){
            res.setResultJSON(new Result("用户名已存在"));
        } else if(accountSer.add(account) > 0){
            //添加成功
            res.setResultJSON(new Result());
            res.setObjectJSON(account);
        }else {
            //这都能失败？
            res.setResultJSON(new Result("服务器异常"));
        }
        return res.toString();
    }
    @RequestMapping("/delete")
    public String delete(@RequestBody String data){
        CustomResp rtn = new CustomResp();
        Result result = new Result();
        Account account = json.fromJson(data,Account.class);
        if(accountSer.fetch("uid = '" + account.getUid() + "'").isEmpty()){
            result.addReason("用户不存在");
            result.setStatus(false);
            rtn.setResultJSON(result);
            return rtn.toString();
        }
        if(accountSer.delete(account.getUid()) > 0) {
            rtn.setResultJSON(result);
            return rtn.toString();
        }else {
            result.setStatus(false);
            result.addReason("服务器异常");
            rtn.setResultJSON(result);
        }
        return  null;
    }
    @RequestMapping("/modify")
    public String modify(@RequestBody String data){
        if(accountSer.modify(json.fromJson(data,Account.class)) > 0){
            return new CustomResp(new Result()).toString();
        }
        return  new CustomResp(new Result("服务器异常")).toString();
    }
}
