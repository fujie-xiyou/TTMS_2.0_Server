package web.idao;

import web.model.Seat;
import web.model.Studio;

import java.util.List;

public interface iSeatDAO {
    public int insert(Seat seat);
    public int insert(List<Seat> seats);
    public int delete(Seat seat);
    public int delete(Studio studio);
    public int update(Seat seat);
    public int update(List<Seat> seats);
    public List<Seat> select(String condt);
}
