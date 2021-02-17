package com.facturaBack.backend.app.model.services;

import org.springframework.data.repository.CrudRepository;

import com.facturaBack.backend.app.model.entity.Cliente;

public interface IClienteServiceDao extends CrudRepository<Cliente, Long>{

}
