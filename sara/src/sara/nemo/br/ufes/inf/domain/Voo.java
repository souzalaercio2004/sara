package sara.nemo.br.ufes.inf.domain;

public class Voo {
	private int idVoo;
	private int idAeronave;
	private int idCategoria;
	private String nomeBox;
	
	
	public Voo() {}

	public int getIdVoo() {
		return idVoo;
	}

	public void setIdVoo(int idVoo) {
		this.idVoo = idVoo;
	}

	public int getIdAeronave() {
		return idAeronave;
	}

	public void setIdAeronave(int idAeronave) {
		this.idAeronave = idAeronave;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	
	public String getNomeBox() {
		return nomeBox;
	}

	public void setNomeBox(String nomeBox) {
		this.nomeBox = nomeBox;
	}

	@Override
	public String toString() {
		return idVoo+" "+idAeronave+" "+idCategoria+" "+nomeBox;
	}

	

	
}
