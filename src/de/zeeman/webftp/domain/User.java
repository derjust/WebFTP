package de.zeeman.webftp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
	private static final long serialVersionUID = 4415360718360744575L;
	private int id;
	private String login;
	private String password;
	private Company company;
	private String emailAddress;
	private Date validFrom;
	private Date validTo;
	@Deprecated
	private Set<UploadedFile> uploadedFiles = new HashSet<UploadedFile>(0);
	@Deprecated
	public Set<UploadedFile> getUploadedFiles() {
		return uploadedFiles;
	}

	public Date getValidFrom() {
		return validFrom;
	}
	
	public boolean isValid() {
		Date now = new Date();
		return getCompany().isValid() && getValidFrom().before(now) && getValidTo().after(now);
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}



	public Date getValidTo() {
		return validTo;
	}



	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}


	@Deprecated
	public void setUploadedFiles(Set<UploadedFile> uploadedFiles) {
		this.uploadedFiles = uploadedFiles;
	}

	public User() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
