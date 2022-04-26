package model;

import java.sql.Date;
import java.util.Objects;

public class Company {
    private String name_cmp;
    private Date date_found;

    public Company(String name_cmp, Date date_found) {
        this.name_cmp = name_cmp;
        this.date_found = date_found;
    }

    public String getName_cmp() {
        return name_cmp;
    }

    public void setName_cmp(String name_cmp) {
        this.name_cmp = name_cmp;
    }

    public Date getDate_found() {
        return date_found;
    }

    public void setDate_found(Date date_found) {
        this.date_found = date_found;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name_cmp, company.name_cmp) && Objects.equals(date_found, company.date_found);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name_cmp, date_found);
    }

    @Override
    public String toString() {
        return "Company{" +
                "name_cmp='" + name_cmp + '\'' +
                ", date_found=" + date_found +
                '}';
    }
}








