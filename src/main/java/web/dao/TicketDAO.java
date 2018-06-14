package web.dao;

import web.idao.iTicketDAO;
import web.model.Ticket;
import web.model.enums.TICKET_STATUS;
import web.util.DBUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements iTicketDAO {
    @Override
    public int insert(Ticket ticket) {
        try {
            String sql = "insert into tickets(scheduleID,price,seat_row,seat_col,status)values('"
                    + ticket.getScheduleID() + "','"
                    + ticket.getPrice() + "','"
                    + ticket.getSeat_row() + ",'"
                    + ticket.getSeat_col() + ",'"
                    + ticket.getStatus() + "')";
            DBUtil db = new DBUtil();
            db.openConnection();
            ResultSet rst = db.getInsertObjectIDs(sql);
            if (rst != null && rst.first()) {
                ticket.setId(rst.getInt(1));
            }
            db.close(rst);
            db.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insert(List<Ticket> tickets) {
        StringBuffer sqlBuf = new StringBuffer("INSERT INTO ticket(scheduleID,price,seat_row,seat_col,status) values");
        for (Ticket ticket : tickets) {
            sqlBuf.append("(" + ticket.getScheduleID() + ","
                    + ticket.getPrice() + ","
                    + ticket.getSeat_row() + ","
                    + ticket.getSeat_col() + ",'"
                    + ticket.getStatus() + "'),");
        }
        sqlBuf.deleteCharAt(sqlBuf.length() - 1);
        String sql = sqlBuf.toString();
        DBUtil db = new DBUtil();
        db.openConnection();
        try {
            ResultSet res = db.getInsertObjectIDs(sql);
            for (Ticket ticket : tickets) {
                if (!res.next()) break;
                ticket.setId(res.getInt(1));
            }
            db.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Ticket ticket) {
        int rtn = 0;
        try {
            String sql = "delete from ticket where id = " + ticket.getId();
            DBUtil db = new DBUtil();
            db.openConnection();
            rtn = db.execCommand(sql);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn;
    }

    @Override
    public int update(Ticket ticket) {
        DBUtil db = new DBUtil();
        db.openConnection();
        int rtn = 1;
        try {
            String sql = "UPDATE ticket SET status = '" + ticket.getStatus() +
                    "' , locked_uid = " + ticket.getLockedUID() +
                    " , locked_time = " + ticket.getLockedTime() +
                    " WHERE id = " + ticket.getId();
            rtn *= db.execCommand(sql);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn;
    }

    @Override
    public int update(List<Ticket> tickets) {
        //批量修改了票的状态
        StringBuffer sqlBuf = null;
        if (tickets != null && !tickets.isEmpty()) {
            sqlBuf = new StringBuffer("UPDATE ticket set status = '" + tickets.get(0).getStatus() + "', locked_uid = "
                    +tickets.get(0).getLockedUID() +",locked_time = "+tickets.get(0).getLockedTime()+" WHERE id IN (");
        }
        for (Ticket ticket : tickets) {
            sqlBuf.append(ticket.getId() + ",");
        }
        if (sqlBuf.charAt(sqlBuf.length() - 1) == ',') sqlBuf.setCharAt(sqlBuf.length()-1, ')');
        else sqlBuf.append(')');
        String sql = sqlBuf.toString();
        System.out.println(sql);
        DBUtil db = new DBUtil();
        db.openConnection();
        try {
            int rtn =  db.execCommand(sql);
            db.close();
            return rtn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Ticket> select(String condt) {
        List<Ticket> tickets = new ArrayList<>();
        try {
            String sql = "SELECT id,scheduleID,price,seat_row,seat_col,status,locked_uid,locked_time FROM ticket";
            condt.trim();
            if (!condt.isEmpty()) sql += " where " + condt;
            DBUtil db = new DBUtil();
            db.openConnection();
            ResultSet rst = db.execQuery(sql);
            if (rst != null) {
                while (rst.next()) {
                    Ticket ticket = new Ticket(
                            rst.getInt("id"),
                            rst.getInt("scheduleID"),
                            rst.getInt("price"),
                            TICKET_STATUS.valueOf(rst.getString("status")),
                            rst.getInt("locked_uid"),
                            rst.getLong("locked_time")
                    );
                    ticket.setSeat_row(rst.getInt("seat_row"));
                    ticket.setSeat_col(rst.getInt("seat_col"));
                    tickets.add(ticket);
                }
            }
            db.close(rst);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public Long timeStamp() {
        String sql = "SELECT UNIX_TIMESTAMP()";
        DBUtil db = new DBUtil();
        db.openConnection();
        try {
            ResultSet res = db.execQuery(sql);
            res.first();
            return res.getLong(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
