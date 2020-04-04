package com.ibm.bancos.service;

import java.util.List;

import com.ibm.bancos.model.BancoModel;

public interface BancosEstadoService {
	List<BancoModel> findBancos(String estado);
}
