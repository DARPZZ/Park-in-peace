package Model.Implements;

import Model.DaoObject.PlotOwner;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoPlotOwner extends Connection implements DaoInterface<PlotOwner>
{

    @Override
    public void Create(PlotOwner plotOwner)
    {

    }

    @Override
    public void Update(PlotOwner plotOwner, String fieldname, String value)
    {

    }

    @Override
    public void Delete(PlotOwner plotOwner, int ID)
    {

    }

    @Override
    public PlotOwner Get(int ID)
    {
        return null;
    }

    @Override
    public List<PlotOwner> GetAll()
    {
        List<PlotOwner> plotOwnerList = new ArrayList<>();
        try {
                CallableStatement stmt =con.prepareCall("{call getOwner()}");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                plotOwnerList.add(new PlotOwner(rs.getInt("fldPlotID"),
                        rs.getString("fldLocation"),
                        rs.getInt("fldZipcode"),
                        rs.getInt("fldUserID"),
                        rs.getDate("fldStartDate"),
                        rs.getDate("fldEndDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return plotOwnerList;
    }
}
