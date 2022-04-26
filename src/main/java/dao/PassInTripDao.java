package dao;


import service.DatabaseConnection;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


public class PassInTripDao {
    public void create(int trip_no, int ID_psg, String place) {

        String query = "Insert into pass_in_trip  values('" + trip_no + "','" + ID_psg + "','" + Date.valueOf(LocalDate.now()) + "','" + place + "');";
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createForFile(int trip_no, int ID_psg, String date, String place) {

        String query = "Insert into pass_in_trip  values('" + trip_no + "','" + ID_psg + "','" + Date.valueOf(date) +
                "','" + place + "');";
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int ID_psg, int trip_no) {
        String query = "delete from pass_in_trip where ID_psg='" + ID_psg + "'and trip_no='" + trip_no + "';";
        try (Statement stmt = DatabaseConnection.getDbConnection().getConnection().createStatement()) {
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
