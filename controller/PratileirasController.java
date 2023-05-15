package controller;

import java.util.ArrayList;
import java.util.List;

import EstruturaDeDados.Pilha.Pilha;
import Fabrica.ElementoFxmlFabrica;
import Ordenacao.Ordenar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.Service.EstoqueBO;
import model.Service.PratileirasBO;
import model.Service.ProdutoBO;
import model.entity.Cliente;
import model.entity.Produto;
import view.Telas;

public class PratileirasController extends ElementoFxmlFabrica{
	@FXML private Pane PanePratileira;
	@FXML private Label NomeCliente;
	
	private Pilha<Produto>[] pratileiras;
	
	private int proxCliente = 0;
	private Cliente cliente;
	private boolean modoCliente = true;
	
	private Produto[] ListaProdutos;
	
	//controlador de movimentação de pagina
	private int PaginaAtual = 1;
	
	private int quantItensPagInicial;
	private int quantItensListados;
	//-------------------------------------------------------------------
	
	private ProdutoBO prodBO = new ProdutoBO();
	private EstoqueBO estoqueBO = new EstoqueBO();
	private PratileirasBO pratBO = new PratileirasBO();
	
	public void initialize() {
		this.quantItensPagInicial = PanePratileira.getChildren().size();
		this.GerarTela(true);
		
		 this.ProximoCliente();
		 
		}
	
	public void ProximoCliente() {
		String[] clientes = {"Daniel", "Lucas", "Felipe", "João"};
		this.cliente = new Cliente(clientes[this.proxCliente]);
		this.proxCliente++;
		if(this.proxCliente == 3) {
			this.proxCliente = 0;
		}
		NomeCliente.setText(this.cliente.getNome());
	}
	
	public void ModoFuncionario() {
		this.modoCliente = false;
		this.RemoveInfo(true);
		this.GerarTela(false);
	}
	
	public void ModoCliente() {
		this.modoCliente = true;
		this.RemoveInfo(true);
		this.GerarTela(false);
	}
	
	public void EntrarNaFila() {
		CaixaController.fila.entrarNaFila(this.cliente);
		this.ProximoCliente();
		this.RemoveInfo(true);
		this.GerarTela(false);
		
	}
	//criação de tela
	private void RemoveInfo(boolean tudo) {
		if (tudo) this.PanePratileira.getChildren().remove(this.quantItensPagInicial,  this.PanePratileira.getChildren().size());
		else this.PanePratileira.getChildren().remove(this.quantItensListados,  this.PanePratileira.getChildren().size());
	}
	
	//criação de tela
	//Especifico pra essa pag
 	private void ColocarInfoNaTela() {
		
		int tamanhoList = this.ListaProdutos.length;
		
		Double LayX;
		Double LayY = 205.0;
		
		int posicaoFinalListaPag = this.PaginaAtual * 5;
		
		int start;
		int end;
		if (tamanhoList - posicaoFinalListaPag < 0) {
			//Esse caso vai ocorrer quando for a ultima pagina e o total de itens da mesma for - de 5
			int x = posicaoFinalListaPag - tamanhoList;
			end = tamanhoList;
			start = end - (5 - x);
		}else {
			//Vai acontecer quando tiver 5 itens pra cobrir a pagina inteira
			end = posicaoFinalListaPag;
			start = end - 5;
		}
		
		
		//Produto prod = new Produto();
		int TamanhoFont = 12;
		Double LarguraLabel = 80.0;
		boolean Centralizar = true;
		
		Double distanciaEntreElementos = 90.0;
		
		for (int i =start ; i < end; i++) {
		
			final Produto prod = this.ListaProdutos[i];
			
			LayX = 170.0;
			String[] dados = {
					prod.getNome(),
					prod.getCodBarras(),
					prod.getMarca(), 
					String.valueOf(prod.getQuantidade()),
					String.valueOf(prod.getPreco())
					};
			
			for(String dado: dados) {
				Label coluna = LabelFabrica(
						dado, 
						LayX, 
						LayY, 
						TamanhoFont, 
						Centralizar, 
						LarguraLabel
						);
				
				LayX += distanciaEntreElementos;
				
				this.PanePratileira.getChildren().add(coluna);
			}
			
			
			
			Double LarguraButton = 30.0;
			if(this.modoCliente) {
				
				LayX += 90;
				
				Button addCarrinho = ButtonFabrica(
						"+", 
						prod.getCodBarras(), 
						LayX, 
						LayY, 
						TamanhoFont, 
						LarguraButton
						);
				addCarrinho.setOnAction(action -> {
					if(prod.getQuantidade() > 0) {
						this.cliente.getCarrinho().addItem(prod);
						Produto p = new Produto();
						p.setId(prod.getId());
						p.setQuantidade(prod.getQuantidade() - 1);
						
						this.pratBO.alterar(p);
						
						this.RemoveInfo(true);
						this.GerarTela(true);
					}
				});
				
				LayX += 40;
				
				Button deleCarrinho = ButtonFabrica(
						"-", 
						prod.getCodBarras(), 
						LayX, 
						LayY, 
						TamanhoFont, 
						LarguraButton
						);
				deleCarrinho.setOnAction(action -> {
					if(prod.getQuantidade() < 10 && this.cliente.getCarrinho().removeItem(prod)) {
	
						Produto p = new Produto();
						p.setId(prod.getId());
						p.setQuantidade(prod.getQuantidade() + 1);
						
						this.pratBO.alterar(p);
						
						this.RemoveInfo(true);
						this.GerarTela(true);
				}});
				this.PanePratileira.getChildren().addAll(deleCarrinho, addCarrinho);
			}else {
				LayX += 90;
				LarguraButton = 70.0;
				Button prencher = ButtonFabrica(
						"Prencher", 
						prod.getCodBarras(), 
						LayX, 
						LayY, 
						TamanhoFont, 
						LarguraButton
						);
				prencher.setOnAction(action -> {
					double quantAntes = prod.getQuantidade();
					double quantEstoque = this.estoqueBO.listarPorCampoEspecifico(prod, "id_produto")[0].getQuantidade();
					
					Produto p = new Produto();
					if(10 - quantAntes <= quantEstoque) {
						prod.setQuantidade(10.0);
						p.setId(prod.getId());
						p.setQuantidade(quantEstoque - (10 - quantAntes));
						
						this.estoqueBO.alterar(p);
					}else {
						prod.setQuantidade(quantAntes - quantEstoque);
						p.setId(prod.getId());
						p.setQuantidade(0.0);
						
						this.estoqueBO.alterar(p);
					}
					
					this.pratBO.alterar(prod);
					
					this.RemoveInfo(true);
					this.GerarTela(true);
					
					
				});
				
				this.PanePratileira.getChildren().add(prencher);
			}
			
			
			LayY += 45;
			
		
		}
		
		
	}
	
 	//criação de tela
	private void ColocarBotoesPag(){
		int tamanhoList = this.ListaProdutos.length;
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
			PanePratileira.getChildren().add(b);
			
			LX += 16;
		}
	}
	
	// criação de tela
	private void GerarTela(boolean ColetarInfoNova) {
		if (ColetarInfoNova) {
			Produto[] quantProdutoL = this.pratBO.listarTodos();
			this.ListaProdutos = this.prodBO.listarTodos();
			
			for(int x = 0 ; x < this.ListaProdutos.length; x++) {
				for(Produto p1: quantProdutoL) {
					if(this.ListaProdutos[x].getId() == p1.getId()) {
						this.ListaProdutos[x].setQuantidade(p1.getQuantidade());
						break;
					}
				}
			}
		}
		this.ColocarInfoNaTela();
		this.ColocarBotoesPag();
		this.quantItensListados = PanePratileira.getChildren().size();;
	}
	
	//criação de tela
	//pane
	//
	public void MudarPagina(ActionEvent e) throws Exception {
		Button botaoAnterior = (Button) this.PanePratileira.lookup("#pagina"+this.PaginaAtual);
		botaoAnterior.setStyle("-fx-background-color:#ffffff ;");
		
		Button b = (Button) e.getSource();
		this.PaginaAtual = Integer.parseInt(b.getText());
		
		this.RemoveInfo(true);
		
		this.GerarTela(false);
		
		Button botaoFocado = (Button) this.PanePratileira.lookup("#pagina"+this.PaginaAtual);
		botaoFocado.setStyle("-fx-background-color:#d3d3d3 ;");
	}
	
	public void LogOut(ActionEvent event) throws Exception{
		Telas.telaMenu();
	}

	public List<Double> BaseTelaNovoEEditarProduto(String titulo) {
		this.BaseParaNovaPagina(titulo);
	
		Double LX = 340.0;
		Double LY = 192.0;
		Double DistanciaLabelEField = 75.0;
		Double DistanciaLabelELabel = 35.0;
		Double LarguraField = 150.0;
		
		List<String> LabelNome = new ArrayList<String>();
		LabelNome.add("Nome");
		LabelNome.add("Marca");
		LabelNome.add("Cod.");
		LabelNome.add("Preço");
		LabelNome.add("Quantidade");
		
		List<String> IdField = new ArrayList<String>();
		IdField.add("FieldNomeProduto");
		IdField.add("FieldMarcaProduto");
		IdField.add("FieldCodProduto");
		IdField.add("FieldPrecoProduto");
		IdField.add("FieldQuantidadeProduto");
		
		for (int x=0;x<IdField.size();x++) {
			Label lb = LabelFabrica(
					LabelNome.get(x),
					LX,LY+5,
					12,
					false,
					70.0
					);
			lb.setAlignment(Pos.CENTER_RIGHT);
			
			LX += DistanciaLabelEField;
			
			TextField tf = TextFieldFabrica(
					IdField.get(x),
					LarguraField,
					13.0,
					LX,LY
					);
			this.PanePratileira.getChildren().addAll(lb, tf);
			
			LX -= DistanciaLabelEField;
			LY += DistanciaLabelELabel;
		}
		
		LX += 40;
		LY += 13;
		
		Button Voltar = ButtonFabrica(
				"Voltar",
				"Voltar",
				LX,LY,
				13,
				90.0
				);
		Voltar.setOnAction(event->{
			this.RemoveInfo(false);
		});
		
		List<Double> LXLY = new ArrayList<Double>();
		LXLY.add(LX);
		LXLY.add(LY);
		
		this.PanePratileira.getChildren().add(Voltar);
		
		return LXLY;
		
	}
	
	/*public void Pesquisar() {
		if (this.Pesquisa.getText() == "") {
			this.RemoveInfo(true);
			this.GerarTela(true);
			return;
		}
		String NomeColuna;
		Produto prod = new Produto();
		switch(this.EscolhaPesquisa.getValue()) {
			case "Nome":
				prod.setNome(this.Pesquisa.getText());
				NomeColuna = "nome";
				break;
			case "Cod. Barras":
				prod.setCodBarras(this.Pesquisa.getText());
				NomeColuna = "cod_de_barras";
				break;
			case "Marca":
				prod.setMarca(this.Pesquisa.getText());
				NomeColuna = "marca";
				break;
			default:
				return;
		}
		this.ListaProdutos = this.prodBO.listarPorCampoEspecificoIncompleto(prod, NomeColuna);
		this.RemoveInfo(true);
		this.GerarTela(false);
	}
	*/
  	
	private void BaseParaNovaPagina(String titulo) {
		ImageView IV = ImageFabrica(
						525.0,
						325.0,
						220.0,
						150.0,
						"view/ve/RectanglePrincipal.png"
						);
				
		Label t = LabelFabrica(
					titulo, 
					150.0, 
					155.0, 
					18, 
					true,
					650.0
					);
		
		
		this.PanePratileira.getChildren().addAll(IV, t);
	}


}
