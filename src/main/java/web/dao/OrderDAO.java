package web.dao;

import com.sun.org.apache.xpath.internal.operations.Or;
import web.idao.iOrderDAO;
import web.model.Order;
import web.model.enums.ORDER_STATUS;
import web.model.enums.ORDER_TYPE;
import web.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.LinkedList;
import java.util.List;

public class OrderDAO implements iOrderDAO {
    @Override
    public int insert(Order order) {
        String sql = "INSERT INTO orders (datetime,uid,type,status,total) VALUES(?,?,?,?,?)";
        DBUtil db = new DBUtil();
        PreparedStatement pstmt = db.getPstmt(sql);
        try {
            pstmt.setString(1, order.getDateTime().toString());
            pstmt.setInt(2, order.getUid());
            pstmt.setString(3, order.getType().name());
            pstmt.setString(4, order.getStatus().name());
            pstmt.setInt(5,order.getTotal());
            int id = db.insert(pstmt);
            order.setId(id);
            db.close(pstmt);
            db.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Order order) {
        return 0;
    }

    @Override
    public int update(Order order) {
        String sql = "UPDATE orders SET status = '" + order.getStatus().name() + "' WHERE id = " + order.getId();
        DBUtil db = new DBUtil();
        db.openConnection();
        try {
            int rtn = db.execCommand(sql);
            db.close();
            return rtn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Order> select(String condt) {
        List<Order> orders = new LinkedList<>();
        String sql = "SELECT id,datetime,uid,type,status,total FROM orders";
        if(condt != null && !condt.isEmpty()){
            sql += " WHERE "+condt;
        }
        DBUtil db = new DBUtil();
        db.openConnection();
        try{
            ResultSet res = db.execQuery(sql);
            while (res.next()){
                Order order = new Order();
                order.setId(res.getInt("id"));
                order.setDateTime(LocalDateTime.parse(res.getString("datetime").split("\\.")[0],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                order.setUid(res.getInt("uid"));
                order.setType(ORDER_TYPE.valueOf(res.getString("type")));
                order.setStatus(ORDER_STATUS.valueOf(res.getString("status")));
                order.setTotal(res.getInt("total"));
                orders.add(order);
            }
            db.close(res);
            db.close();
            return orders;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
