package com.marcalsantarem.CryptoApp.controllers;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcalsantarem.CryptoApp.entities.Coin;
import com.marcalsantarem.CryptoApp.repositories.CoinRepository;

@RestController
@RequestMapping(value="/coin")
public class CoinController {

	@Autowired
	private CoinRepository coinRepository;
	
	@Bean
	public void init() {
		Coin c1 = new Coin();
		c1.setName("Bitcoin");
		c1.setPrice(new BigDecimal(1000));
		c1.setQuantity(new BigDecimal(0.0007));
		post(c1);
		

		Coin c2 = new Coin();
		c2.setName("Etherum");
		c2.setPrice(new BigDecimal(867.45));
		c2.setQuantity(new BigDecimal(0.18));
		post(c2);
		

		Coin c3 = new Coin();
		c3.setName("Bitcoin");
		c3.setPrice(new BigDecimal(1050));
		c3.setQuantity(new BigDecimal(0.0010));
		post(c3);
	}
	
	@GetMapping
	public ResponseEntity get() {
		return new ResponseEntity<>(coinRepository.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value="/{name}")
	public ResponseEntity get(@PathVariable String name) {		
		try {
			return new ResponseEntity<>(coinRepository.getByName(name), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity post(@RequestBody Coin coin) {
		try {
			coin.setDateTime(new Timestamp(System.currentTimeMillis()));
			return new ResponseEntity<>(coinRepository.insert(coin), HttpStatus.CREATED);
		} catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	public ResponseEntity put(@RequestBody Coin coin) {
		try {
			coin.setDateTime(new Timestamp(System.currentTimeMillis()));
			return new ResponseEntity<>(coinRepository.update(coin), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(value="{id}")
	public ResponseEntity delete(@PathVariable int id) {
		try {
			return new ResponseEntity<>(coinRepository.remove(id), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
		}
	}

}
