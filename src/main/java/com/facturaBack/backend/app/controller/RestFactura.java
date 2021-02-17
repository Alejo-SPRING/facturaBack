package com.facturaBack.backend.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facturaBack.backend.app.pojos.FacturaPojo;
import com.facturaBack.backend.app.services.IFacturaService;

@RestController
@RequestMapping("/factura")
public class RestFactura {

	@Autowired
	private IFacturaService facturaService;
	private Map<String, Object> body;
	
	@PostMapping("/crearFactura")
	public ResponseEntity<?> crearFactura(@Valid @RequestBody FacturaPojo factura, BindingResult result){
		if(result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream().map(error -> {
				return error.getDefaultMessage() + " en el campo:" + error.getField();
			}).collect(Collectors.toList());
			body = new HashMap<>();
			body.put("mensaje", errores);
			return new ResponseEntity<Map<String, Object>>(body, HttpStatus.BAD_REQUEST);
		}
		try {
			return facturaService.crearFactura(factura);
		} catch (DataAccessException e) {
			e.printStackTrace();
			body = new HashMap<>();
			body.put("mensaje", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
