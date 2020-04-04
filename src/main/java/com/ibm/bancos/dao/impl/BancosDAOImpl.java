package com.ibm.bancos.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibm.bancos.client.BancosEstadoClient;
import com.ibm.bancos.dao.BancosDAO;
import com.ibm.bancos.entity.Banco;
import com.ibm.bancos.repository.BancoRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BancosDAOImpl implements BancosDAO{
	@Autowired
	BancoRepository repository;
	
	@Autowired
	BancosEstadoClient feignClient; 
	
	@HystrixCommand(fallbackMethod = "findByEstadoFallback")
	@Override
	public List<Banco> findBankByState(String estado) {
		return repository.findByPropiedadesEstado(estado);
	}
	
	public List<Banco> findByEstadoFallback(String estado){
		log.info("Fallback Method");
		
		List<Banco> response = feignClient.retrieveEstadoBanks(estado);
		
		log.info("Retrieving fallback method {}", response.size());
		return response;
	}
}
