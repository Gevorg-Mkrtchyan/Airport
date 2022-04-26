package service;

import dao.impl.TripDaoImpl;
import model.Passenger;
import model.Trip;

import java.sql.Time;
import java.util.Set;

public class TripService {
    TripDaoImpl tripDao = new TripDaoImpl();

    public Trip findByID(int tripNo) {
        return tripDao.findByID(tripNo);
    }

    public void create(int trip_no, int ID_cmp, String plane, String town_from, String town_to, String time_out,
                       String time_in) {
        tripDao.create(new Trip(trip_no, ID_cmp, plane, town_from, town_to,
                Time.valueOf(time_out), Time.valueOf(time_in)));
    }

    public void deleteById(int tripNO) {
        tripDao.deleteById(tripNO);
    }

    public void update(int trip_no, int ID_cmp, String plane, String town_from, String town_to,
                       String time_out, String time_in) {
        tripDao.update(trip_no, new Trip(trip_no, ID_cmp, plane, town_from, town_to,
                Time.valueOf(time_out), Time.valueOf(time_in)));
    }

    public void findAll() {
        Set<Trip> tripSet = tripDao.findAll();
        for (Trip t : tripSet) {
            System.out.println(t);
        }
    }

    public Set<Trip> get(int offset, int perPage, String sort) {
        return tripDao.get(offset, perPage, sort);
    }

    public Set<Trip> getTripsTo(String city) {
        return tripDao.getTripsTo(city);
    }

    public Set<Trip> getTripsFrom(String city) {
        return tripDao.getTripsFrom(city);
    }
}
