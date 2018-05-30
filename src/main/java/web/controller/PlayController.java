package web.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.model.CustomResp;
import web.model.Result;
import web.service.PlaySer;

@Controller
@ResponseBody
@RequestMapping(value = "/play",produces = "text/html; charset=UTF-8")
public class PlayController {
    PlaySer playSer = new PlaySer();
    Gson json = new Gson();
    @RequestMapping("/fetchAll")
    public String fetchAll(){
        return new CustomResp(new Result(),playSer.fetchAll()).toString();
    }
}
