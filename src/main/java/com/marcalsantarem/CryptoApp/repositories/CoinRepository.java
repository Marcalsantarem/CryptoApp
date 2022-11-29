package com.marcalsantarem.CryptoApp.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.marcalsantarem.CryptoApp.dto.CoinTransactionDTO;
import com.marcalsantarem.CryptoApp.entities.Coin;

@Repository
@EnableAutoConfiguration
public class CoinRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	public Coin insert(Coin coin) {
		entityManager.persist(coin);
		return coin;
	}

	@Transactional
	public Coin update(Coin coin) {
		entityManager.merge(coin);
		return coin;
	}
	
	public List<CoinTransactionDTO> getAll(){
		String jpql = "select new com.marcalsantarem.CryptoApp.dto.CoinTransactionDTO(c.name, sum(c.quantity)) from Coin c group by c.name";
		TypedQuery<CoinTransactionDTO> query = entityManager.createQuery(jpql, CoinTransactionDTO.class);
		return query.getResultList();
	}
	
	public List<Coin> getByName(String name){
		String jpql = "select c from Coin c where c.name like :name";
		TypedQuery<Coin> query = entityManager.createQuery(jpql, Coin.class);
		query.setParameter("name", "%" + name + "%");
		return query.getResultList();
	}
	

	@Transactional
	public boolean remove(int id) {
		Coin coin = entityManager.find(Coin.class, id);
		if (!entityManager.contains(coin)) {
			coin = entityManager.merge(coin);
		}
		entityManager.remove(coin);
		return true;
	} 

}