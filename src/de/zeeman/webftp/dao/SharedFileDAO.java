package de.zeeman.webftp.dao;

import java.util.List;

import de.zeeman.webftp.domain.Company;
import de.zeeman.webftp.domain.SharedFile;

public interface SharedFileDAO extends GenericDAO<SharedFile, Integer> {

	List<SharedFile> listByCompany(Company company);

}
