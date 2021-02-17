package com.facturaBack.backend.app.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.facturaBack.backend.app.model.entity.Cliente;
import com.facturaBack.backend.app.model.entity.Detalle;
import com.facturaBack.backend.app.model.entity.Factura;
import com.facturaBack.backend.app.model.entity.Producto;
import com.facturaBack.backend.app.model.services.IClienteServiceDao;
import com.facturaBack.backend.app.model.services.IDetalleServiceDao;
import com.facturaBack.backend.app.model.services.IFacturaServiceDao;
import com.facturaBack.backend.app.model.services.IProductoServiceDao;
import com.facturaBack.backend.app.pojos.FacturaPojo;
import com.facturaBack.backend.app.pojos.ProductoPojo;
import com.facturaBack.backend.app.util.MensajesEnum;

@Service
public class FacturaServiceImpl implements IFacturaService{

	@Autowired
	private IDetalleServiceDao detalleServiceDao;
	@Autowired
	private IProductoServiceDao productoServiceDao;
	@Autowired
	private IClienteServiceDao clienteServiceDao;
	@Autowired
	private IFacturaServiceDao facturaServiceDao;
	private Map<String, Object> body;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public ResponseEntity<?> crearFactura(FacturaPojo factura) {
		Cliente cliente = clienteServiceDao.findById(factura.getClienteId()).orElse(null);
		if(cliente != null) {
			Factura facturaDto = new Factura();
			facturaDto.setIdCliente(cliente);
			facturaDto.setFecha(new Date());
			for(ProductoPojo producto : factura.getProductos()) {
				Producto productoDto = productoServiceDao.findById(producto.getIdProducto()).orElse(null); 
				if(productoDto == null) {
					throw new DataIntegrityViolationException(MensajesEnum.NO_EXISTE_PRODUCTO + " con el id:" +  producto.getIdProducto());
				}
				if(productoDto.getStock() < producto.getCantidad()) {
					throw new DataIntegrityViolationException(MensajesEnum.NO_HAY_EXISTENCIAS + " para el producto con el id:" +  producto.getIdProducto());
				}
				productoDto.setStock(productoDto.getStock() - producto.getCantidad());
				Detalle detalle = new Detalle();
				detalle.setCantidad(producto.getCantidad());
				detalle.setIdProducto(productoDto);
				detalle.setIdFactura(facturaDto);
				detalle.setPrecio(new BigDecimal(producto.getCantidad()).multiply(productoDto.getPrecio()));
				detalleServiceDao.save(detalle);
			}
			body = new HashMap<>();
			body.put("mensaje", MensajesEnum.FACTURA_CREADA);
			return new ResponseEntity<Map<String,Object>>(body, HttpStatus.CREATED);
		} else {
			body = new HashMap<>();
			body.put("mensaje", MensajesEnum.NO_EXISTE_CLIENTE);
			return new ResponseEntity<Map<String,Object>>(body, HttpStatus.NOT_FOUND);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public ResponseEntity<?> verFacturas(int pagina) {
		Pageable page = PageRequest.of((pagina - 1), 10);
		return new ResponseEntity<Page<Factura>>(facturaServiceDao.findAllPage(page), HttpStatus.OK);
	}
	
}
