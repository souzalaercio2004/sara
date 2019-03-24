package sara.nemo.br.ufes.inf.domain;

public class PosicaoPatio extends Recurso {
	private String nomeDaPosicao;
	private float comprimentoToleravel;
	private float envergaduratoleravel;
	private String aeronaveCritica;
	

	public PosicaoPatio(String tipoRecurso, Boolean estaEmUso, String nomeDaPosicao, float comprimentoToleravel, float envergaduratoleravel,
			String aeronaveCritica) {
		super(tipoRecurso, estaEmUso);
		this.nomeDaPosicao = nomeDaPosicao;
		this.comprimentoToleravel = comprimentoToleravel;
		this.envergaduratoleravel = envergaduratoleravel;
		this.aeronaveCritica = aeronaveCritica;
	}


	public String getNomeDaPosicao() {
		return nomeDaPosicao;
	}


	public void setNomeDaPosicao(String nomeDaPosicao) {
		this.nomeDaPosicao = nomeDaPosicao;
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
