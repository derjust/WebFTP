package de.zeeman.webftp.dao.impl.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import de.zeeman.webftp.dao.CompanyDAO;
import de.zeeman.webftp.dao.UploadedFileDAO;
import de.zeeman.webftp.dao.UserDAO;
import de.zeeman.webftp.dao.impl.jdbc.SharedFileDAOJDBCImpl.AbsFileRowMapper;
import de.zeeman.webftp.domain.UploadedFile;
import de.zeeman.webftp.domain.User;

public class UploadedFileDAOJDBCImpl extends GenericDAOJDBCImpl implements UploadedFileDAO {
	protected class UploadedFileRowMapper extends AbsFileRowMapper implements ParameterizedRowMapper<UploadedFile> {
		private final UserDAO userDAO;
		
		public UploadedFileRowMapper(UserDAO pUserDAO, CompanyDAO pCompanyDAO) {
			super(pCompanyDAO);
			this.userDAO = pUserDAO;
		}
		
		public UploadedFile mapRow(ResultSet rs, int rowNum) throws SQLException {
			UploadedFile uploadedFile = new UploadedFile();
			super.mapRow(uploadedFile, rs);
			uploadedFile.setAgent(rs.getString("Agent"));
			uploadedFile.setInetAddress(rs.getString("InetAddress"));
			uploadedFile.setUploader(userDAO.load(rs.getInt("UploaderId")));
			return uploadedFile;
		}
	}

	@Override
	public List<UploadedFile> listByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer create(UploadedFile newInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UploadedFile persistentObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UploadedFile load(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(UploadedFile transientObject) {
		// TODO Auto-generated method stub
		
	}


}
