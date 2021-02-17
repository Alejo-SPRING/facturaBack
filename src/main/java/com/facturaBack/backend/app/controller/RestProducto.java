package com.facturaBack.backend.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facturaBack.backend.app.model.entity.Producto;
import com.facturaBack.backend.app.model.services.IProductoServiceDao;

@RestController
@RequestMapping("/producto")
public class RestProducto {

	@Autowired
	private IProductoServiceDao productoServiceDao;
	private Map<String, Object> body;
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		try {
			return new ResponseEntity<List<Producto>>((List<Producto>) productoServiceDao.findAll(), HttpStatus.OK);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String,Object>>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
