package sara.nemo.br.ufes.inf.domain;
import java.time.LocalDate;
import java.time.LocalTime;


public class VooGrupoI extends Voo{
	int id;
	int idHotran;
	int numeroVoo;
	LocalTime horaConfirmadaPouso;
	LocalTime horaConfirmadaDecolagem;
	
	public VooGrupoI() {
		super();
	}
	
	public VooGrupoI(LocalDate dataPrevistaParaPouso, LocalTime horaPrevistaParaPouso,
			LocalDate dataPrevistaParaDecolagem, LocalTime horaPrevistaParaDecolagem, String situacao, String origem,
			String destino, int id, int idHotran, int numeroVoo, LocalTime horaConfirmadaPouso,
			LocalTime horaConfirmadaDecolagem) {
		
		super.setDataPrevistaParaPouso(dataPrevistaParaPouso);
		super.setHoraPrevistaParaPouso(horaPrevistaParaPouso);
		super.setDataPrevistaParaDecolagem(dataPrevistaParaDecolagem);
		super.setHoraPrevistaParaDecolagem(horaPrevistaParaDecolagem);
		super.setSituacao(situacao);
		super.setOrigem(origem);
		super.setDestino(destino);
		
		this.id = id;
		this.idHotran = idHotran;
		this.numeroVoo = numeroVoo;
		this.horaConfirmadaPouso = horaConfirmadaPouso;
		this.horaConfirmadaDecolagem = horaConfirmadaDecolagem;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdHotran() {
		return idHotran;
	}
	public void setIdHotran(int idHotran) {
		this.idHotran = idHotran;
	}
	public int getNumeroVoo() {
		return numeroVoo;
	}
	public void setNumeroVoo(int numeroVoo) {
		this.numeroVoo = numeroVoo;
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
	

	
}
