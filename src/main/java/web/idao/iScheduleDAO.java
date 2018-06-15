package web.idao;

import web.model.Schedule;

import java.util.List;

public interface iScheduleDAO {
    public int insert(Schedule schedule);
    public int delete(int schID);
    public int update(Schedule schedule);
    public int updateTicketCount(int id ,int addNum);
    public List<Schedule> select(String condt);
}
