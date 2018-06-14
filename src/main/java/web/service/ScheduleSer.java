package web.service;

import web.idao.DAOFactory;
import web.idao.iScheduleDAO;
import web.model.Schedule;

import java.util.List;

public class ScheduleSer {
    private iScheduleDAO iScheduleDAO = DAOFactory.creatScheduleDAO();
    public int add(Schedule schedule){
        if( iScheduleDAO.insert(schedule) > 0){
            return new TicketSer().addAll(schedule);
        }
        return 0;
    }
    public int delete(Schedule schedule){
        return iScheduleDAO.delete(schedule.getId());
    }
    public int modify(Schedule schedule){
        return iScheduleDAO.update(schedule);
    }
    public List<Schedule> fetch(String condt){
        return iScheduleDAO.select(condt);
    }
    public List<Schedule> fetchAll(){
        return iScheduleDAO.select("");
    }
}
