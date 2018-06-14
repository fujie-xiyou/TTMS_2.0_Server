package web.service;

import web.idao.DAOFactory;
import web.idao.iOrderItemDAO;
import web.model.OrderItem;
import web.model.Order;
import java.util.List;

public class OrderItemSer {
    iOrderItemDAO iOrderItemDAO = DAOFactory.createOrderItem();
    public int add(OrderItem item){
        return iOrderItemDAO.insert(item);
    }
    public int add(List<OrderItem> items){
        return iOrderItemDAO.insert(items);
    }
    public int modify(List<OrderItem> items){
        return iOrderItemDAO.update(items);
    }
    public List<OrderItem> fetchByOrder(Order order){
        return iOrderItemDAO.select("orderID = "+ order.getId());
    }
    public List<OrderItem> fetchAll(){
        return iOrderItemDAO.select("");
    }
}
