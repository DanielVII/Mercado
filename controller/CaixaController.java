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
import model.entity.Cliente;
import model.entity.Produto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import EstruturaDeDados.Fila.Fila;
import Fabrica.ElementoFxmlFabrica;

public class CaixaController extends ElementoFxmlFabrica{
	@FXML private Pane PaneCaixa;
	@FXML private Label precoTotal;
	@FXML private Label mensagemErro;
	@FXML private Label proximoCliente;
	@FXML private Label clienteAgora;
	
	public static Fila<Cliente> fila = new Fila<Cliente>(10); 
	private Cliente clienteNow;
	private Cliente clienteNext;
	
	private DecimalFormat df = new DecimalFormat("#.##");
	
	private List<Produto> produtosNaComanda = new ArrayList<Produto>();
	
	private static ProdutoBO produtoBO = new ProdutoBO();

	private int scroll = 0; // sempre será menor igual a zero. Usado para lógica de scroll da comanda.
	
	private int tamanhoAntesDeItensNaComanda; // Usado para limpar itens da tela.
	
	public void initialize() {
		this.tamanhoAntesDeItensNaComanda = this.PaneCaixa.getChildren().size();
		
		this.proximoCliente();
		
		
	}
	
	private void proximoCliente() {
		this.clienteNow = fila.atenderProximo();
		this.clienteNext = fila.proximo();
		
		if(this.clienteNow != null) {
			this.clienteAgora.setText(this.clienteNow.getNome());
		}else {
			this.clienteAgora.setText("");
		}
		
		if(this.clienteNext != null) {
			this.proximoCliente.setText(this.clienteNext.getNome());
		}else {
			this.proximoCliente.setText("");
		}
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
		
		for(Produto p: this.clienteNow.getCarrinho().getItens()) {
			this.produtosNaComanda.add(p);
		}
		this.ColocarInfoNaComanda();
		
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
				/*
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
				*/
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
				this.PaneCaixa.getChildren().addAll(marcaLinha, linha, linha2, bDel );
			}
		}
	}
	/*
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
		

		
		Produto[] listProd = CaixaController.produtoBO.listarPorCampoEspecifico(produto, "cod_de_barras");//Isso é necessario pois o produto fornecido em alguns contextos terão a quantidade existente na comanda, n no bd
		Produto novoProd = listProd[0];
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
	*/
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
		this.proximoCliente();
		this.produtosNaComanda.removeAll(this.produtosNaComanda);
		this.removeInfo();
	}
	

}
