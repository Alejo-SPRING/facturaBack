package com.facturaBack.backend.app.util;

public enum MensajesEnum {

	NO_EXISTE_CLIENTE("¡No se encontro el cliente!"),
	NO_EXISTE_PRODUCTO("¡No se encontro el producto!"),
	NO_HAY_EXISTENCIAS("¡No hay existencias suficientes!"),
	FACTURA_CREADA("¡La factura ha sido creada!");
	
	private String mensaje;

	private MensajesEnum(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
