package sara.nemo.br.ufes.inf.domain;
import java.time.LocalDate;
import java.time.LocalTime;


public class VooPartidaGrupoI extends Voo{
	int idVooPartidaGrupoI;
	int numeroVooDecolagem;
	private LocalDate dataPrevistaParaDecolagem;
	private LocalTime horaPrevistaParaDecolagem;
	private LocalDate dataConfirmadaDecolagem;
	private LocalTime horaConfirmadaDecolagem;
	private String destino;
	private String situacaoPartida;
	private String portaoDeEmbarque;
	private int Hotran_idHotran;
	
	public VooPartidaGrupoI() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public int getIdVooPartidaGrupoI() {
		return idVooPartidaGrupoI;
	}



	public void setIdVooPartidaGrupoI(int idVooPartidaGrupoI) {
		this.idVooPartidaGrupoI = idVooPartidaGrupoI;
	}



	public int getNumeroVooDecolagem() {
		return numeroVooDecolagem;
	}



	public void setNumeroVooDecolagem(int numeroVooDecolagem) {
		this.numeroVooDecolagem = numeroVooDecolagem;
	}



	public LocalDate getDataPrevistaParaDecolagem() {
		return dataPrevistaParaDecolagem;
	}



	public void setDataPrevistaParaDecolagem(LocalDate dataPrevistaParaDecolagem) {
		this.dataPrevistaParaDecolagem = dataPrevistaParaDecolagem;
	}



	public LocalTime getHoraPrevistaParaDecolagem() {
		return horaPrevistaParaDecolagem;
	}



	public void setHoraPrevistaParaDecolagem(LocalTime horaPrevistaParaDecolagem) {
		this.horaPrevistaParaDecolagem = horaPrevistaParaDecolagem;
	}



	public LocalDate getDataConfirmadaDecolagem() {
		return dataConfirmadaDecolagem;
	}



	public void setDataConfirmadaDecolagem(LocalDate dataConfirmadaDecolagem) {
		this.dataConfirmadaDecolagem = dataConfirmadaDecolagem;
	}



	public LocalTime getHoraConfirmadaDecolagem() {
		return horaConfirmadaDecolagem;
	}



	public void setHoraConfirmadaDecolagem(LocalTime horaConfirmadaDecolagem) {
		this.horaConfirmadaDecolagem = horaConfirmadaDecolagem;
	}



	public String getDestino() {
		return destino;
	}



	public void setDestino(String destino) {
		this.destino = destino;
	}



	public String getSituacaoPartida() {
		return situacaoPartida;
	}



	public void setSituacaoPartida(String situacaoPartida) {
		this.situacaoPartida = situacaoPartida;
	}



	public String getPortaoDeEmbarque() {
		return portaoDeEmbarque;
	}



	public void setPortaoDeEmbarque(String portaoDeEmbarque) {
		this.portaoDeEmbarque = portaoDeEmbarque;
	}

	

	public int getHotran_idHotran() {
		return Hotran_idHotran;
	}



	public void setHotran_idHotran(int hotran_idHotran) {
		Hotran_idHotran = hotran_idHotran;
	}



	@Override
	public String toString() {
		return 	idVooPartidaGrupoI+" "+numeroVooDecolagem+" "+dataPrevistaParaDecolagem
				+" "+horaPrevistaParaDecolagem+" "+dataConfirmadaDecolagem+" "+
				horaConfirmadaDecolagem+" "+situacaoPartida+" "+portaoDeEmbarque+" "+Hotran_idHotran;
	}




}
