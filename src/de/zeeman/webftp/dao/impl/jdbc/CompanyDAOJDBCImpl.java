package de.zeeman.webftp.dao.impl.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import de.zeeman.webftp.dao.CompanyDAO;
import de.zeeman.webftp.domain.Company;

public class CompanyDAOJDBCImpl extends GenericDAOJDBCImpl implements CompanyDAO {

	private final class CompanyRowMapper implements ParameterizedRowMapper<Company> {
		public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
			Company company = new Company();
			company.setId(rs.getInt("Id"));
			company.setName(rs.getString("Name"));
			company.setFolder(rs.getString("Folder"));
			company.setValidFrom(rs.getDate("ValidFrom"));
			company.setValidTo(rs.getDate("ValidTo"));
			return company;
		}
	}

	private static final String COMPANY_INSERT = "INSERT INTO company (Name, Folder, ValidFrom, ValidTo)" +
			" VALUES (:name, :folder, :validfrom, :validto)";
	private static final String COMPANY_UPDATE = "UPDATE company SET" +
			" Name=:name, Folder=:folder, ValidFrom=:validFrom, ValidTo=:validTo WHERE Id=:id";
	private static final String COMPANY_DELETE = "DELETE FROM company WHERE Id = ?";
	private static final String COMPANY_SELECT = "SELECT Id, Name, Folder, ValidFrom, ValidTo FROM company";
	private static final String COMPANY_BY_ID_SELECT = COMPANY_SELECT + " WHERE Id=?";
	//TODO MySQL
	private static final String COMPANY_VALID_SELECT = COMPANY_SELECT + " WHERE validFrom <= NOW() AND (ValidTo IS NULL OR ValidTo >= NOW())";
	
	
	@Override
	public List<Company> listAllValid() {
		return getJdbcTemplate().query(COMPANY_VALID_SELECT, new CompanyRowMapper());
	}

	@Override
	public Integer create(Company newInstance) {
		Map<String, Object> companyMap = new HashMap<String, Object>();
		companyMap.put("name", newInstance.getName());
		companyMap.put("folder", newInstance.getFolder());
		companyMap.put("validFrom", newInstance.getValidFrom());
		companyMap.put("validTo", newInstance.getValidTo());
		
		getJdbcTemplate().update(COMPANY_INSERT, companyMap);
		//FIXME
		return -1;
	}

	@Override
	public void delete(Company persistentObject) {
		getJdbcTemplate().update(COMPANY_DELETE, persistentObject.getId());
	}

	@Override
	public Company load(Integer id) {
		List<Company> matches = getJdbcTemplate().query(COMPANY_BY_ID_SELECT, new CompanyRowMapper(), id);

		assert matches.size() < 2;
		return matches.size() > 0 ? matches.get(0) : null;
	}

	@Override
	public void update(Company transientObject) {
		Map<String, Object> customerMap = new HashMap<String, Object>();
		customerMap.put("id", transientObject.getId());
		customerMap.put("name", transientObject.getName());
		customerMap.put("folder", transientObject.getFolder());
		customerMap.put("validFrom", transientObject.getValidFrom());
		customerMap.put("validTo", transientObject.getValidTo());
		
		getJdbcTemplate().update(COMPANY_UPDATE, customerMap);
	}

}
