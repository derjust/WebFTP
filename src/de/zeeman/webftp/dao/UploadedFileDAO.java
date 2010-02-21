package de.zeeman.webftp.dao;

import java.util.List;

import de.zeeman.webftp.domain.UploadedFile;
import de.zeeman.webftp.domain.User;

public interface UploadedFileDAO extends GenericDAO<UploadedFile, Integer> {

	List<UploadedFile> listByUser(User user);

}
