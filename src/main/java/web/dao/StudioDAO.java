package web.dao;

import web.idao.DAOFactory;
import web.idao.iStudioDAO;
import web.model.Seat;
import web.model.Studio;
import web.util.DBUtil;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class StudioDAO implements iStudioDAO {
    @Override
    public int insert(Studio stu) {
        try {
            String sql = "insert into studio(studio_name, studio_row_count, studio_col_count, studio_introduction )"
                    + " values('"
                    + stu.getName()
                    + "', "
                    + stu.getRow()
                    + ", " + stu.getCol()
                    + ", '" + stu.getIntroduction()
                    + "' )";
            DBUtil db = new DBUtil();
            db.openConnection();
            ResultSet rst = db.getInsertObjectIDs(sql);
            if (rst!=null && rst.first()) {
                stu.setId(rst.getInt(1));
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
    public int update(Studio stu) {
        int rtn=0;
        try {
            String sql = "update studio set " + " studio_name ='"
                    + stu.getName() + "', " + " studio_row_count = "
                    + stu.getRow() + ", " + " studio_col_count = "
                    + stu.getCol() + ", " + " studio_introduction = '"
                    + stu.getIntroduction() + "' ";

            sql += " where studio_id = " + stu.getId();
            DBUtil db = new DBUtil();
            db.openConnection();
            rtn =db.execCommand(sql);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn;
    }

    @Override
    public int delete(int ID) {
        int rtn=0;
        try{
            String sql = "delete from  studio ";
            sql += " where studio_id = " + ID;
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
    public List<Studio> select(String condt) {
        List<Studio> stuList = null;
        stuList=new LinkedList<Studio>();
        try {
            String sql = "select studio_id, studio_name, studio_row_count, studio_col_count, studio_introduction from studio ";
            condt.trim();
            if(!condt.isEmpty())
                sql+= " where " + condt;
            DBUtil db = new DBUtil();
            if(!db.openConnection()){
                System.out.print("fail to connect database");
                return null;
            }
            ResultSet rst = db.execQuery(sql);
            if (rst!=null) {
                while(rst.next()){
                    Studio stu=new Studio();
                    stu.setId(rst.getInt("studio_id"));
                    stu.setName(rst.getString("studio_name"));
                    stu.setRow(rst.getInt("studio_row_count"));
                    stu.setCol(rst.getInt("studio_col_count"));
                    stu.setCount(rst.getInt("seat_count"));
                    stu.setIntroduction(rst.getString("studio_introduction"));
                    List<Seat> seats = DAOFactory.creatSeatDAO().select("studioID = "+stu.getId());
                    Seat[][] seats1 = new Seat[stu.getRow()][stu.getCol()];
                    for(Seat seat:seats){
                        seats1[seat.getRow()][seat.getCol()] = seat;
                    }
                    stu.setSeats(seats1);
                    stuList.add(stu);
                }
            }
            db.close(rst);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{

        }

        return stuList;
    }
}
