package com.facturaBack.backend.app.model.services;

import org.springframework.data.repository.CrudRepository;

import com.facturaBack.backend.app.model.entity.Detalle;

public interface IDetalleServiceDao extends CrudRepository<Detalle, Long>{

}
