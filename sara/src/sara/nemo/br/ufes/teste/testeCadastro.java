package sara.nemo.br.ufes.teste;

import java.awt.EventQueue;

import sara.nemo.br.ufes.view.CadAeronave;

public class testeCadastro {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadAeronave frame = new CadAeronave();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
