package web.dao;

import web.idao.iScheduleDAO;
import web.model.Schedule;
import web.util.DBUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ScheduleDAO implements iScheduleDAO {
    @Override
    public int insert(Schedule schedule) {
        String sql = "INSERT INTO schedule (playID,studioID,date,seatCount) VALUES(?,?,?,?)";
        DBUtil db = new DBUtil();
        PreparedStatement pstmt = db.getPstmt(sql);
        try {
            pstmt.setInt(1, schedule.getPlayID());
            pstmt.setInt(2,schedule.getStudioID());
            pstmt.setDate(3,Date.valueOf(schedule.getDate()));
            pstmt.setInt(4,schedule.getSeatCount());
            int id = db.insert(pstmt);
            schedule.setId(id);
            db.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int schID) {
        return 0;
    }

    @Override
    public int update(Schedule schedule) {
        return 0;
    }

    @Override
    public List<Schedule> select(String condt) {
        return null;
    }
}
