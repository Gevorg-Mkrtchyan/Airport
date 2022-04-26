package dao.impl;

import dao.DaoForAll;
import model.Address;
import model.Company;
import service.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class AddressDaoImpl implements DaoForAll<Address> {
    @Override
    public Address findByID(int id) {
        String query = "select country,city from address where ID_adrs= " + id;
        Address address = null;
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            if (resultSet.next()) {
                address = new Address(resultSet.getString("country"),
                        resultSet.getString("city"));
            }
        } catch (SQLException e) {
            System.out.println("Wrong query for Address with id = " + id);
        }
        return address;
    }

    @Override
    public void create(Address address) {
        String query = "insert into address(country, city) values ('" +
                address.getCountry() + "','" + address.getCity() + "');";
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getID(String country,String city){
        int id=-1;
        String query = "Select ID_adrs from address where country='"+country+"'and city='"+city+"'";
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            ResultSet res=stmt.executeQuery(query);
            if(res.next()) {
                id = res.getInt("ID_adrs");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void deleteById(int id) {
        String query = "delete from address where ID_adrs=" + id;
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Address address) {
        String query = "update  address set country='" + address.getCountry() + "',city='"
                + address.getCity() + "' where ID_adrs=" + id;
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<Address> findAll() {
        String query = "select * from address";
        Set<Address> cmp = new HashSet<>();
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                cmp.add(new Address(res.getString("country"), res.getString("city")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cmp;
    }

    @Override
    public Set<Address> get(int offset, int perPage, String sort) {
        Address address;
        Set<Address> addressSet = new LinkedHashSet<>();
        String query = "SELECT * from address " +
                "ORDER BY " + sort + " " +
                "LIMIT " + offset + " ," + perPage + " ";
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {

            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                address = new Address(resultSet.getString("country"), resultSet.getString("city"));
                addressSet.add(address);
            }
        } catch (SQLException e) {
            System.out.println("no");
        }
        return addressSet;
    }
}

