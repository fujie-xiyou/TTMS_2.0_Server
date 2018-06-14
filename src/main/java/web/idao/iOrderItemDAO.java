package web.idao;

import web.model.OrderItem;

import java.util.List;

public interface iOrderItemDAO {
    public int insert(OrderItem item);
    public int insert(List<OrderItem> items);
    public int delete(OrderItem orderItem);
    public int update(List<OrderItem> items);
    public List<OrderItem> select(String condt);
}
