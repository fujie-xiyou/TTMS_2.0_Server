package web.dao;

import web.idao.iPlayDAO;
import web.model.Play;
import web.model.enums.PLAY_RATING;
import web.model.enums.PLAY_TYPE;
import web.util.DBUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class PlayDAO implements iPlayDAO {
    @Override
    public int insert(Play play) {
        String sql = "INSERT INTO play (name,type,area,rating,duration,startDate,endDate,price,imgUrl) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        DBUtil db = new DBUtil();
        PreparedStatement pstmt = db.getPstmt(sql);

        try{
            pstmt.setString(1,play.getName());
            pstmt.setString(2,play.getType().name());
            pstmt.setString(3,play.getArea());
            pstmt.setString(4,play.getRating().name());
            pstmt.setInt(5,play.getDuration());
            pstmt.setDate(6,Date.valueOf(play.getStartDate()));
            pstmt.setDate(7,Date.valueOf(play.getEndDate()));
            pstmt.setInt(8,play.getPrice());
            pstmt.setString(9,play.getImgUrl());
            play.setId(db.insert(pstmt));
            db.close();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM play WHERE id = "+ id;
        DBUtil db = new DBUtil();
        int rtn = db.command(db.getPstmt(sql));
        try{
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rtn;
    }

    @Override
    public int update(Play play) {
        String sql = "UPDATE play SET name = ?,type = ?,area = ?,rating = ?,duration = ?, startDate = ?," +
                "endDate = ?,price = ?,imgUrl = ? WHERE id = ?";
        DBUtil db = new DBUtil();
        PreparedStatement pstmt = db.getPstmt(sql);
        int rtn = 0;
        try {
            pstmt.setString(1,play.getName());
            pstmt.setString(2,play.getType().name());
            pstmt.setString(3,play.getArea());
            pstmt.setString(4,play.getRating().name());
            pstmt.setInt(5,play.getDuration());
            pstmt.setDate(6,Date.valueOf(play.getStartDate()));
            pstmt.setDate(7,Date.valueOf(play.getEndDate()));
            pstmt.setInt(8,play.getPrice());
            pstmt.setString(9,play.getImgUrl());
            pstmt.setInt(10,play.getId());
            rtn = db.command(pstmt);
            db.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rtn;
    }

    @Override
    public List<Play> select(String condt) {
        String sql = "SELECT id, name, type,area,rating,duration, startDate, endDate,price,imgUrl  FROM play";
        List<Play> plays = new LinkedList<>();
        if(condt != null && !condt.isEmpty()){
            sql += " WHERE " + condt;
        }
        DBUtil dbUtil = new DBUtil();
        dbUtil.openConnection();
        try {
            ResultSet resultSet = dbUtil.execQuery(sql);
            while (resultSet.next()){
                Play play = new Play();
                play.setId(resultSet.getInt(1));
                play.setName(resultSet.getString(2));
                play.setType(PLAY_TYPE.valueOf(resultSet.getString(3)));
                play.setArea(resultSet.getString(4));
                play.setRating(PLAY_RATING.valueOf(resultSet.getString(5)));
                play.setDuration(resultSet.getInt(6));
                play.setStartDate(resultSet.getDate(7).toLocalDate());
                play.setEndDate(resultSet.getDate(8).toLocalDate());
                play.setPrice(resultSet.getInt(9));
                play.setImgUrl(resultSet.getString(10));
                plays.add(play);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return plays;

    }
}
