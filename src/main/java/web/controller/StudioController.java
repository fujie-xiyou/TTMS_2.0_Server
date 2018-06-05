package web.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.model.CustomResp;
import web.model.Result;
import web.model.Studio;
import web.service.StudioSer;

@Controller
@ResponseBody
@RequestMapping(value = "/studio" , produces = "text/html; charset=UTF-8")
public class StudioController {
    private static StudioSer studioSer = new StudioSer();
    private static Gson json = new Gson();

    @RequestMapping("/fetchAll")
    public String fetchAll(){
        return new CustomResp(new Result(),studioSer.fetchAll()).toString();
    }

    @RequestMapping("/add")
    public String add(@RequestBody String data){
        CustomResp rtn = new CustomResp();
        Studio studio = json.fromJson(data,Studio.class);
        if(!studioSer.fetch("studio_name = '"+studio.getName()+"'").isEmpty()){
            rtn.setResultJSON(new Result("演出厅 \""+studio.getName()+"\" 已经存在"));
        } else if(studioSer.add(studio) > 0){
            //添加成功
            rtn.setResultJSON(new Result());
            rtn.setObjectJSON(studio);
        }else {
            rtn.setResultJSON(new Result("服务器异常"));
        }
        return rtn.toString();
    }
    @RequestMapping("/delete")
    public String delete(@RequestBody String data){
        Studio studio = json.fromJson(data,Studio.class);
        if(studioSer.delete(studio.getId()) > 0){
            return new CustomResp(new Result()).toString();
        }else {
            return new CustomResp(new Result("服务器异常")).toString();
        }
    }
}