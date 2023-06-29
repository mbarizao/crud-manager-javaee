package com.rh.manager.model;

public class AccessLog {

	private int id;
	private String pagina;
	private String enderecoIp;
	private int usuarioId;

	public AccessLog() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPagina() {
		return this.pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getEnderecoIP() {
		return this.enderecoIp;
	}

	public void setEnderecoIP(String enderecoIp) {
		this.enderecoIp = enderecoIp;
	}

	public int getUsuarioID() {
		return this.usuarioId;
	}

	public void setUsuarioID(int usuarioId) {
		this.usuarioId = usuarioId;
	}
}
