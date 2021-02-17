package com.facturaBack.backend.app.pojos;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FacturaPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message = "Ingresa un valor")
	private Long clienteId;
	@NotNull(message =  "Ingresa un valor")
	@NotEmpty(message =  "Ingresa un valor")
	@Valid
	private List<ProductoPojo> productos;

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public List<ProductoPojo> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoPojo> productos) {
		this.productos = productos;
	}

}
