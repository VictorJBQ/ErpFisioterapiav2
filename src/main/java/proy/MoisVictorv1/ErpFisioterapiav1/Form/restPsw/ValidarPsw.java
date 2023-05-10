package proy.MoisVictorv1.ErpFisioterapiav1.Form.restPsw;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ValidarPsw {

	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String psw1;
	
	
	@NotBlank(message = "No puede estar vacío")
	@Pattern(regexp = "^[^\\s]*$", message = "No puede contener espacios en blanco")
	private String ps2;
	private String email1;
	private String token;
	private String expiration;
	public String getPsw1() {
		return psw1;
	}
	public void setPsw1(String psw1) {
		this.psw1 = psw1;
	}
	public String getPs2() {
		return ps2;
	}
	public void setPs2(String ps2) {
		this.ps2 = ps2;
	}

	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public ValidarPsw() {
		super();
	}
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	public ValidarPsw(String psw1, String ps2, String email1, String token, String expiration) {
		super();
		this.psw1 = psw1;
		this.ps2 = ps2;
		this.email1 = email1;
		this.token = token;
		this.expiration = expiration;
	}

	
	
}
