package Model.Implements;

import Model.DaoObject.User;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoSeason extends Connection implements DaoInterface<DaoSeason>
{

    @Override
    public void Create(DaoSeason daoSeason)
    {

    }

    @Override
    public void Update(DaoSeason daoSeason, String fieldname, String value)
    {

    }

    @Override
    public void Delete(DaoSeason daoSeason, int ID)
    {

    }

    @Override
    public DaoSeason Get(int ID)
    {
        return null;
    }

    @Override
    public List<DaoSeason> GetAll()
    {
return null;
    }
}
