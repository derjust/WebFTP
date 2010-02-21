package de.zeeman.webftp.business;

import java.io.File;
import java.util.List;

import de.zeeman.webftp.domain.Company;
import de.zeeman.webftp.domain.SharedFile;
import de.zeeman.webftp.domain.UploadedFile;
import de.zeeman.webftp.domain.User;

public interface FileService {

	public List<SharedFile> listAllFilesByCompany(Company pCompany);
	
	public List<UploadedFile> listAllFilesByUser(User pUser);
	
	public void addUploadedFile(File pFile, User pUser, String userAgent, String inetAddress);
}
