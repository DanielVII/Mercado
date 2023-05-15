package controller;

import javafx.event.ActionEvent;
import view.Telas;

public class MenuController {
	

	public void GoCaixa(ActionEvent event) throws Exception{
		Telas.telaCaixa();
	}
	public void GoEstoque(ActionEvent event) throws Exception{
		Telas.telaEstoque();
	}
	
	public void GoPratileiras(ActionEvent event) throws Exception{
		Telas.telaPratileiras();
	}
}