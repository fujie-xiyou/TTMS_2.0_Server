package web.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.model.CustomResp;
import web.model.Result;
import web.model.Schedule;
import web.service.ScheduleSer;

@Controller
@ResponseBody
@RequestMapping(value = "/schedule" , produces = "text/html; charset=UTF-8")
public class ScheduleController {
    private ScheduleSer scheduleSer = new ScheduleSer();
    private Gson json = new Gson();
    @RequestMapping("/add")
    public String add(@RequestBody String data){
        Schedule schedule = json.fromJson(data,Schedule.class);
        if(scheduleSer.add(schedule) > 0){
            return new CustomResp(new Result(),schedule).toString();
        }else {
            return new CustomResp(new Result("服务器异常")).toString();
        }
    }
    @RequestMapping("/delete")
    public String delete(@RequestBody String data){
        Schedule schedule = json.fromJson(data,Schedule.class);
        if(scheduleSer.delete(schedule) > 0){
            return new CustomResp(new Result()).toString();
        }else {
            return new CustomResp(new Result("服务器异常")).toString();
        }
    }
    @RequestMapping("modify")
    public String modify(@RequestBody String data){
        Schedule schedule = json.fromJson(data,Schedule.class);
        if(scheduleSer.modify(schedule) > 0){
            return new CustomResp(new Result()).toString();
        }else {
            return new CustomResp(new Result("服务器异常")).toString();
        }
    }
    @RequestMapping("/fetchAll")
    public String fetchAll(){
        return new CustomResp(new Result(),scheduleSer.fetchAll()).toString();
    }
}
