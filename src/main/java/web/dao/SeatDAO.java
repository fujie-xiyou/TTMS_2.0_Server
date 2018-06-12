package web.dao;

import org.springframework.test.context.jdbc.Sql;
import web.idao.iSeatDAO;
import web.model.Seat;
import web.model.Studio;
import web.model.enums.PLAY_TYPE;
import web.model.enums.SEAT_STATUS;
import web.util.DBUtil;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class SeatDAO implements iSeatDAO {
    @Override
    public int insert(Seat seat) {
        return 0;
    }
    @Override
    public int insert(List<Seat> seats){
        StringBuffer sqlBuf = new StringBuffer("INSERT INTO seat(studioID,row,col,status) VALUES");
        for(Seat seat : seats){
            sqlBuf.append("("+seat.getstudioID()+","+seat.getRow()+","+seat.getCol()+",'"+seat.getStatus().name()+"'),") ;
        }
        sqlBuf.deleteCharAt(sqlBuf.length()-1);
        String sql = sqlBuf.toString();
        DBUtil db = new DBUtil();
        db.openConnection();
        try{
           ResultSet res = db.getInsertObjectIDs(sql);
           for (Seat seat : seats){
               if (!res.next()) break;
               seat.setId(res.getInt(1));
           }
           db.close();
           return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int delete(Seat seat) {
        return 0;
    }

    @Override
    public int delete(Studio studio){
        String sql = "DELETE FROM seat WHERE studioID = " + studio.getId();
        DBUtil db = new DBUtil();
        db.openConnection();
        try {
            int rtn = db.execCommand(sql);
            db.close();
            return  rtn;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Seat seat) {
        return 0;
    }
    @Override
    public int update(List<Seat> seats){
        DBUtil db = new DBUtil();
        db.openConnection();
        int rtn = 1;
        try {
            for (Seat seat : seats) {
                String sql = "UPDATE seat SET status = '" + seat.getStatus() + "' WHERE id = " + seat.getId();
                rtn *= db.execCommand(sql);
            }
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rtn;
    }

    @Override
    public List<Seat> select(String condt) {
        String sql = "SELECT id,studioID,row,col,status FROM seat";
        List<Seat> seats = new LinkedList<>();
        if(condt != null && !condt.isEmpty()){
            sql += " WHERE " + condt;
        }
        DBUtil db = new DBUtil();
        db.openConnection();

        try {
            ResultSet res = db.execQuery(sql);
            while (res.next()){
                Seat seat = new Seat(res.getInt(1),res.getInt(2),
                        res.getInt(3),res.getInt(4),
                        SEAT_STATUS.valueOf(res.getString(5)));
                seats.add(seat);
            }
            db.close(res);
            db.close();
            return seats;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String []args){
        List<Seat> seats=new LinkedList<>();
        seats.add(new Seat(-1,18,2,3,SEAT_STATUS.BROKEN));

        System.out.println(new SeatDAO().insert(seats));
    }
}
