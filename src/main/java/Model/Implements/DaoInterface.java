package Model.Implements;

import java.util.List;

public interface DaoInterface<T>
{

    public abstract void Create(T t);

    public abstract void Update(T t, String fieldname, String value);

    public abstract void Delete(T t, int ID);

    public abstract T  Get(int ID);

    List<T> GetAll();
}
