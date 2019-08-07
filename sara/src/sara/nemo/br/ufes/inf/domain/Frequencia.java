package sara.nemo.br.ufes.inf.domain;

public class Frequencia {
	int idFrequencia;
	private boolean domingo;
	private boolean segunda;
	private boolean terca;
	private boolean quarta;
	private boolean quinta;
	private boolean sexta;
	private boolean sabado;
	
	public Frequencia() {};
	
	public int getIdFrequencia() {
		return idFrequencia;
	}
	public void setIdFrequencia(int idFrequencia) {
		this.idFrequencia = idFrequencia;
	}
	public boolean isDomingo() {
		return domingo;
	}
	public void setDomingo(boolean domingo) {
		this.domingo = domingo;
	}
	public boolean isSegunda() {
		return segunda;
	}
	public void setSegunda(boolean segunda) {
		this.segunda = segunda;
	}
	public boolean isTerca() {
		return terca;
	}
	public void setTerca(boolean terca) {
		this.terca = terca;
	}
	public boolean isQuarta() {
		return quarta;
	}
	public void setQuarta(boolean quarta) {
		this.quarta = quarta;
	}
	public boolean isQuinta() {
		return quinta;
	}
	public void setQuinta(boolean quinta) {
		this.quinta = quinta;
	}
	public boolean isSexta() {
		return sexta;
	}
	public void setSexta(boolean sexta) {
		this.sexta = sexta;
	}
	public boolean isSabado() {
		return sabado;
	}
	public void setSabado(boolean sabado) {
		this.sabado = sabado;
	}
	
}
