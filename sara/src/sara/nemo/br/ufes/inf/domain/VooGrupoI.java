package sara.nemo.br.ufes.inf.domain;
import java.time.LocalDate;
import java.time.LocalTime;


public class VooGrupoI extends Voo{
	Hotran dadosDoVoo;
	LocalTime horaConfirmadaPouso;
	LocalTime horaConfirmadaDecolagem;
	ProprietarioCiaAerea ciaAerea;
	
	public Hotran getDadosDoVoo() {
		return dadosDoVoo;
	}
	public void setDadosDoVoo(Hotran dadosDoVoo) {
		this.dadosDoVoo = dadosDoVoo;
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
	public ProprietarioCiaAerea getCiaAerea() {
		return ciaAerea;
	}
	public void setCiaAerea(ProprietarioCiaAerea ciaAerea) {
		this.ciaAerea = ciaAerea;
	}
	
	public VooGrupoI(LocalDate dataPrevistaParaPouso, LocalTime horaPrevistaParaPouso,
			LocalDate dataPrevistaParaDecolagem, LocalTime horaPrevistaParaDecolagem, String situacao, String origem,
			String destino, Hotran dadosDoVoo, LocalTime horaConfirmadaPouso, LocalTime horaConfirmadaDecolagem,
			ProprietarioCiaAerea ciaAerea) {
		super(dataPrevistaParaPouso, horaPrevistaParaPouso, dataPrevistaParaDecolagem, horaPrevistaParaDecolagem,
				situacao, origem, destino);
		this.dadosDoVoo = dadosDoVoo;
		this.horaConfirmadaPouso = horaConfirmadaPouso;
		this.horaConfirmadaDecolagem = horaConfirmadaDecolagem;
		this.ciaAerea = ciaAerea;
	}
	
}
