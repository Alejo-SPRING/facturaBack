package com.facturaBack.backend.app.model.services;

import org.springframework.data.repository.CrudRepository;

import com.facturaBack.backend.app.model.entity.Producto;

public interface IProductoServiceDao extends CrudRepository<Producto, Long>{

}
