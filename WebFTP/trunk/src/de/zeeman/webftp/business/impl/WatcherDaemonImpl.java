package de.zeeman.webftp.business.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import de.zeeman.webftp.business.FileService;
import de.zeeman.webftp.business.WatcherDaemon;
import de.zeeman.webftp.dao.CompanyDAO;
import de.zeeman.webftp.dao.SharedFileDAO;
import de.zeeman.webftp.domain.Company;
import de.zeeman.webftp.domain.SharedFile;

public class WatcherDaemonImpl extends TimerTask implements WatcherDaemon {
	private FileService fileService;
	private CompanyDAO companyDAO;
	private SharedFileDAO sharedFileDAO;
	
	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	public void setSharedFileDAO(SharedFileDAO sharedFileDAO) {
		this.sharedFileDAO = sharedFileDAO;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	@Override
	public void run() {
		File baseFolder = new File("C:\\temp");
		for(Company company : companyDAO.listAllValid()) {
			List<SharedFile> dbFiles = sharedFileDAO.listByCompany(company);
			File compFolder = new File(baseFolder, company.getFolder());
			List<File> fsFiles = Arrays.asList(compFolder.listFiles());
			List<SharedFile> foundDbFiles = new ArrayList<SharedFile>();
			List<File> foundFsFiles = new ArrayList<File>();
			for (File f : fsFiles) {
				for(SharedFile dbFile : dbFiles) {
					if (dbFile.getFileName().equals(f.getName())) {
						//Update file
						foundDbFiles.add(dbFile);
						foundFsFiles.add(f);
					}
				}
			}
			dbFiles.removeAll(foundDbFiles);
			fsFiles.removeAll(foundFsFiles);
			for (SharedFile dbFile : dbFiles) {
				dbFile.setValidTo(new Date());
				sharedFileDAO.update(dbFile);
			}
			for(File fsFile : fsFiles) {
				SharedFile sf = new SharedFile(fsFile, company);
				sharedFileDAO.create(sf);
			}
						
		}
	}

}
