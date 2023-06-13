package Controller.DatabaseWorker;

import Model.DaoObject.Reservations;
import Model.Implements.DaoReservations;

import java.util.ArrayList;

public final class ReservationList
{
    private static ReservationList INSTANCE;
    private static ArrayList<Reservations> list = new ArrayList<>();
    private DaoReservations resevationsWorker = new DaoReservations();

    private ReservationList()
    {}

    public static ReservationList getSingleton() {
        if (INSTANCE == null) {
            INSTANCE = new ReservationList();
        }
        return INSTANCE;
    }
    public ArrayList<Reservations> getList()
    {return list;}
    public void setList()
    {

        for (Reservations r: resevationsWorker.GetAll())
        {
         list.add(r);

        }
    }
    public void addList(Reservations reservations)
    {
        list.add(reservations);
    }

    public void CreateReservation(Reservations reservations)
    {resevationsWorker.Create(reservations);
    list.add(reservations);
    }
    public void UpdateReservation(Reservations reservations, String fieldname, String value)
    {resevationsWorker.Update(reservations, fieldname, value);}
    public void DeleteReservation(Reservations reservations, int ID)
    {resevationsWorker.Delete(reservations, ID);}
}
