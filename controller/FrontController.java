package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.Telas;
import controller.GerenteController;

public class FrontController {
	

	public void GoCaixa(ActionEvent event) throws Exception{
		Telas.telaFuncionario();
	}
	public void GoEstoque(ActionEvent event) throws Exception{
		Telas.telaGerente();
	}
	
	/*public void Auntenticar(ActionEvent event) throws Exception{
		Usuario user = new Usuario();
		user.setEmail(email.getText());
		user.setSenha(senha.getText());
		Usuario userColetado = userBo.autenticar(user);
		if (userColetado instanceof Gerente) {
			GerenteController g = new GerenteController();
			g.staticNome = userColetado.getNome();
			Telas.telaGerente();
		}
		else {
			if (userColetado instanceof Funcionario) {
				FuncionarioController f = new FuncionarioController();
				f.staticNome = userColetado.getNome();
				Telas.telaFuncionario();
			}
			else erroAutenticacao.setVisible(true);
		}
	}*/
	
	
	

}