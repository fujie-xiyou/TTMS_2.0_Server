package web.service;

import web.idao.DAOFactory;
import web.idao.iOrderDAO;
import web.model.Order;

import java.util.List;

public class OrderSer {
    iOrderDAO iOrderDAO = DAOFactory.createOrderDAO();
    public int add(Order order){
        return iOrderDAO.insert(order);
    }
    public int modify(Order order){
        return iOrderDAO.update(order);
    }
    public int delete(Order order){
        return iOrderDAO.delete(order);
    }
    public List<Order> fetch(String condt){
        return iOrderDAO.select(condt);
    }
    public List<Order> fetchAll(){
        return iOrderDAO.select("");
    }
    public void getItems(Order order){
        order.setItems(new OrderItemSer().fetchByOrder(order));
    }
}
