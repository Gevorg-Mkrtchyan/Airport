package service;

import dao.impl.CompanyDaoImpl;
import model.Company;

import java.sql.Date;
import java.util.Set;

public class CompanyService {
    CompanyDaoImpl companyDao = new CompanyDaoImpl();

    public Company findId(int id) {
        return companyDao.findByID(id);
    }

    public void create(String companyName, String sinceDate) {
        companyDao.create(new Company(companyName, Date.valueOf(sinceDate)));
    }

    public void delete(int id) {
        companyDao.deleteById(id);
    }

    public void update(int id, String companyName, String sinceDate) {
        companyDao.update(id, new Company(companyName, Date.valueOf(sinceDate)));
    }

    public Set<Company> findAll() {
        return companyDao.findAll();
    }

    public Set<Company> get(int offset, int perPage, String sort){
       return companyDao.get(offset,perPage,sort);
    }
}
