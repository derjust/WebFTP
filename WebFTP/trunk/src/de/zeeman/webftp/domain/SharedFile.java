package de.zeeman.webftp.domain;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import javax.activation.MimetypesFileTypeMap;

public class SharedFile implements Serializable {
	private static final long serialVersionUID = 4617953088719422006L;
	private int id;
	private String fileName;
	private String mimeType;
	private long size;
	private Company company;
	private Date validFrom;
	private Date validTo;
	
	public SharedFile() {
	}
	
	public SharedFile(File file, Company comp) {
		String mime = new MimetypesFileTypeMap().getContentType(file);
		setCompany(comp);
		setFileName(file.getName());
		setSize(file.length());
		setMimeType(mime);
		setValidFrom(new Date());
		setValidTo(null);
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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
}
