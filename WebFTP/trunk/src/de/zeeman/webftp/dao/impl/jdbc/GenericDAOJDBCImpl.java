package de.zeeman.webftp.dao.impl.jdbc;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public abstract class GenericDAOJDBCImpl {

	private SimpleJdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(SimpleJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	protected SimpleJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}
