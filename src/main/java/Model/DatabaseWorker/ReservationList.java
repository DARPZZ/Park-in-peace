package Model.DatabaseWorker;

import Model.DaoObject.Plot;
import Model.DaoObject.Resevations;
import Model.Implements.DaoResevations;

import java.util.ArrayList;

public final class ReservationList
{
    private static ReservationList INSTANCE;
    private static ArrayList<Resevations> list = new ArrayList<>();
    private DaoResevations resevationsWorker = new DaoResevations();

    private ReservationList()
    {}

    public static ReservationList getSingleton() {
        if (INSTANCE == null) {
            INSTANCE = new ReservationList();
        }
        return INSTANCE;
    }
    public ArrayList<Resevations> getList()
    {return list;}
    public void setList()
    {

        for (Resevations r: resevationsWorker.GetAll())
        {
         list.add(r);
        }
    }
    public void addList(Resevations resevations)
    {
        list.add(resevations);
    }

    public void CreateReservation(Resevations resevations)
    {resevationsWorker.Create(resevations);
    list.add(resevations);
    }
    public void UpdateReservation(Resevations resevations, String fieldname, String value)
    {resevationsWorker.Update(resevations, fieldname, value);}
    public void DeleteReservation(Resevations resevations, int ID)
    {resevationsWorker.Delete(resevations, ID);}
}
