package dao.impl;

import dao.DaoForAll;
import model.Company;
import model.Trip;
import service.DatabaseConnection;
import service.TripService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class TripDaoImpl implements DaoForAll<Trip> {
    public Trip findByID(int tripNo) {
        String query = "Select trip_no,ID_cmp,plane,town_from,town_to,time_out,time_in from trip where trip_no = "
                + tripNo;
        Trip trip = null;
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {
            if (resultSet.next()) {
                trip = new Trip(resultSet.getInt("trip_no"), resultSet.getInt("ID_cmp"),
                        resultSet.getString("plane"), resultSet.getString("town_from"),
                        resultSet.getString("town_to"), resultSet.getTime("time_out"),
                        resultSet.getTime("time_in"));
            }
        } catch (SQLException e) {
            System.out.println("Wrong query for Trip with no = " + tripNo);
        }
        return trip;
    }

    @Override
    public void create(Trip trip) {
        String query = "Insert into trip(trip_no,ID_cmp,plane,town_from,town_to,time_out,time_in) " +
                "values('" + trip.getTrip_no() + "','" + trip.getID_cmp() + "','" + trip.getPlane() +
                "','" + trip.getTown_from() + "','" + trip.getTown_to() + "','" + trip.getTime_out() +
                "','" + trip.getTime_in() + "');";
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println("Wrong query for Trip");
        }

    }

    @Override
    public void deleteById(int tripNo) {
        String query = "Delete from trip where trip_no = " + tripNo;
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println("Wrong query for Trip with no = " + tripNo);
        }
    }

    @Override
    public void update(int tripNo, Trip trip) {
        String query = "update trip set ID_cmp='" + trip.getID_cmp() + "',plane ='" + trip.getPlane() + "',town_from ='"
                + trip.getTown_from() + "',town_to ='" + trip.getTown_to() + "',time_out ='" + trip.getTime_out()
                + "',time_in ='" + trip.getTime_in() + "' where trip_no=" + tripNo;
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println("Wrong query for Trip with id = " + tripNo);
        }

    }

    @Override
    public Set<Trip> findAll() {
        Set<Trip> trip = new HashSet<>();
        String query = "Select * from trip";
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                trip.add(new Trip(resultSet.getInt("trip_no"), resultSet.getInt("ID_cmp"),
                        resultSet.getString("plane"), resultSet.getString("town_from"),
                        resultSet.getString("town_to"), resultSet.getTime("time_out"),
                        resultSet.getTime("time_in")));
            }
        } catch (SQLException e) {
            System.out.println("Wrong query for Trip");
        }
        return trip;
    }

    @Override
    public Set<Trip> get(int offset, int perPage, String sort) {
        Trip trip;
        Set<Trip> tripSet = new LinkedHashSet<>();
        String query = "select * from trip " +
                "order by " + sort + " " +
                "LIMIT " + offset + " ," + perPage + " ";
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                trip = new Trip(resultSet.getInt("trip_no"), resultSet.getInt("ID_cmp")
                        , resultSet.getString("plane"), resultSet.getString("town_from"),
                        resultSet.getString("town_to"), resultSet.getTime("time_out"),
                        resultSet.getTime("time_in"));
                tripSet.add(trip);
            }
        } catch (SQLException e) {
            System.out.println("no");
        }
        return tripSet;
    }

    public Set<Trip> getTripsFrom(String city) {
        Trip trip;
        Set<Trip> tripSet = new LinkedHashSet<>();
        String query = "select * from trip " + "where town_from ='" + city + "'";
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                trip = new Trip(resultSet.getInt("trip_no"), resultSet.getInt("ID_cmp")
                        , resultSet.getString("plane"), resultSet.getString("town_from"),
                        resultSet.getString("town_to"), resultSet.getTime("time_out"),
                        resultSet.getTime("time_in"));
                tripSet.add(trip);
            }
        } catch (SQLException e) {
            System.out.println("no");
        }
        return tripSet;
    }

    public Set<Trip> getTripsTo(String city) {
        Trip trip;
        Set<Trip> tripSet = null;
        String query = "select * from trip " + "where town_to ='" + city + "'";
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            tripSet = new LinkedHashSet<>();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                trip = new Trip(resultSet.getInt("trip_no"), resultSet.getInt("ID_cmp")
                        , resultSet.getString("plane"), resultSet.getString("town_from"),
                        resultSet.getString("town_to"), resultSet.getTime("time_out"),
                        resultSet.getTime("time_in"));
                tripSet.add(trip);
            }
        } catch (SQLException e) {
            System.out.println("no");
        }
        return tripSet;
    }
}