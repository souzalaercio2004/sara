package sara.nemo.br.ufes.inf.domain;

public class ProprietarioParticular extends Proprietario{
	boolean querAbastecimento;
	String tipoCombustivel;
	
	public ProprietarioParticular(String proprietario, Aeronave aeronaves) {
		super(proprietario, aeronaves);
		// TODO Auto-generated constructor stub
	}

	
	public boolean isQuerAbastecimento() {
		return querAbastecimento;
	}
	public void setQuerAbastecimento(boolean querAbastecimento) {
		this.querAbastecimento = querAbastecimento;
	}
	public String getTipoCombustivel() {
		return tipoCombustivel;
	}
	public void setTipoCombustivel(String tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}
	public ProprietarioParticular(String proprietario, Aeronave aeronaves, boolean querAbastecimento, String tipoCombustivel) {
		
		super(proprietario, aeronaves);
		
		this.querAbastecimento = querAbastecimento;
		this.tipoCombustivel = tipoCombustivel;
	}
	
	
}
