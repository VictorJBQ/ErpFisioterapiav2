package proy.MoisVictorv1.ErpFisioterapiav1.Form.restPsw;

import jakarta.validation.constraints.NotBlank;

public class OlvidoPsw {

	@NotBlank(message = "No puede estar vacío")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public OlvidoPsw(String email) {
		super();
		this.email = email;
	}
	
	 public OlvidoPsw() {};
	
	
	
}
