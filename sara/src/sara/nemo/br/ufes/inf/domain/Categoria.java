package sara.nemo.br.ufes.inf.domain;


public class Categoria {
	int id;
	String tipoCategoria;
	String classe;
	String especificacao;
	String passageiroOuCargueiro;
	

	public Categoria(){}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


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
	
	public String toString(){
		return("id: "+this.getId()+" Categoria: "+this.getTipoCategoria()+" Especificação: "+this.getEspecificacao()+" Passageiro ou Cargueiro? "+this.getPassageiroOuCargueiro());
	}
	
	public String abreviarCategoria() {
		String tipo= this.getTipoCategoria();
		String paxOuCargo= this.getPassageiroOuCargueiro();
		String classe= this.getClasse();
		String especificacao= this.getEspecificacao();
		
		if (tipo.equals("Doméstico")) {
			tipo= "DOM";
		}else if(tipo.equals("Internacional")) {
			tipo= "INT";
		}
		
		if (paxOuCargo.equals("Passageiro")) {
			paxOuCargo= "PAX";
		}else if (paxOuCargo.equals("Cargueiro")) {
			paxOuCargo= "CAR";
		}
		
		if (classe.equals("Regular")) {
			classe= "REG";
		}else if (classe.equals("Não Regular")) {
			classe= "NRG";
		}
		
		if (especificacao.equals("Alternado")) {
			especificacao= "ALT";
		}else if (especificacao.equals("Charter")) {
			especificacao= "CHT";
		}else if (especificacao.equals("Fretado")) {
			especificacao.equals("FRT");
		}else if (especificacao.equals("Militar")) {
			especificacao= "MLT";
		}else if (especificacao.equals("Translado")) {
			especificacao= "TRL";
		}else if (especificacao.equals("Regular")) {
			especificacao= "REG";
		}
		
		return(tipo+"-"+ paxOuCargo +"-"+ classe +"-"+ especificacao);
	}
}
