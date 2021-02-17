package com.facturaBack.backend.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.facturaBack.backend.app.model.entity.Cliente;
import com.facturaBack.backend.app.model.services.IClienteServiceDao;

@RestController
@RequestMapping("/cliente")
public class RestCliente {

	@Autowired
	private IClienteServiceDao clienteServiceDao;
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<List<Cliente>>((List<Cliente>) clienteServiceDao.findAll(), HttpStatus.OK);
	}
	
}
