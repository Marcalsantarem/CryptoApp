package com.marcalsantarem.CryptoApp.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.marcalsantarem.CryptoApp.dto.CoinTransactionDTO;
import com.marcalsantarem.CryptoApp.entities.Coin;

@Repository
@EnableAutoConfiguration
public class CoinRepository {
	
	private String insert = "insert into coin (name, price, quantity, datetime) values (?,?,?,?)";
	
	private String selectAll = "select name, sum(quantity) as quantity from coin group by name";
	
	private String selectByName = "select * from coin where name = ?";
	
	private String delete = "delete from coin where id = ?";
	
	private String update = "update coin set name = ?, price = ?, quantity = ? where id = ?";
	
	private JdbcTemplate jdbcTemplate;
	
	public CoinRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Coin insert(Coin coin) {
		Object[] attr = new Object[] {
				coin.getName(),
				coin.getPrice(),
				coin.getQuantity(),
				coin.getDateTime()
		};
		jdbcTemplate.update(insert, attr);
		return coin;
	}
	
	public Coin update(Coin coin) {
		Object[] attr = new Object[] {
				coin.getName(),
				coin.getPrice(),
				coin.getQuantity(),
				coin.getId()	
		};
		jdbcTemplate.update(update, attr);
		return coin;
	}
	
	public List<CoinTransactionDTO> getAll(){
		return jdbcTemplate.query(selectAll, new RowMapper<CoinTransactionDTO>() {
			@Override
			public CoinTransactionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				CoinTransactionDTO coin = new CoinTransactionDTO();
				coin.setName(rs.getString("name"));
				coin.setQuantity(rs.getBigDecimal("quantity"));
				return coin;
			}
		});
	}
	
	public List<Coin> getByName(String name){
		Object[] attr = new Object[] { 
				name 
		};
		return jdbcTemplate.query(selectByName, new RowMapper<Coin>() {
			@Override
			public Coin mapRow(ResultSet rs, int rowNum) throws SQLException {
				Coin coin = new Coin();
				coin.setId(rs.getInt("id"));
				coin.setName(rs.getString("name"));
				coin.setPrice(rs.getBigDecimal("price"));
				coin.setQuantity(rs.getBigDecimal("quantity"));
				coin.setDateTime(rs.getTimestamp("datetime"));
				return coin;
			}
		}, attr);
	}
	
	public int remove(int id) {
		return jdbcTemplate.update(delete, id);
	}

}