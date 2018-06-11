package web.idao;


import web.model.Ticket;

import java.util.List;

public interface iTicketDAO {
    public int insert(Ticket ticket);
    public int insert(List<Ticket> tickets);
    public int delete(Ticket ticket);
    public int modify(Ticket ticket);
    public int select(String condt);
}
