package sara.nemo.br.ufes.inf.domain.item;

import sara.nemo.br.ufes.inf.domain.Categoria;

public class ItemCategoria {
	private int idItemCategoria;
	private String dados;
	
	
	public ItemCategoria(Categoria cat) {
		this.idItemCategoria= cat.getId();
		this.dados= cat.getTipoCategoria()+" "+cat.getClasse()+" "+cat.getEspecificacao()+" "+ cat.getPassageiroOuCargueiro();
		
	}


	public int getIdItemCategoria() {
		return idItemCategoria;
	}


	public void setIdItemCategoria(int idItemCategoria) {
		this.idItemCategoria = idItemCategoria;
	}


	public String getDados() {
		return dados;
	}


	public void setDados(String dados) {
		this.dados = dados;
	}
	
	
}