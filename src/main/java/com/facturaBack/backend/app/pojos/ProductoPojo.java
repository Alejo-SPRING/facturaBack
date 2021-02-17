package com.facturaBack.backend.app.pojos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ProductoPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message = "Ingresa un valor")
	private Long idProducto;
	@NotNull(message = "Ingresa un valor")
	private Integer cantidad;

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
