package web.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.model.CustomResp;
import web.model.Play;
import web.model.Result;
import web.service.PlaySer;

@Controller
@ResponseBody
@RequestMapping(value = "/play",produces = "text/html; charset=UTF-8")
public class PlayController {
    PlaySer playSer = new PlaySer();
    Gson json = new Gson();
    @RequestMapping("/add")
    public String add(@RequestBody String date){
        Play play = json.fromJson(date,Play.class);
        if(!playSer.fetch("name = '"+play.getName()+"'").isEmpty()){
            return new CustomResp(new Result("剧目 \"" + play.getName()+"\"已经存在")).toString();
        }
        if(playSer.add(play) > 0){
            return  new CustomResp(new Result(),play).toString();
        }
        return new CustomResp(new Result("服务器异常")).toString();
    }
    @RequestMapping("/delete")
    public String delete(@RequestBody String date){
        Play play = json.fromJson(date,Play.class);
        if(playSer.delete(play) > 0) {
            return new CustomResp(new Result()).toString();

        }else {
            return new CustomResp(new Result("服务器异常")).toString();
        }
    }
    @RequestMapping("/modify")
    public String modify(@RequestBody String date){
        Play play = json.fromJson(date,Play.class);
        if(playSer.modify(play) > 0){
            return new CustomResp(new Result()).toString();
        }else {
            return new CustomResp(new Result("服务器异常")).toString();
        }
    }
    @RequestMapping("/fetchAll")
    public String fetchAll(){
        return new CustomResp(new Result(),playSer.fetchAll()).toString();
    }
}