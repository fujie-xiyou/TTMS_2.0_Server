package web.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.model.CustomResp;
import web.model.Order;
import web.model.Result;
import web.service.OrderItemSer;
import web.service.OrderSer;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping(value = "/order" , produces = "text/html; charset=UTF-8")
public class OrderController {
    Gson json = new Gson();
    OrderSer orderSer= new OrderSer();
    @RequestMapping("fetchByID")
    public String fetchByID(@RequestBody String data){
        int id = json.fromJson(data,Integer.class);
        List<Order> orders = orderSer.fetch("id = "+id);
        if(orders.isEmpty()) return new CustomResp(new Result("订单id不存在")).toString();
        Order order = orders.get(0);
        order.setItems(new OrderItemSer().fetchByOrder(order));
        return new CustomResp(new Result(),order).toString();
    }
}
