package de.zeeman.webftp.dao.impl.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import de.zeeman.webftp.dao.CompanyDAO;
import de.zeeman.webftp.dao.SharedFileDAO;
import de.zeeman.webftp.domain.Company;
import de.zeeman.webftp.domain.SharedFile;

public class SharedFileDAOJDBCImpl extends GenericDAOJDBCImpl implements SharedFileDAO {
	private final class SharedFileRowMapper extends AbsFileRowMapper implements ParameterizedRowMapper<SharedFile> {
		public SharedFileRowMapper(CompanyDAO pCompanyDAO) {
			super(pCompanyDAO);
		}
		public SharedFile mapRow(ResultSet rs, int rowNum) throws SQLException {
			SharedFile sharedFile = new SharedFile();
			super.mapRow(sharedFile, rs);
			return sharedFile;
		}
	}
	protected abstract static class AbsFileRowMapper {
		private final CompanyDAO companyDAO;
		public AbsFileRowMapper(CompanyDAO pCompanyDAO) {
			this.companyDAO = pCompanyDAO;
		}
		public SharedFile mapRow(SharedFile sharedFile, ResultSet rs) throws SQLException {
			sharedFile.setId(rs.getInt("Id"));
			sharedFile.setCompany(companyDAO.load(rs.getInt("CompanyId")));
			sharedFile.setFileName(rs.getString("FileName"));
			sharedFile.setMimeType(rs.getString("MimeType"));
			sharedFile.setSize(rs.getLong("Size"));
			sharedFile.setValidFrom(rs.getDate("ValidFrom"));
			sharedFile.setValidTo(rs.getDate("ValidTo"));
			return sharedFile;
		}
	}
	
	private static final String SHAREDFILE_INSERT = "INSERT INTO SharedFile (Name, Folder, ValidFrom, ValidTo)" +
	" VALUES (:name, :folder, :validfrom, :validto)";
private static final String SHAREDFILE_UPDATE = "UPDATE company SET" +
	" Name=:name, Folder=:folder, ValidFrom=:validFrom, ValidTo=:validTo WHERE Id=:id";
private static final String SHAREDFILE_DELETE = "DELETE FROM company WHERE Id = ?";
private static final String SHAREDFILE_SELECT = "SELECT Id, Name, Folder, ValidFrom, ValidTo FROM company";
private static final String SHAREDFILE_BY_ID_SELECT = SHAREDFILE_SELECT + " WHERE Id=?";
//TODO MySQL
private static final String SHAREDFILE_BY_COMPANY_SELECT = SHAREDFILE_SELECT + " WHERE companyId = ?";

	
	@Override
	public List<SharedFile> listByCompany(
			Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer create(SharedFile newInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(SharedFile persistentObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public SharedFile load(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SharedFile transientObject) {
		// TODO Auto-generated method stub

	}

}
