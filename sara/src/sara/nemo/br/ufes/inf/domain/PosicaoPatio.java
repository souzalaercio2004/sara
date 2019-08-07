package sara.nemo.br.ufes.inf.domain;

public class PosicaoPatio extends Recurso {
	int idPosicaoPatio;
	private String nome;
	private float comprimentoToleravel;
	private float envergaduratoleravel;
	private String aeronaveCritica;
	
	public PosicaoPatio() {
		super();
	}
	

	
	public int getIdPosicaoPatio() {
		return idPosicaoPatio;
	}



	public void setIdPosicaoPatio(int idPosicaoPatio) {
		this.idPosicaoPatio = idPosicaoPatio;
	}
	
	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public float getComprimentoToleravel() {
		return comprimentoToleravel;
	}
	public void setComprimentoToleravel(float comprimentoToleravel) {
		this.comprimentoToleravel = comprimentoToleravel;
	}
	public float getEnvergaduratoleravel() {
		return envergaduratoleravel;
	}
	public void setEnvergaduratoleravel(float envergaduratoleravel) {
		this.envergaduratoleravel = envergaduratoleravel;
	}
	public String getAeronaveCritica() {
		return aeronaveCritica;
	}
	public void setAeronaveCritica(String aeronaveCritica) {
		this.aeronaveCritica = aeronaveCritica;
		
	}
	
}
