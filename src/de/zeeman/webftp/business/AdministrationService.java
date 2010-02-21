package de.zeeman.webftp.business;

import de.zeeman.webftp.domain.Company;
import de.zeeman.webftp.domain.User;

public interface AdministrationService {

	public void addUser(User pUser);
	public void invalidUser(User pUser);
	public void renewUserpassword(User pUser, boolean informUser);
	
	public void addCompany(Company pCompany);
	public void invalidCompany(Company pCompany);
}
