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
        try{
            String sql="insert into tickets(scheduleID,seatID,price,status)values('"
                    +ticket.getScheduleID()+"','"
                    +ticket.getSeatID()+"','"
                    +ticket.getPrice()+"','"
                    +ticket.getStatus()+"')";
            DBUtil db=new DBUtil();
            db.openConnection();
            ResultSet rst=db.getInsertObjectIDs(sql);
            if(rst!=null && rst.first()){
                ticket.setId(rst.getInt(1));
            }
            db.close(rst);
            db.close();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insert(List<Ticket> tickets) {
        StringBuffer sqlBuf=new StringBuffer("INSERT INTO ticket(scheduleID,seatID,price,status)values");
              for (Ticket ticket: tickets){
                 sqlBuf.append("(" +ticket.getScheduleID()+"','"
                +ticket.getSeatID()+"','"
                +ticket.getPrice()+"','"
                +ticket.getStatus()+"'),");
              }
              sqlBuf.deleteCharAt(sqlBuf.length()-1);
              String sql=sqlBuf.toString();
              DBUtil db=new DBUtil();
              db.openConnection();
              try{
                  ResultSet res=db.getInsertObjectIDs(sql);
                  for(Ticket ticket: tickets){
                      if(!res.next()) break;
                      ticket.setId(res.getInt(1));
                  }
                  db.close();
                  return 1;
              }catch (Exception e){
                  e.printStackTrace();
              }
              return 0;
    }

    @Override
    public int delete(Ticket ticket) {
        int rtn =0;
        try{
            String sql="delete from ticket";
            sql+="where ticket = "+ ticket;
            DBUtil db = new DBUtil();
            db.openConnection();
            rtn=db.execCommand(sql);
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
        int rtn=1;
        try{
            String sql = "UPDATE tickets SET status = '" + ticket.getStatus()+"'WHERE id = " + ticket.getId();
             rtn *= db.execCommand(sql);
             db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rtn;
    }

    @Override
    public List<Ticket> select(String condt) {
        List<Ticket> tickets = new ArrayList<>();
        try{
            String sql = "SELECT id,scheduleID,seatID,priceID,status FROM tickets";
            condt.trim();
            if(!condt.isEmpty()) sql += "where" + condt;
            DBUtil db = new DBUtil();
            db.openConnection();
            ResultSet rst = db.execQuery(sql);
            if(rst !=null){
                while (rst.next()){
                    Ticket ticket=new Ticket(
                            rst.getInt("id"),
                            rst.getInt("scheduleID"),
                            rst.getInt("seatID"),
                            rst.getInt("priceID"),
                            TICKET_STATUS.valueOf(rst.getString("status"))
                            );
                    tickets.add(ticket);
                }
            }
            db.close(rst);
            db.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return tickets;
    }
}
