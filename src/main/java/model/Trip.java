package model;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Objects;

public class Trip {
    private int trip_no;
    private int ID_cmp;
    private String plane;
    private String town_from;
    private String town_to;
    private Time time_out;
    private Time time_in;

    public Trip(int trip_no, int ID_cmp, String plane, String town_from, String town_to, Time time_out, Time time_in) {
        this.trip_no = trip_no;
        this.ID_cmp = ID_cmp;
        this.plane = plane;
        this.town_from = town_from;
        this.town_to = town_to;
        this.time_out = time_out;
        this.time_in = time_in;
    }

    public int getTrip_no() {
        return trip_no;
    }

    public void setTrip_no(int trip_no) {
        this.trip_no = trip_no;
    }

    public int getID_cmp() {
        return ID_cmp;
    }

    public void setID_cmp(int ID_cmp) {
        this.ID_cmp = ID_cmp;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    public String getTown_from() {
        return town_from;
    }

    public void setTown_from(String town_from) {
        this.town_from = town_from;
    }

    public String getTown_to() {
        return town_to;
    }

    public void setTown_to(String town_to) {
        this.town_to = town_to;
    }

    public Time getTime_out() {
        return time_out;
    }

    public void setTime_out(Time time_out) {
        this.time_out = time_out;
    }

    public Time getTime_in() {
        return time_in;
    }

    public void setTime_in(Time time_in) {
        this.time_in = time_in;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return trip_no == trip.trip_no && ID_cmp == trip.ID_cmp && Objects.equals(plane, trip.plane)
                && Objects.equals(town_from, trip.town_from) && Objects.equals(town_to, trip.town_to)
                && Objects.equals(time_out, trip.time_out) && Objects.equals(time_in, trip.time_in);
    }
    @Override
    public int hashCode() {
        return Objects.hash(trip_no, ID_cmp, plane, town_from, town_to, time_out, time_in);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "trip_no=" + trip_no +
                ", ID_cmp=" + ID_cmp +
                ", plane='" + plane + '\'' +
                ", town_from='" + town_from + '\'' +
                ", town_to='" + town_to + '\'' +
                ", time_out=" + time_out +
                ", time_in=" + time_in +
                '}';
    }
}




