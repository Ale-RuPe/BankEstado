package com.ibm.bancos.dao;

import java.util.List;

import com.ibm.bancos.entity.Banco;

public interface BancosDAO {
	List<Banco> findBankByState(String estado);
}
