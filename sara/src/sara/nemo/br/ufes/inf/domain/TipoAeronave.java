package sara.nemo.br.ufes.inf.domain;



public class TipoAeronave {
	String equipamento;
	float comprimento;
	float envergadura;
	float pmd;
	
	protected TipoAeronave() {
	}
	public String getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
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
	public TipoAeronave(String equipamento, float comprimento, float envergadura, float pmd) {
		this.equipamento = equipamento;
		this.comprimento = comprimento;
		this.envergadura = envergadura;
		this.pmd = pmd;
	}
	
	
}
