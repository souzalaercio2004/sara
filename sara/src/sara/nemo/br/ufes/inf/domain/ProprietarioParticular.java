package sara.nemo.br.ufes.inf.domain;


public class ProprietarioParticular extends Proprietario{
	int id;
	boolean querAbastecimento;
	String tipoCombustivel;
	
	public ProprietarioParticular(){
		super();
	};
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	
	
}
