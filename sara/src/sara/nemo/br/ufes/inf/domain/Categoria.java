package sara.nemo.br.ufes.inf.domain;


public class Categoria {
	String tipoCategoria;
	String classe;
	String especificacao;
	String passageiroOuCargueiro;
	
	public String getTipoCategoria() {
		return tipoCategoria;
	}
	public void setTipoCategoria(String tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getEspecificacao() {
		return especificacao;
	}
	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}
	public String getPassageiroOuCargueiro() {
		return passageiroOuCargueiro;
	}
	public void setPassageiroOuCargueiro(String passageiroOuCargueiro) {
		this.passageiroOuCargueiro = passageiroOuCargueiro;
	}
	
	Categoria(String tipoCategoria, String classe, String especificacao, String passageiroOuCargueiro){
		this.tipoCategoria= tipoCategoria;
		this.classe= classe;
		this.especificacao= especificacao;
		this.passageiroOuCargueiro= passageiroOuCargueiro;
	}
	
	Categoria(){}
	
}
