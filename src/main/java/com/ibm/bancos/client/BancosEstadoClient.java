package com.ibm.bancos.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ibm.bancos.entity.Banco;

@FeignClient(name = "${client.estado.name}", url = "${client.estado.url}", fallback = BancosEstadoClientFallback.class)
public interface BancosEstadoClient {
	
	@GetMapping("${client.estado.uri}")
	public List<Banco> retrieveEstadoBanks(@PathVariable String estado);
}