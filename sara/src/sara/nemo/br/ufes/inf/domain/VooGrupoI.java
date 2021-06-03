package sara.nemo.br.ufes.inf.domain;
import java.time.LocalDate;
import java.time.LocalTime;


public class VooGrupoI extends Voo{
	int idVooGrupoI;
	int idHotran;
	int numeroVooPouso;
	int numeroVooDecolagem;
	LocalDate dataConfirmadaPouso;
	LocalDate dataConfirmadaDecolagem;
	LocalTime horaConfirmadaPouso;
	LocalTime horaConfirmadaDecolagem;
	int idProprietarioCiaAerea;
	private int nomeCabeceira;
	private String nomeBox;
	private String nomeEsteira;
	private String portaoDeEmbarque;

	public VooGrupoI() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdVooGrupoI() {
		return idVooGrupoI;
	}

	public void setIdVooGrupoI(int idVooGrupoI) {
		this.idVooGrupoI = idVooGrupoI;
	}


	public int getIdHotran() {
		return idHotran;
	}

	public void setIdHotran(int idHotran) {
		this.idHotran = idHotran;
	}

	public int getNumeroVooPouso() {
		return numeroVooPouso;
	}

	public void setNumeroVooPouso(int numeroVooPouso) {
		this.numeroVooPouso = numeroVooPouso;
	}

	public int getNumeroVooDecolagem() {
		return numeroVooDecolagem;
	}

	public void setNumeroVooDecolagem(int numeroVooDecolagem) {
		this.numeroVooDecolagem = numeroVooDecolagem;
	}

	public LocalDate getDataConfirmadaPouso() {
		return dataConfirmadaPouso;
	}

	public void setDataConfirmadaPouso(LocalDate dataConfirmadaPouso) {
		this.dataConfirmadaPouso = dataConfirmadaPouso;
	}

	public LocalDate getDataConfirmadaDecolagem() {
		return dataConfirmadaDecolagem;
	}

	public void setDataConfirmadaDecolagem(LocalDate dataConfirmadaDecolagem) {
		this.dataConfirmadaDecolagem = dataConfirmadaDecolagem;
	}

	public LocalTime getHoraConfirmadaPouso() {
		return horaConfirmadaPouso;
	}

	public void setHoraConfirmadaPouso(LocalTime horaConfirmadaPouso) {
		this.horaConfirmadaPouso = horaConfirmadaPouso;
	}

	public LocalTime getHoraConfirmadaDecolagem() {
		return horaConfirmadaDecolagem;
	}

	public void setHoraConfirmadaDecolagem(LocalTime horaConfirmadaDecolagem) {
		this.horaConfirmadaDecolagem = horaConfirmadaDecolagem;
	}


	public int getIdProprietarioCiaAerea() {
		return idProprietarioCiaAerea;
	}


	public void setIdProprietarioCiaAerea(int idProprietarioCiaAerea) {
		this.idProprietarioCiaAerea = idProprietarioCiaAerea;
	}

	public int getNomeCabeceira() {
		return nomeCabeceira;
	}

	public void setNomeCabeceira(int nomeCabeceira) {
		this.nomeCabeceira = nomeCabeceira;
	}

	public String getNomeBox() {
		return nomeBox;
	}

	public void setNomeBox(String nomeBox) {
		this.nomeBox = nomeBox;
	}

	public String getNomeEsteira() {
		return nomeEsteira;
	}

	public void setNomeEsteira(String nomeEsteira) {
		this.nomeEsteira = nomeEsteira;
	}
	
	

	public String getPortaoDeEmbarque() {
		return portaoDeEmbarque;
	}

	public void setPortaoDeEmbarque(String portaoDeEmbarque) {
		this.portaoDeEmbarque = portaoDeEmbarque;
	}

	public String toString() {
		String vooGrupoI = idVooGrupoI+" "+ idHotran+" "+numeroVooPouso+" "+numeroVooDecolagem+" "+
				dataConfirmadaPouso+" "+horaConfirmadaPouso+" "+dataConfirmadaDecolagem+" "+horaConfirmadaDecolagem+" "+
				idProprietarioCiaAerea+" "+nomeCabeceira+" "+nomeBox+" "+nomeEsteira;
		
		return vooGrupoI;
	}
}
