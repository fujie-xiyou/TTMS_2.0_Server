package web.idao;

import web.model.Play;

import java.util.List;

public interface iPlayDAO {
    public int insert(Play play);
    public int delete(int id);
    public int update(Play play);
    public List<Play> select(String condt);
}
