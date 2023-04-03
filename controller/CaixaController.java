package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import view.Telas;
import model.Service.ProdutoBO;
import model.entity.Produto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import Fabrica.ElementoFxmlFabrica;

public class CaixaController extends ElementoFxmlFabrica{
	@FXML private Pane PaneCaixa;
	@FXML private TextField buscar;
	@FXML private TextField quantidade;
	@FXML private Label NomeProduto;
	@FXML private Label preco;
	@FXML private Label precoTotal;
	@FXML private Label precoTotalProduto;
	@FXML private Label mensagemErro;
	
	
	private DecimalFormat df = new DecimalFormat("#.##");
	
	private List<Produto> produtosNaComanda = new ArrayList<Produto>();
	
	private static ProdutoBO produtoBO = new ProdutoBO();

	private int scroll = 0; // sempre será menor igual a zero. Usado para lógica de scroll da comanda.
	
	private int tamanhoAntesDeItensNaComanda; // Usado para limpar itens da tela.
	
	public void initialize() {
		this.tamanhoAntesDeItensNaComanda = this.PaneCaixa.getChildren().size();
	}
	
	public void LogOut(ActionEvent event) throws Exception{
		Telas.telaMenu();		
	}


	public void ScrollCima() {
		if(this.produtosNaComanda.size()>5) {
			if(this.produtosNaComanda.size() + this.scroll > 5) { //scroll sempre será menor igual a zero. Quando ele diminuir do size e der 5 quer dizer q ta no limite
				this.scroll -= 1;
				this.ColocarInfoNaComanda();
			}
		}
	}
	
	public void ScrollBaixo() {
		if(this.scroll<0) {//scroll sempre será menor igual a zero.
			this.scroll += 1;
			this.ColocarInfoNaComanda();
		}
	}
	
	private void removeInfo() {
		this.PaneCaixa.getChildren().remove(
				this.tamanhoAntesDeItensNaComanda, 
				this.PaneCaixa.getChildren().size()
				);
	}
	
	public void adicionarProduto(ActionEvent event) throws Exception{
		
		this.preco.setText("0.00");
		this.NomeProduto.setText("nome Produto");
		this.precoTotalProduto.setText("0.00");
		//								//
		//removendo msg de erro da tela//
		//							  //
		try {
			this.PaneCaixa.getChildren().remove(this.PaneCaixa.lookup("#erro1"));
		}catch (Exception e) {
			//aloha
		}
		
		try {
			this.PaneCaixa.getChildren().remove(this.PaneCaixa.lookup("#erro2"));
		}catch (Exception e) {
			//aloha
		}
		try {
			this.PaneCaixa.getChildren().remove(this.PaneCaixa.lookup("#erro3"));
		}catch (Exception e) {
			//aloha
		}
		
		Produto produto = new Produto();
		produto.setCodBarras(buscar.getText());
		
		//vendo se cod de barras é valido
		try {
			List<Produto> produtoColetado= produtoBO.listarPorCampoEspecifico(produto,"cod_de_barras");		
			produto = produtoColetado.get(0);
		}catch (Exception e) {
			Label msgErro = LabelFabrica(
					"Cod. de barras não existe no armazém",
					100.0,
					300.0,
					12,
					false
					);
			msgErro.setTextFill(Color.RED);
			msgErro.setId("erro1");
			this.PaneCaixa.getChildren().add(msgErro);
			return;
		}
		//						//
		//colocando info na tela//
		//						//
		this.preco.setText(String.valueOf(produto.getPreco()));
		this.NomeProduto.setText(produto.getNome());
		
		String QuantidadeString = this.quantidade.getText();
		
		
		if(this.ValidarQuantidade(QuantidadeString, produto)) {
			Double quantEscolhido = Double.parseDouble(QuantidadeString);
			
			produto.setQuantidade(quantEscolhido);
			
			boolean prodComanda = false;
			
			for (int x=0; x<this.produtosNaComanda.size();x++) {
				Produto prodNaLista = this.produtosNaComanda.get(x);
				if (prodNaLista.getCodBarras().equals(produto.getCodBarras())) {
					Double quantAnterior = this.produtosNaComanda.get(x).getQuantidade();
					Double quantNova = quantAnterior + produto.getQuantidade();
					
					if (this.ValidarQuantidade(String.valueOf(quantNova), produto)) {
						produto.setQuantidade(quantNova);	
						this.produtosNaComanda.set(x, produto);
						prodComanda = true;
					}else {
						return;
					}
				}
			}
			if (!prodComanda) {
				this.produtosNaComanda.add(produto);
			}
			Double valorTotalProd = produto.getPreco() * produto.getQuantidade();
			this.precoTotalProduto.setText(this.df.format(valorTotalProd));
			
			this.ColocarInfoNaComanda();
		}
	}
	
	private void ColocarInfoNaComanda() {
		this.removeInfo();//limpando para colocar info
		
		Double LX = 500.0;
		Double LY = 185.0;
		Double distancia = 20.0;

		if (this.produtosNaComanda.size() > 0) {
			this.TotalComanda();
			int end, start;
			
			if (this.produtosNaComanda.size()>5) {
				end = this.produtosNaComanda.size() + this.scroll;
				start = end - 5;
			}else {
				end = this.produtosNaComanda.size();
				start = 0;
			}
			for(int x=start;x<end;x++) {
				Produto produto = (Produto) this.produtosNaComanda.get(x); 
				Label marcaLinha = LabelFabrica(
						x + "|" ,
						LX, LY,
						12,
						false
						);
				
				Label linha = LabelFabrica(
						produto.getCodBarras() + " - " + produto.getNome(),
						LX, LY,
						12,
						true,
						240.0
						);
				LX += 220;
				Button bDel = ButtonFabrica(
						"Del",
						String.valueOf(x),
						LX, LY,
						11,
						40.0,
						"#cc1515"
						);
				final int posicaoLinha = x;
				bDel.setOnAction(event->{
					this.produtosNaComanda.remove(posicaoLinha);
					this.ColocarInfoNaComanda();
				});
				LX += 45;
				
				Button bEdit = ButtonFabrica(
						"Edit",
						String.valueOf(x),
						LX, LY,
						11,
						40.0
						);
				Double LYBEdit = LY;
				bEdit.setOnAction(event ->{
					TextField TF = TextFieldFabrica(
							"TF" + posicaoLinha,
							40.0, 12.0,
							455.0, LYBEdit
							);
					TF.setOnAction(EventHandler -> {
						if (this.ValidarQuantidade(TF.getText(), this.produtosNaComanda.get(posicaoLinha))){
							Double quant = Double.parseDouble(TF.getText());
							this.produtosNaComanda.get(posicaoLinha).setQuantidade(quant);
							this.ColocarInfoNaComanda();
						}
					});
					
					this.PaneCaixa.getChildren().add(TF);				});
				LX -= 220.0 + 45;
				LY += distancia;
				
				Label linha2 = LabelFabrica(
						produto.getQuantidade() + " X " + produto.getPreco() + " = " + this.df.format(produto.getQuantidade() * produto.getPreco()),
						LX, LY,
						12,
						true,
						240.0
						);
				
				LY += distancia;
				this.PaneCaixa.getChildren().addAll(marcaLinha, linha, linha2, bDel, bEdit);
			}
		}
	}
	
	private boolean ValidarQuantidade(String quantidade, Produto produto) {
		Double quantEscolhido;
		try {
			 quantEscolhido = Double.valueOf(quantidade);
		}catch (Exception e){
			Label msgErro = LabelFabrica(
					"Quantidade tem que ser numerico",
					100.0,
					360.0,
					12,
					false
					);
			msgErro.setTextFill(Color.RED);
			msgErro.setId("erro4");
			this.PaneCaixa.getChildren().add(msgErro);
			return false;
		}
		if (quantEscolhido <= 0 || this.quantidade.getText() == null) {
			Label msgErro = LabelFabrica(
					"Quantidade tem que ser maior que 0",
					100.0,
					320.0,
					12,
					false
					);
			msgErro.setTextFill(Color.RED);
			msgErro.setId("erro2");
			this.PaneCaixa.getChildren().add(msgErro);
			return false;}
		

		
		List<Produto> listProd = CaixaController.produtoBO.listarPorCampoEspecifico(produto, "cod_de_barras");//Isso é necessario pois o produto fornecido em alguns contextos terão a quantidade existente na comanda, n no bd
		Produto novoProd = listProd.get(0);
		Double quantEmEstoque = novoProd.getQuantidade();

		if(quantEmEstoque < quantEscolhido) {
			Label msgErro = LabelFabrica(
					"Falta estoque",
					100.0,
					380.0,
					12,
					false
					);
			msgErro.setTextFill(Color.RED);
			msgErro.setId("erro5");
			this.PaneCaixa.getChildren().add(msgErro);
			return false;
		}
		
		return true;
	}
	
	private void TotalComanda() {
		Double total = 0.0;
		for (int x=0;x<this.produtosNaComanda.size(); x++) {
			Produto prod = (Produto) this.produtosNaComanda.get(x);
			Double semiTotal = prod.getPreco() * prod.getQuantidade();
			
			total += semiTotal;
		}
		this.precoTotal.setText(this.df.format(total));;
	}
	
	public void FinalizarComanda() {
		for(int x = 0;x<this.produtosNaComanda.size();x++) {
			Produto prod = (Produto) this.produtosNaComanda.get(x);
			List<Produto> produtoColetado = produtoBO.listarPorCampoEspecifico(prod, "cod_de_barras");
			produtoColetado.get(0).setQuantidade((produtoColetado.get(0).getQuantidade() - prod.getQuantidade()));
			produtoBO.alterar(produtoColetado.get(0));
		}
		this.produtosNaComanda.removeAll(this.produtosNaComanda);
		this.removeInfo();
	}
	

}
