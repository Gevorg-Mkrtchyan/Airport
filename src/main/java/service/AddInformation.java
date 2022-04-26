package service;


import dao.PassInTripDao;
import model.Address;
import model.Trip;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


public class AddInformation {


    public static void addCompaniesInfo() {
        String file = "src/main/resources/companies.txt";
        CompanyService companyService = new CompanyService();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String s = bufferedReader.readLine();
            String[] rslt = new String[2];
            String[] dt = new String[3];
            while (true) {
                s = bufferedReader.readLine();
                if (s == null) {
                    break;
                }
                rslt = s.split(",");
                dt = rslt[1].split("/");
                companyService.create(rslt[0], dt[2] + "-" + dt[0] + "-" + dt[1]);
            }
        } catch (IOException e) {
            System.out.println("companies.txt file is not found");
        }
    }

    public static void addAddressInfo() {
        AddressService addressService = new AddressService();
        String file = "src/main/resources/passengers.txt";
        Set<Address> countryCity = new HashSet<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String[] rslt = new String[2];
            String s = bufferedReader.readLine();
            while (true) {
                s = bufferedReader.readLine();
                if (s == null)
                    break;
                rslt = s.split(",");
                countryCity.add(new Address(rslt[2], rslt[3]));
            }
            for (Address address : countryCity) {
                addressService.create(address.getCountry(), address.getCity());
            }
        } catch (IOException e) {
            System.out.println("passengers.txt file for read is not found");
        }
    }

    public static void addPassengersInfo() {
        PassengersService passengerService = new PassengersService();
        String file = "src/main/resources/passengers.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String[] rslt = new String[4];
            String s = bufferedReader.readLine();
            while (true) {
                s = bufferedReader.readLine();

                if (s == null)
                    break;
                if (s.contains("'")) {
                    s = s.replace("'", "՛");
                }
                rslt = s.split(",");
                passengerService.create(rslt[0], rslt[1], rslt[2], rslt[3]);
            }
        } catch (IOException e) {
            System.out.println("passengers.txt file for read is not found");
        }
    }

    public static void addTripsInfo() {
        TripService tripService = new TripService();
        String file = "src/main/resources/trips.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String[] rslt = new String[7];
            String s = "";
            while (true) {
                s = bufferedReader.readLine();

                if (s == null)
                    break;

                rslt = s.split(",");
                String time_out = rslt[5].split(" ")[1].substring(0, 8);
                String time_in = rslt[6].split(" ")[1].substring(0, 8);
                tripService.create(Integer.parseInt(rslt[0]), Integer.parseInt(rslt[1]), rslt[2], rslt[3], rslt[4],
                        time_out, time_in);
            }
        } catch (IOException e) {
            System.out.println("trips.txt.txt file for read is not found");
        }
    }

    public static void addPass_In_TripInfo() {

        String file = "src/main/resources/passInTrip";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String[] rslt = new String[4];
            String s = bufferedReader.readLine();
            while (true) {
                s = bufferedReader.readLine();

                if (s == null)
                    break;

                rslt = s.split(",");
                new PassInTripDao().createForFile(Integer.parseInt(rslt[0]), Integer.parseInt(rslt[1]), rslt[2].
                        split(" ")[0], rslt[3]);
            }
        } catch (IOException e) {
            System.out.println("pass_in_trip.txt file for read is not found");
        }
    }
}


