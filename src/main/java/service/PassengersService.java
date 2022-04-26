package service;

import dao.PassInTripDao;
import dao.impl.PassengerDaoImpl;
import model.Address;
import model.Passenger;

import java.util.Set;

public class PassengersService {
    PassengerDaoImpl passengerDao = new PassengerDaoImpl();
    private final PassInTripDao passInTripDao = new PassInTripDao();

    public Passenger findId(int id) {
        return passengerDao.findByID(id);
    }

    public void delete(int id) {
        passengerDao.deleteById(id);
    }

    public void create(String name, String phone, String country, String city) {
        passengerDao.create(new Passenger(name, phone, new Address(country, city)));
    }

    public void update(int id, String newName, String newPhone, String newCountry, String newCity) {
        passengerDao.update(id, new Passenger(newName, newPhone, new Address(newCountry, newCity)));
    }

    public Set<Passenger> findAll() {
        return passengerDao.findAll();
    }

    public Set<Passenger> get(int offset, int perPage, String sort) {
        return passengerDao.get(offset, perPage, sort);
    }

    void registerTrip(int trip_no, int ID_psg, String place) {
        passInTripDao.create(trip_no, ID_psg, place);
    }

    void cancelTrip(int ID_psg, int trip_no) {
        passInTripDao.delete(ID_psg, trip_no);
    }

    public Set<Passenger> getPassengersOfTrip(int tripNumber) {
        return passengerDao.getPassengersOfTrip(tripNumber);
    }
}
