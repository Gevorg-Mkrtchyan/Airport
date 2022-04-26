package service;

import dao.impl.AddressDaoImpl;
import model.Address;

import java.util.Set;

public class AddressService {
    AddressDaoImpl addressDao = new AddressDaoImpl();

    public Address findId(int id) {
        return addressDao.findByID(id);
    }

    public void create(String country, String city) {
        addressDao.create(new Address(country, city));
    }

    public void delete(int id) {
        addressDao.deleteById(id);
    }

    public void update(int id, String country, String city) {
        addressDao.update(id, new Address(country, city));
    }

    public Set<Address> findAll() {
        return addressDao.findAll();
    }

    public Set<Address> get(int offset, int perPage, String sort) {
        return addressDao.get(offset, perPage, sort);
    }
}