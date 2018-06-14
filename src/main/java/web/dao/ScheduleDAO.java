package web.dao;

import web.idao.DAOFactory;
import web.idao.iScheduleDAO;
import web.model.Play;
import web.model.Schedule;
import web.model.Studio;
import web.service.PlaySer;
import web.service.StudioSer;
import web.util.DBUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ScheduleDAO implements iScheduleDAO {
    @Override
    public int insert(Schedule schedule) {
        String sql = "INSERT INTO schedule (playID,studioID,date,time,ticketCount) VALUES(?,?,?,?,?)";
        DBUtil db = new DBUtil();
        PreparedStatement pstmt = db.getPstmt(sql);
        try {
            pstmt.setInt(1, schedule.getPlay().getId());
            pstmt.setInt(2,schedule.getStudio().getId());
            pstmt.setDate(3,Date.valueOf(schedule.getDate()));
            pstmt.setTime(4,Time.valueOf(schedule.getTime()));
            pstmt.setInt(5,schedule.getTicketCount());
            int id = db.insert(pstmt);
            schedule.setId(id);
            db.close();
            return 1;
        }catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(int schID) {
        int rtn = 0;
        try{
            String sql = "DELETE FROM schedule WHERE id = " + schID;
            DBUtil db = new DBUtil();
            db.openConnection();
            rtn = db.execCommand(sql);
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rtn;
    }

    @Override
    public int update(Schedule schedule) {
        int rtn = 0;
        String sql = "UPDATE schedule SET studioID = ? ,date = ? ,time = ? ,ticketCount = ? WHERE id = ?";
        DBUtil db = new DBUtil();
        PreparedStatement pstmt = db.getPstmt(sql);
        try {
            pstmt.setInt(1, schedule.getPlay().getId());
            pstmt.setInt(2,schedule.getStudio().getId());
            pstmt.setDate(3,Date.valueOf(schedule.getDate()));
            pstmt.setTime(4,Time.valueOf(schedule.getTime()));
            pstmt.setInt(5,schedule.getTicketCount());
            pstmt.setInt(6,schedule.getId());
            rtn = db.command(pstmt);
            db.close();
            return rtn;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Schedule> select(String condt) {
        List<Schedule> schedules = new LinkedList<>();
        String sql = "SELECT id, playID, studioID, date, time ,ticketCount FROM schedule ";
        if(condt != null && !condt.isEmpty()){
            sql += "WHERE " + condt;
        }
        DBUtil db = new DBUtil();
        db.openConnection();
        try {
            ResultSet res = db.execQuery(sql);
            while (res.next()){
                Schedule schedule = new Schedule(res.getInt("id"),
                        null,null,
                        res.getDate("date").toLocalDate(),
                        res.getInt("ticketCount"));
                schedule.setTime(res.getTime("time").toLocalTime());
                Play play = new PlaySer().fetch("id = "+ res.getInt("playID")).get(0);
                schedule.setPlay(play);
                Studio studio = new StudioSer().fetch("studio_id = "+res.getInt("studioID")).get(0);
                schedule.setStudio(studio);
                schedules.add(schedule);
            }
            db.close(res);
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return schedules;
    }
}
