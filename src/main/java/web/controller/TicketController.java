package web.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import web.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.Request;
import web.model.Account;
import web.model.CustomResp;
import web.model.Result;
import web.model.Ticket;
import web.service.TicketSer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping(value = "/ticket" , produces = "text/html; charset=UTF-8")
public class TicketController {
    Gson json = new Gson();
    TicketSer ticketSer = new TicketSer();
    @RequestMapping("/lockedTicket")
    public String lockedTicket(@RequestBody String data){
        Ticket ticket = json.fromJson(data,Ticket.class);
        if(ticketSer.lockTicket(ticket) > 0){
            return new CustomResp(new Result()).toString();
        }
        return new CustomResp(new Result("锁定失败")).toString();
    }
    @RequestMapping("/unLockTicket")
    public String unLockTicket(@RequestBody String data){
        Ticket ticket = json.fromJson(data,Ticket.class);
        if(ticketSer.unLockTicket(ticket) > 0){
            return new CustomResp(new Result()).toString();
        }
        return new CustomResp(new Result("解锁失败")).toString();
    }
    @RequestMapping("/timeStamp")
    public String timeStamp(){
        return new CustomResp(new Result(),ticketSer.timeStamp()).toString();
    }
    @RequestMapping("/purchaseTicket")
    public String purchaseTicket(@RequestBody String data, HttpServletRequest request){
        List<Ticket> tickets = json.fromJson(data,new TypeToken<List<Ticket>>(){}.getType());
        Account loginUser = (Account) request.getSession().getAttribute("user");
        Order order = ticketSer.purchaseTicket(tickets,loginUser);
        if(order != null){
            return new CustomResp(new Result(),order).toString();
        }else {
            return new CustomResp(new Result("服务器异常")).toString();
        }
    }
    @RequestMapping("/returnTicket")
    public String returnTicket(@RequestBody String data, HttpServletRequest request){
        Order order = json.fromJson(data,Order.class);
        Account loginUser = (Account) request.getSession().getAttribute("user");
        return new CustomResp(new Result(),ticketSer.returnTicket(order,loginUser)).toString();
    }
    @RequestMapping("/fetchByID")
    public String fetchByID(@RequestBody String data){
        int id = json.fromJson(data,Integer.class);
        List<Ticket> tickets = ticketSer.fetch("id = "+id);
        if(tickets.isEmpty()) return new CustomResp(new Result("票id不存在")).toString();
        return new CustomResp(new Result(),tickets.get(0)).toString();
    }
}
