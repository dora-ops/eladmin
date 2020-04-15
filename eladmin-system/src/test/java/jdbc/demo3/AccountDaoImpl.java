package jdbc.demo3;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


public class AccountDaoImpl implements AccountDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void outMoney(String out, Double money) {
		String sql = "update account set money = money - ? where name = ?";
		this.jdbcTemplate.update(sql,money,out);
	}

	@Override
	public void inMoney(String in, Double money) {
		String sql = "update account set money = money + ? where name = ?";
		this.jdbcTemplate.update(sql, money,in);
	}

}
