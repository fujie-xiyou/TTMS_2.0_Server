package web.service;

import web.model.Order;
import web.idao.DAOFactory;
import web.idao.iTicketDAO;
import web.model.*;
import web.model.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.*;

public class TicketSer {
    iTicketDAO iTicketDAO = DAOFactory.createTicketDAO();
    public Ticket[][] fetchBySchedule(Schedule schedule){
        List<Ticket> tickets = iTicketDAO.select("scheduleID = "+schedule.getId());
        Ticket[][] tickets1 = new Ticket[schedule.getStudio().getRow()][schedule.getStudio().getCol()];
        Iterator<Ticket> iterator = tickets.iterator();
        for(int i = 0; i < tickets1.length; i++){
            for (int j = 0; j < tickets1[0].length; j++){
                if(!iterator.hasNext()) break;
                tickets1[i][j] = iterator.next();
            }
        }
        return tickets1;
    }
    public int addAll(Schedule schedule){
        List<Ticket> tickets = new ArrayList<>(schedule.getStudio().getRow() * schedule.getStudio().getCol());
        List<Seat> seats = DAOFactory.creatSeatDAO().select("studioID = "+schedule.getStudio().getId());
        Iterator<Seat> seatIterator = seats.iterator();
        while (seatIterator.hasNext()){
            TICKET_STATUS status = null;
            Seat seat = seatIterator.next();
            if(seat.getStatus().equals(SEAT_STATUS.GOOD)) status = TICKET_STATUS.AVL;
            else status = TICKET_STATUS.NONE;
            Ticket ticket = new Ticket(-1,schedule.getId(),schedule.getPlay().getPrice(),status,0,0);
            ticket.setSeat_row(seat.getRow());
            ticket.setSeat_col(seat.getCol());
            tickets.add(ticket);
        }
        return iTicketDAO.insert(tickets);
    }
    public List<Ticket> fetch(String condt){
        return iTicketDAO.select(condt);
    }
    public int lockTicket(Ticket ticket){
        Long time = timeStamp();
        Ticket ticket1 = iTicketDAO.select("id = "+ticket.getId()).get(0);
        if(time - ticket1.getLockedTime() < Ticket.lockTime){
            if(ticket1.getLockedUID() != ticket.getLockedUID()){
                return 0;
            }
        }
        ticket.setLockedTime(timeStamp());
        return iTicketDAO.update(ticket);
    }
    public int unLockTicket(Ticket ticket){
        Long time = timeStamp();
        Ticket ticket1 = iTicketDAO.select("id = "+ticket.getId()).get(0);
        if(time - ticket1.getLockedTime() < Ticket.lockTime){
            if(ticket1.getLockedUID() != ticket.getLockedUID()){
                return 0;
            }
        }
        ticket.setLockedUID(0);
        ticket.setLockedTime(0);
        return iTicketDAO.update(ticket);
    }
    public Long timeStamp(){
        return iTicketDAO.timeStamp();
    }
    public Order purchaseTicket(List<Ticket> tickets,Account account) {
        if(iTicketDAO.update(tickets) > 0){
            DAOFactory.creatScheduleDAO().updateTicketCount(tickets.get(0).getScheduleID(),-tickets.size());
            //生成订单
            List<OrderItem> items = new LinkedList<>();
            Order order = new Order();
            order.setDateTime(LocalDateTime.now());
            order.setUid(account.getUid());
            order.setType(ORDER_TYPE.BUY);
            order.setStatus(ORDER_STATUS.TBU);
            for(Ticket ticket : tickets){
                order.setTotal(order.getTotal()+ticket.getPrice());
            }
            if(new OrderSer().add(order) > 0){

                for (Ticket ticket : tickets) {
                    OrderItem item = new OrderItem();
                    item.setOrderID(order.getId());
                    item.setTicketID(ticket.getId());
                    item.setStatus(ORDER_ITEM_STATUS.TBU);
                    item.setPrice(ticket.getPrice());
                    items.add(item);
                }
                if(new OrderItemSer().add(items) > 0){
                    order.setItems(items);
                    return order;
                }
            }

        }
        return null;
    }
    public Order returnTicket(Order order ,Account loginUser){
        OrderSer orderSer = new OrderSer();
        List<Order> list = orderSer.fetch("id = "+order.getId() + " AND status = '"+ORDER_STATUS.TBU.name()+"'");
        if(list.isEmpty()) {
            return null;
        }
        Order res = list.get(0);
        orderSer.getItems(order);
        List<OrderItem> items = order.getItems();
        res.setItems(items);
        res.setStatus(ORDER_STATUS.REFUNDED);
        List<Ticket> tickets = new LinkedList<>();
        for(OrderItem item : items){
            item.setStatus(ORDER_ITEM_STATUS.REFUNDED);
            Ticket ticket = new Ticket(item.getTicketID(),0,item.getPrice(),TICKET_STATUS.AVL,0,0);
            tickets.add(ticket);

        }
        if(iTicketDAO.update(tickets) <= 0) return null;
        DAOFactory.creatScheduleDAO().updateTicketCount(tickets.get(0).getScheduleID(),tickets.size());
        if(orderSer.modify(res) <= 0) return null;
        if(new OrderItemSer().modify(items) <= 0 ) return null;
        res.setType(ORDER_TYPE.REFUND);
        res.setUid(loginUser.getUid());
        if(orderSer.add(res) <= 0) return null;
        return res;
    }
}
