package Pagination;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import Fabrica.ElementoFxmlFabrica;

public class Pagination extends ElementoFxmlFabrica{
	
	private int PaginaAtual = 1;
	
	private int quantItensPagInicial;
	private int quantItensListados;
	
	private Pane pane;
	
	public Pagination(Pane p) {
		this.pane = p;
		this.quantItensPagInicial = this.pane.getChildren().size();
	}
	/*
	public void ColocarBotoesPag(){
		int tamanhoList = this.ListaProdutos.size();
		Double totalBotoes = tamanhoList/5.0;
		int totalBotoesInt = totalBotoes.intValue();
		if (totalBotoes <= 1) return; // esse caso significa q só existe uma pagina, então a função para por aqui
		else{
			if (totalBotoes == totalBotoesInt) {
				totalBotoesInt -= 1; // como já existe um botão na pagina inicial, é preciso tirar ele
			}
		}
		Double LX = 179.0;
		Double LY = 421.0;
		
		for (Integer i = 0; i < totalBotoesInt; i++) {
			
			Button b = ButtonFabrica(
					Integer.toString(i+2),
					"pagina"+Integer.toString(i+2),
					LX,
					LY,
					7,
					14.0,
					"#ffffff"
					);
			b.setPrefHeight(17.0);
			b.setOnAction(action -> {
				try {
					this.MudarPagina(action);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			PaneEstoque.getChildren().add(b);
			
			LX += 16;
		}
	}

	public void MudarPagina(ActionEvent e, Pane p) throws Exception {
		Button botaoAnterior = (Button) p.lookup("#pagina"+this.PaginaAtual);
		botaoAnterior.setStyle("-fx-background-color:#ffffff ;");
		
		Button b = (Button) e.getSource();
		this.PaginaAtual = Integer.parseInt(b.getText());
		
		this.RemoveInfo(true);
		
		this.GerarTela(false);
		
		Button botaoFocado = (Button) p.lookup("#pagina"+this.PaginaAtual);
		botaoFocado.setStyle("-fx-background-color:#d3d3d3 ;");
	}
	
	private void RemoveInfo(boolean tudo) {
		if (tudo) this.PaneEstoque.getChildren().remove(this.quantItensPagInicial,  this.PaneEstoque.getChildren().size());
		else this.PaneEstoque.getChildren().remove(this.quantItensListados,  this.PaneEstoque.getChildren().size());
	}
	*/
	public int getPaginaAtual() {
		return PaginaAtual;
	}

	public int getQuantItensPagInicial() {
		return quantItensPagInicial;
	}

	public int getQuantItensListados() {
		return quantItensListados;
	}
}
