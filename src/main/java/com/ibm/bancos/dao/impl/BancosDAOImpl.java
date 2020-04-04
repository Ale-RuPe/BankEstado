package com.ibm.bancos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibm.bancos.dao.BancosDAO;
import com.ibm.bancos.entity.Banco;
import com.ibm.bancos.entity.Geometry;
import com.ibm.bancos.entity.Propiedades;
import com.ibm.bancos.repository.BancoRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BancosDAOImpl implements BancosDAO{
	@Autowired
	BancoRepository repository;
	
	@HystrixCommand(fallbackMethod = "findByEstadoFallback")
	@Override
	public List<Banco> findBankByState(String estado) {
		return repository.findByPropiedadesEstado(estado);
	}
	
	public List<Banco> findByEstadoFallback(String estado){
		log.info("Fallback Method");
		Banco bank = new Banco();
		Geometry geom = new Geometry();
		Propiedades prop = new Propiedades();
		bank.setGeometry(geom);
		bank.setPropiedades(prop);
		List<Banco> banks = new ArrayList<>();
		banks.add(bank);
		return banks;
	}
}
