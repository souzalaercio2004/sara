package sara.nemo.br.ufes.inf.domain;



public class TipoAeronave {
	int idTipoAeronave;
	String equipamento;
	float comprimento;
	float envergadura;
	float pmd;
	
	public TipoAeronave() {
	}
	
	public int getIdTipoAeronave() {
		return idTipoAeronave;
	}

	public void setIdTipoAeronave(int idTipoAeronave) {
		this.idTipoAeronave = idTipoAeronave;
	}

	public String getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento.toUpperCase(); //Converte a string para Letras maiúsculas
	}
	public float getComprimento() {
		return comprimento;
	}
	public void setComprimento(float comprimento) {
		this.comprimento = comprimento;
	}
	public float getEnvergadura() {
		return envergadura;
	}
	public void setEnvergadura(float envergadura) {
		this.envergadura = envergadura;
	}
	public float getPmd() {
		return pmd;
	}
	public void setPmd(float pmd) {
		this.pmd = pmd;
	}	
	
}
