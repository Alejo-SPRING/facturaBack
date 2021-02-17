package com.facturaBack.backend.app.model.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.facturaBack.backend.app.model.entity.Factura;

public interface IFacturaServiceDao extends CrudRepository<Factura, Long>{

	@Query("SELECT f FROM Factura f")
	Page<Factura> findAllPage(Pageable pageable);
	
}
