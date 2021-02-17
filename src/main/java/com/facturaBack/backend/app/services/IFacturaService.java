package com.facturaBack.backend.app.services;

import org.springframework.http.ResponseEntity;

import com.facturaBack.backend.app.pojos.FacturaPojo;

public interface IFacturaService {

	public ResponseEntity<?> crearFactura(FacturaPojo factura);
	
	public ResponseEntity<?> verFacturas(int pagina);

}
