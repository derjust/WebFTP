package de.zeeman.webftp.business.impl;

import de.zeeman.webftp.dao.CompanyDAO;
import de.zeeman.webftp.dao.UserDAO;
import de.zeeman.webftp.domain.Company;
import de.zeeman.webftp.domain.User;

public class AdministrationServiceImpl implements de.zeeman.webftp.business.AdministrationService {
	private UserDAO userDAO;
	private CompanyDAO companyDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	public void addCompany(Company company) {
		companyDAO.create(company);
	}

	public void addUser(User user) {
		userDAO.create(user);
	}

	public void invalidCompany(Company company) {
		company.setValidTo(new java.util.Date());
		companyDAO.update(company);
	}

	public void invalidUser(User user) {
		user.setValidTo(new java.util.Date());
		userDAO.update(user);
	}

	public void renewUserpassword(User user, boolean informUser) {
		// TODO Auto-generated method stub

	}

}
