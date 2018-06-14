package web.idao;

import web.model.Order;

import java.util.List;

public interface iOrderDAO {
    public int insert(Order order);
    public int delete(Order order);
    public int update(Order order);
    public List<Order> select(String condt);
}
