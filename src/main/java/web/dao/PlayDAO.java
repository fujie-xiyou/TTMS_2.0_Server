package web.dao;

import web.idao.iPlayDAO;
import web.model.Play;
import web.model.enums.PLAY_RATING;
import web.model.enums.PLAY_TYPE;
import web.util.DBUtil;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class PlayDAO implements iPlayDAO {
    @Override
    public int insert(Play play) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int update(Play play) {
        return 0;
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
