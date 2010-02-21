package de.zeeman.webftp.dao;

import java.util.List;

import de.zeeman.webftp.domain.Company;

public interface CompanyDAO extends GenericDAO<Company, Integer> {

	List<Company> listAllValid();

}
