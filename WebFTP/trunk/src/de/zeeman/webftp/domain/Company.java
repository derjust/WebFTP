package de.zeeman.webftp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Company implements Serializable {
	private static final long serialVersionUID = -60970806703354372L;
	private int id;
	private String name;
	private String folder;
	private Date validFrom;
	private Date validTo;
	@Deprecated
	private Set<User> users;
	@Deprecated
	private Set<SharedFile> files;
	
	public Company() {
	}
	
	public boolean isValid() {
		Date now = new Date();
		return getValidFrom().before(now) && getValidTo().after(now);
	}
	
	public Date getValidFrom() {
		return validFrom;
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

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	@Deprecated
	public Set<User> getUsers() {
		return users;
	}
	@Deprecated
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	@Deprecated
	public Set<SharedFile> getFiles() {
		return files;
	}
	@Deprecated
	public void setFiles(Set<SharedFile> files) {
		this.files = files;
	}
}
