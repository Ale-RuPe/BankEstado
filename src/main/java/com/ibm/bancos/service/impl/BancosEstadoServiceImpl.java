package com.ibm.bancos.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.bancos.dao.BancosDAO;
import com.ibm.bancos.entity.Banco;
import com.ibm.bancos.model.BancoModel;
import com.ibm.bancos.model.exceptions.NotFoundException;
import com.ibm.bancos.service.BancosEstadoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BancosEstadoServiceImpl implements BancosEstadoService {
	@Autowired
	BancosDAO dao;
	
	@Override
	public List<BancoModel> findBancos(String estado) {
		log.info("findBancos service with param: {}",estado);
		
		List<Banco> bancos = dao.findBankByState(estado);
		List<BancoModel> response = bancos.stream().map( this::createBancoModel).collect(Collectors.toList() );
		if(response.size()==0)
			throw new NotFoundException("${exceptions.message.notfound}","${exceptions.location.service}", "${controller.uri}");
		log.info("Retrieving response {}",response);
		
		return response;
	}
	
	public BancoModel createBancoModel(Banco banco){
		BancoModel model = new BancoModel();
		
		model.setDireccion(banco.getPropiedades().getDireccion());
		model.setEstado(banco.getPropiedades().getEstado());
		model.setHoraApertura(banco.getPropiedades().getHora_apertura());
		model.setHoraCierre(banco.getPropiedades().getHora_cierre());
		model.setNombre(banco.getPropiedades().getNombre());
		model.setTelefono(banco.getPropiedades().getTelefono());
		model.setTipoSucursal(banco.getPropiedades().getTipo_sucursal());
		
		return model;
	}
}
