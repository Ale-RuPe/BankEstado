package com.ibm.bancos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.bancos.model.BancoModel;
import com.ibm.bancos.service.BancosEstadoService;
import com.ibm.bancos.validator.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BancosEstadoController {
	@Autowired
	Validator validator;
	
	@Autowired
	BancosEstadoService service;
	
	
	@GetMapping("${controller.uri}")
	public ResponseEntity<List<BancoModel>> getBancos(@RequestHeader HttpHeaders httpHeaders,
			@PathVariable String state) throws MissingServletRequestParameterException {
		
		log.info("Headers {}", httpHeaders.toString());
		validator.validateHeaders(httpHeaders.toSingleValueMap());
		
		log.info("PathVariable: {}", state);
		validator.validateParam(state);
		
		List<BancoModel> bancos = service.findBancos(state);
		log.info("Returning response: {}", bancos.size());
		
		return new ResponseEntity<>(bancos, HttpStatus.OK);
	}
}
