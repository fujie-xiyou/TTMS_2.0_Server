package web.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.idao.DAOFactory;
import web.idao.iStudioDAO;
import web.model.Account;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.util.Enumeration;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(produces = "text/html; charset=UTF-8")
public class TestController {
    @RequestMapping(value = "/test" ,method = GET)
    @ResponseBody
    public String main(HttpServletRequest request){
        try{
            request.setCharacterEncoding("UTF-8");
        }catch (Exception e){
        }
        Enumeration<String> enumer = request.getHeaderNames();
        while(enumer.hasMoreElements()){
            String key = enumer.nextElement();
            System.out.println(key+" "+request.getHeader(key));
        }
        return new Gson().toJson((Account)request.getSession().getAttribute("user"));
    }
}
