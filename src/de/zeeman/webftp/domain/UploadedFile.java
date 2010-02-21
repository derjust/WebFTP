package de.zeeman.webftp.domain;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UploadedFile extends SharedFile {

	private static final long serialVersionUID = -8201436117265578775L;
	private User uploader;
	private String inetAddress;
	private String agent;
	
	public UploadedFile() {
	}
	
	public UploadedFile(File file, Company comp) {
		super(file, comp);
	}

	public User getUploader() {
		return uploader;
	}

	public void setUploader(User user) {
		this.uploader = user;
	}

	public String getInetAddress() {
		return inetAddress;
	}

	public void setInetAddress(String inetAddress) {
		this.inetAddress = inetAddress;
	}
	
	public InetAddress getInetAddressAsInetAddress() throws UnknownHostException {
		return InetAddress.getByName(getInetAddress());
	}

	public void setInetAddressAsInetAddress(InetAddress inetAddress) {
		setInetAddress(this.inetAddress.toString());
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}
	
}
