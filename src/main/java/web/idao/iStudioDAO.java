package web.idao;


//参考老师提供的模板

import web.model.Studio;

import java.util.List;

public interface iStudioDAO {
    public int insert(Studio stu);
    public int update(Studio stu);
    public int delete(int ID);
    public List<Studio> select(String condt);
}
