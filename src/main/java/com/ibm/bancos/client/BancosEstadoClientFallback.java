package com.ibm.bancos.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.bancos.entity.Banco;
import com.ibm.bancos.entity.Geometry;
import com.ibm.bancos.entity.Propiedades;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BancosEstadoClientFallback implements BancosEstadoClient{

	@Override
	public List<Banco> retrieveEstadoBanks(String estado) {
		log.info("Fallback method feign");
		Banco banco = new Banco();
		Geometry geo = new Geometry();
		Propiedades pro = new Propiedades();
		
		List<Banco> bancos = new ArrayList<>();
		banco.setGeometry(geo);
		banco.setPropiedades(pro);
		bancos.add(banco);
		
		return bancos;
	}

}

