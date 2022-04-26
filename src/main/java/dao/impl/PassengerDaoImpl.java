package dao.impl;

import dao.DaoForAll;
import model.Address;
import model.Passenger;
import service.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class PassengerDaoImpl implements DaoForAll<Passenger> {
    @Override
    public Passenger findByID(int id) {
        String query = "select * from passenger where ID_psg= " + id;
        Passenger pss = null;
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                pss = (new Passenger(res.getString("name_psg"), res.getString("phone")
                        , new AddressDaoImpl().findByID(res.getInt("ID_adrs"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pss;
    }

    @Override
    public void create(Passenger passenger) {
        AddressDaoImpl addressDao = new AddressDaoImpl();
        int adrsID = addressDao.getID(passenger.getAddress().getCountry(), passenger.getAddress().getCity());
        if (adrsID == -1) {
            addressDao.create(passenger.getAddress());
            adrsID = addressDao.getID(passenger.getAddress().getCountry(), passenger.getAddress().getCity());
        }
        String query = "Insert into passenger(name_psg,phone,ID_adrs) values('" + passenger.getName() +
                "','" + passenger.getPhone() + "','" + adrsID + "');";
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String query = "delete from passenger where ID_psg=" + id;
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Passenger passenger) {
        AddressDaoImpl addressDao = new AddressDaoImpl();
        int adrsID = addressDao.getID(passenger.getAddress().getCountry(), passenger.getAddress().getCity());
        if (adrsID == -1) {
            addressDao.create(passenger.getAddress());
            adrsID = addressDao.getID(passenger.getAddress().getCountry(), passenger.getAddress().getCity());
        }
        String query = "update passenger set name_psg='" + passenger.getName() + "', phone='" + passenger.getPhone() +
                "',ID_adrs=" + adrsID;
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<Passenger> findAll() {
        AddressDaoImpl addressDao = new AddressDaoImpl();
        String query = "select * from passenger";
        Set<Passenger> pss = new HashSet<>();
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            ResultSet res = stmt.executeQuery(query);
            Address address = null;
            while (res.next()) {
                address = addressDao.findByID(res.getInt("ID_adrs"));
                pss.add(new Passenger(res.getString("name_psg"), res.getString("phone"),
                        new Address(address.getCountry(), address.getCity())));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pss;
    }

    @Override
    public Set<Passenger> get(int offset, int perPage, String sort) {
        AddressDaoImpl addressDao = new AddressDaoImpl();
        Passenger passenger;
        Set<Passenger> passengerSet = new LinkedHashSet<>();
        String query = "SELECT * from passenger " +
                "ORDER BY " + sort + " " +
                "LIMIT " + offset + " ," + perPage + " ";
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {

            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                Address address = addressDao.findByID(resultSet.getInt("ID_adrs"));
                passenger = new Passenger(resultSet.getString("name_psg"),
                        resultSet.getString("phone"), new Address(address.getCountry(), address.getCity()));
                passengerSet.add(passenger);
            }
        } catch (SQLException e) {
            System.out.println("no");
        }
        return passengerSet;
    }

    public Set<Passenger> getPassengersOfTrip(int tripNo) {
        Set<Passenger> passengers = new HashSet<>();
        String query = "SELECT * FROM pass_in_trip as pt join passenger as p on pt.ID_psg = p.ID_psg where trip_no = "
                + tripNo;
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                passengers.add(new Passenger(res.getString("name_psg"), res.getString("phone"),
                        new AddressDaoImpl().findByID(res.getInt("ID_adrs"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers;
    }
}
