package de.zeeman.webftp.business.impl;

import java.io.File;
import java.util.List;

import de.zeeman.webftp.business.FileService;
import de.zeeman.webftp.dao.CompanyDAO;
import de.zeeman.webftp.dao.SharedFileDAO;
import de.zeeman.webftp.dao.UploadedFileDAO;
import de.zeeman.webftp.dao.UserDAO;
import de.zeeman.webftp.domain.Company;
import de.zeeman.webftp.domain.SharedFile;
import de.zeeman.webftp.domain.UploadedFile;
import de.zeeman.webftp.domain.User;

public class FileServiceImpl implements FileService {
	private CompanyDAO companyDAO;
	private UserDAO userDAO;
	private SharedFileDAO sharedFileDAO;
	private UploadedFileDAO uploadedFileDAO;

	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setSharedFileDAO(SharedFileDAO sharedFileDAO) {
		this.sharedFileDAO = sharedFileDAO;
	}

	public void setUploadedFileDAO(UploadedFileDAO uploadedFileDAO) {
		this.uploadedFileDAO = uploadedFileDAO;
	}

	@Override
	public void addUploadedFile(File file, User user, String agent, String inetAddress) {
		UploadedFile uf = new UploadedFile(file, user.getCompany());
		uf.setAgent(agent);
		uf.setInetAddress(inetAddress);
		uf.setUploader(user);
		uploadedFileDAO.create(uf);
	}

	public List<SharedFile> listAllFilesByCompany(Company company) {
		return sharedFileDAO.listByCompany(company);
	}

	public List<UploadedFile> listAllFilesByUser(User user) {
		return uploadedFileDAO.listByUser(user);
	}

}
