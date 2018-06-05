package web.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.idao.DAOFactory;
import web.idao.iSeatDAO;
import web.model.CustomResp;
import web.model.Result;
import web.model.Seat;
import web.model.Studio;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping(value = "/seat" , produces = "text/html; charset=UTF-8")
public class SeatController {
    Gson json = new Gson();
    iSeatDAO iSeatDAO = DAOFactory.creatSeatDAO();//惊了  咋就架空Server层了!!!
    @RequestMapping("/addAll")
    public String addAll(@RequestBody String data){
        List<Seat> seats = json.fromJson(data,new TypeToken<List<Seat>>(){}.getType());
        if(iSeatDAO.insert(seats) > 0){
            return new CustomResp(new Result(),seats).toString();
        }else {
            return new CustomResp(new Result("服务器异常")).toString();
        }
    }
    @RequestMapping("/modifyAll")
    public String modifyAll(@RequestBody String data){
        List<Seat> seats = json.fromJson(data, new TypeToken<List<Seat>>(){}.getType());
        if(iSeatDAO.update(seats) > 0){
            return new CustomResp(new Result()).toString();
        }else {
            return new CustomResp(new Result("服务器异常")).toString();
        }
    }
    @RequestMapping("/deleteAll")
    public String deleteAll(@RequestBody String data){
        Studio studio = json.fromJson(data,Studio.class);
        if(iSeatDAO.delete(studio) > 0){
            return new CustomResp(new Result()).toString();
        }else {
            return new CustomResp(new Result("服务器异常")).toString();
        }
    }
}
