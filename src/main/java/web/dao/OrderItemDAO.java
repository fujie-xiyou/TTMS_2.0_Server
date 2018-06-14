package web.dao;

import web.idao.iOrderItemDAO;
import web.model.OrderItem;
import web.model.enums.ORDER_ITEM_STATUS;
import web.util.DBUtil;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class OrderItemDAO implements iOrderItemDAO {
    @Override
    public int insert(OrderItem item) {
        return 0;
    }

    @Override
    public int insert(List<OrderItem> items) {
        StringBuffer sqlBuf = new StringBuffer("INSERT INTO order_items (orderID,ticketID,status,price) VALUES");
        for (OrderItem item : items) {
            sqlBuf.append("(" + item.getOrderID() + "," + item.getTicketID() + ",'" + item.getStatus().name() + "'," + item.getPrice() + "),");
        }
        sqlBuf.deleteCharAt(sqlBuf.length() - 1);
        String sql = sqlBuf.toString();
        DBUtil db = new DBUtil();
        db.openConnection();
        try {
            ResultSet res = db.getInsertObjectIDs(sql);
            for (OrderItem item : items) {
                if (!res.next()) break;
                item.setId(res.getInt(1));
            }
            db.close(res);
            db.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(OrderItem orderItem) {
        return 0;
    }

    @Override
    public int update(List<OrderItem> items) {
        StringBuffer sqlBuf = null;
        if (items != null && !items.isEmpty()) {
            sqlBuf = new StringBuffer("UPDATE order_items SET status = '"+items.get(0).getStatus().name()+"' WHERE id IN (");
        }
        for (OrderItem item : items){
            sqlBuf.append(item.getId()+",");
        }
        if (sqlBuf.charAt(sqlBuf.length() - 1) == ',') sqlBuf.setCharAt(sqlBuf.length()-1, ')');
        else sqlBuf.append(')');
        String sql = sqlBuf.toString();
        DBUtil db = new DBUtil();
        db.openConnection();
        try{
            int rtn = db.execCommand(sql);
            db.close();
            return rtn;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<OrderItem> select(String condt) {
        List<OrderItem> items = new LinkedList<>();
        String sql = "SELECT id,orderID,ticketID,status,price FROM order_items";
        if (condt != null && !condt.isEmpty()){
            sql += " WHERE "+condt;
        }
        DBUtil db = new DBUtil();
        db.openConnection();
        try{
            ResultSet res = db.execQuery(sql);
            while (res.next()){
                OrderItem item = new OrderItem();
                item.setId(res.getInt("id"));
                item.setOrderID(res.getInt("orderID"));
                item.setTicketID(res.getInt("ticketID"));
                item.setStatus(ORDER_ITEM_STATUS.valueOf(res.getString("status")));
                item.setPrice(res.getInt("price"));
                items.add(item);
            }
            db.close(res);
            db.close();
            return items;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
