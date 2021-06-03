package sara.nemo.br.ufes.inf.domain;
import java.time.LocalDate;
import java.time.LocalTime;


public class VooChegadaGrupoI extends Voo{
	private int idVooChegadaGrupoI;
	private int idHotran;
	private int idProprietarioCiaAerea;
	private int numeroVooPouso;
	private LocalDate dataPrevistaParaPouso;
	private LocalTime horaPrevistaParaPouso;
	private LocalDate dataConfirmadaPouso;
	private LocalTime horaConfirmadaPouso;
	private String situacao;
	private String origem;
	private int nomeCabeceira;
	private String nomeEsteira;

	
	public VooChegadaGrupoI() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


	public int getIdVooChegadaGrupoI() {
		return idVooChegadaGrupoI;
	}




	public void setIdVooChegadaGrupoI(int idVooChegadaGrupoI) {
		this.idVooChegadaGrupoI = idVooChegadaGrupoI;
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




	public LocalDate getDataPrevistaParaPouso() {
		return dataPrevistaParaPouso;
	}




	public void setDataPrevistaParaPouso(LocalDate dataPrevistaParaPouso) {
		this.dataPrevistaParaPouso = dataPrevistaParaPouso;
	}




	public LocalTime getHoraPrevistaParaPouso() {
		return horaPrevistaParaPouso;
	}




	public void setHoraPrevistaParaPouso(LocalTime horaPrevistaParaPouso) {
		this.horaPrevistaParaPouso = horaPrevistaParaPouso;
	}




	public LocalDate getDataConfirmadaPouso() {
		return dataConfirmadaPouso;
	}




	public void setDataConfirmadaPouso(LocalDate dataConfirmadaPouso) {
		this.dataConfirmadaPouso = dataConfirmadaPouso;
	}




	public LocalTime getHoraConfirmadaPouso() {
		return horaConfirmadaPouso;
	}




	public void setHoraConfirmadaPouso(LocalTime horaConfirmadaPouso) {
		this.horaConfirmadaPouso = horaConfirmadaPouso;
	}




	public String getSituacao() {
		return situacao;
	}




	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}




	public String getOrigem() {
		return origem;
	}




	public void setOrigem(String origem) {
		this.origem = origem;
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




	public String getNomeEsteira() {
		return nomeEsteira;
	}




	public void setNomeEsteira(String nomeEsteira) {
		this.nomeEsteira = nomeEsteira;
	}




	public String toString() {
		String vooGrupoI = idVooChegadaGrupoI+" "+ idHotran+" "+idProprietarioCiaAerea+" "+numeroVooPouso+" "+
				dataPrevistaParaPouso+" "+horaPrevistaParaPouso+" "+dataConfirmadaPouso+" "+
				horaConfirmadaPouso+" "+situacao+" "+origem+" "+
				nomeCabeceira+" "+nomeEsteira;
		
		return vooGrupoI;
	}
}
