package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import view.Telas;
import model.Service.EstoqueBO;
import model.Service.PratileirasBO;
import model.Service.ProdutoBO;
import model.entity.Produto;
import Fabrica.ElementoFxmlFabrica;
import Ordenacao.Ordenar;

public class EstoqueController extends ElementoFxmlFabrica{
	@FXML private Pane PaneEstoque;
	@FXML private Button pagina1;
	@FXML private Button BotaoQuantidade;
	@FXML private Button BotaoPreco;
	@FXML private ImageView testan;

	private Produto[] ListaProdutos;
	
	//controlador de movimentação de pagina
	private int PaginaAtual = 1;
	
	private int quantItensPagInicial;
	private int quantItensListados;
	//-------------------------------------------------------------------
	
	private ProdutoBO prodBO = new ProdutoBO();
	private EstoqueBO estoqueBO = new EstoqueBO();
	private PratileirasBO pratBO = new PratileirasBO();
	
	//Ordenação
	private boolean ordenarQuantidadeMenorParaMaior = true;
	//-------------------------------------------------------------------
	
	public void initialize() {
		this.quantItensPagInicial = PaneEstoque.getChildren().size();
		this.GerarTela(true);
		
		this.pagina1.setStyle("-fx-background-color:#d3d3d3 ;");
		
		 // Colocar evento de click em img
		testan.setOnMouseClicked((MouseEvent e)->{
			try {
				Telas.telaMenu();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		//-------------------------------------------------------------------
		}
		
	public void OrdenarPreco() {
		
	}
	
	public void OrdenarQuantidade() {
		Ordenar ord = new Ordenar();
		if(this.ordenarQuantidadeMenorParaMaior) {
			
			ord.OrdenarPorQuantidade(this.ListaProdutos);
			
			this.RemoveInfo(true);
			this.ColocarInfoNaTela();
			
			this.BotaoQuantidade.setStyle("-fx-border-color:#FFD966; -fx-background-color: #5BD0E3;");
			this.ordenarQuantidadeMenorParaMaior = false;
		}else {
			ord.ReversePorQuantidade(this.ListaProdutos);
			
			this.RemoveInfo(true);
			this.ColocarInfoNaTela();
			
			this.BotaoQuantidade.setStyle("-fx-border-color:#655DBB; -fx-background-color: #5BD0E3;");
			this.ordenarQuantidadeMenorParaMaior = true;
		}
	}

	//criação de tela
	private void RemoveInfo(boolean tudo) {
		if (tudo) this.PaneEstoque.getChildren().remove(this.quantItensPagInicial,  this.PaneEstoque.getChildren().size());
		else this.PaneEstoque.getChildren().remove(this.quantItensListados,  this.PaneEstoque.getChildren().size());
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
		
		
		Produto prod = new Produto();
		int TamanhoFont = 12;
		Double LarguraLabel = 80.0;
		boolean Centralizar = true;
		
		Double distanciaEntreElementos = 90.0;
		
		for (int i =start ; i < end; i++) {
		
			prod = this.ListaProdutos[i];
			
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
				
				this.PaneEstoque.getChildren().add(coluna);
			}
			
			LayY += 45;
			/*
			LayX += 90;
			
			Button dele = ButtonFabrica(
					"Del", 
					prod.getCodBarras(), 
					LayX, 
					LayY, 
					TamanhoFont, 
					LarguraButton, 
					"#cc1515"
					);
			dele.setOnAction(action -> DeletarProduto(action));
			
			LayX += 40;
			
			Button edit = ButtonFabrica(
					"Edit", 
					prod.getCodBarras(), 
					LayX, 
					LayY, 
					TamanhoFont, 
					LarguraButton
					);
			edit.setOnAction(action -> EditarProduto(action));
			*/
			
			
		
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
			PaneEstoque.getChildren().add(b);
			
			LX += 16;
		}
	}
	
	// criação de tela
	private void GerarTela(boolean ColetarInfoNova) {
		if (ColetarInfoNova) {
			Produto[] quantProdutoL = this.estoqueBO.listarTodos();
			this.ListaProdutos = this.prodBO.listarTodos();
			
			for(int x = 0 ; x < this.ListaProdutos.length; x++) {
				for(Produto p1: quantProdutoL) {
					if(this.ListaProdutos[x].getId() == p1.getId()) {
						this.ListaProdutos[x].setQuantidade(p1.getQuantidade());
					}
				}
			}
		}
		this.ColocarInfoNaTela();
		this.ColocarBotoesPag();
		this.quantItensListados = PaneEstoque.getChildren().size();;
	}
	
	//criação de tela
	//pane
	//
	public void MudarPagina(ActionEvent e) throws Exception {
		Button botaoAnterior = (Button) this.PaneEstoque.lookup("#pagina"+this.PaginaAtual);
		botaoAnterior.setStyle("-fx-background-color:#ffffff ;");
		
		Button b = (Button) e.getSource();
		this.PaginaAtual = Integer.parseInt(b.getText());
		
		this.RemoveInfo(true);
		
		this.GerarTela(false);
		
		Button botaoFocado = (Button) this.PaneEstoque.lookup("#pagina"+this.PaginaAtual);
		botaoFocado.setStyle("-fx-background-color:#d3d3d3 ;");
	}
	
	public void LogOut(ActionEvent event) throws Exception{
		Telas.telaMenu();
	}

	public void RemersaNova() {
		this.BaseParaNovaPagina("Nova Remessa");;
		
		List<String> idTextF = new ArrayList<String>();
		idTextF.add("cod");
		idTextF.add("quant");
		
		List<String> textLabel = new ArrayList<String>();
		textLabel.add("Cod de barras");
		textLabel.add("Quantidade");
		
		Double LX = 360.0;
		Double LY = 194.0;
		Double distanciaElementos = 100.0;
		
		for (int n = 0; n<2;n++) {
			Label l = LabelFabrica(
					textLabel.get(n), 
					LX, 
					LY,
					12, 
					false, 
					90.0
					);
			
			LX +=distanciaElementos;
			
			TextField tf = TextFieldFabrica(
					idTextF.get(n),
					150.0,
					17.0,
					LX,
					LY
					);
			
			this.PaneEstoque.getChildren().addAll(l, tf);
			LX -= distanciaElementos;
			LY += 34;
		}
		
		Double tamanhoBotao = 80.0;
		
		LX += 50;
		Button bV = ButtonFabrica(
				"Voltar",
				"Voltar",
				LX,
				291.0,
				12,
				tamanhoBotao
				);
		bV.setOnAction(event -> {
			this.RemoveInfo(false);
		});
		
		LX += distanciaElementos;
		
		Button bMudar = ButtonFabrica(
				"Atualizar",
				"Atualizar",
				LX,
				291.0,
				12,
				tamanhoBotao,
				"#06FF6A"
				);
		bMudar.setOnAction(event -> {
			Produto prod = new Produto();
			TextField tFCode = (TextField) this.PaneEstoque.lookup("#cod"); 
			TextField tFQuanti = (TextField) this.PaneEstoque.lookup("#quant");
			Double quantidade = Double.parseDouble(tFQuanti.getText());
			
			prod.setCodBarras(tFCode.getText());
			
			Produto[] lProd = this.prodBO.listarPorCampoEspecifico(prod, "cod_de_barras");//conferindo pra saber se existe no bd
			
			if (lProd.length>0 ) {
				prod = lProd[0];
				Produto quantProd = estoqueBO.listarPorCampoEspecifico(prod, "id_produto")[0];
				prod.setQuantidade(quantProd.getQuantidade()+quantidade);
				
				this.prodBO.alterar(prod);
				this.estoqueBO.alterar(prod);
				this.RemoveInfo(true);
				this.GerarTela(true);
			}else {
				Label msgErro = LabelFabrica(
						"O cod de barras não existe no armazém ou a quantidade é decimal para produto unitario",
						300.0,
						260.0,
						12,
						false);
				msgErro.setTextFill(Color.RED);
				this.PaneEstoque.getChildren().add(msgErro);
			}
		});
		this.PaneEstoque.getChildren().addAll( bV, bMudar);
	}
	
	public void ProdutoNovo() {
		
		List<Double> LXLY = this.BaseTelaNovoEEditarProduto("Produto Novo");
		Double LX = LXLY.get(0);
		Double LY = LXLY.get(1);
		
		LX += 100;
		
		Button Adicionar = ButtonFabrica(
				"Adicionar",
				"AdicionarProduto",
				LX,LY,
				13,
				90.0,
				"#06FF6A"
				);
		
		Adicionar.setOnAction(event->{
			Produto prod = new Produto();
			//Tratando Nome do produto
			TextField nomeTF = (TextField) this.PaneEstoque.lookup("#FieldNomeProduto");
			String nomeProd = nomeTF.getText();
			if (nomeProd.isBlank()) {
				this.ErroEmNovoEEditarProd(LY);
				return;
			}
			
			prod.setNome(nomeProd);
			
			//Tratando Marca do Produto
			TextField marcaTF = (TextField) this.PaneEstoque.lookup("#FieldMarcaProduto");
			String marcaProd = marcaTF.getText();
			if (marcaProd.isBlank()) {
				this.ErroEmNovoEEditarProd(LY);
				return;
			}
			
			prod.setMarca(marcaProd);
			
			//Tratando cod de barras
			TextField codTF = (TextField) this.PaneEstoque.lookup("#FieldCodProduto");
			String codProd = codTF.getText();
			if (codProd.isBlank()) {
				this.ErroEmNovoEEditarProd(LY);
				return;
			}
			
			prod.setCodBarras(codProd);
			
			//Tratando Preço
			TextField precoTF = (TextField) this.PaneEstoque.lookup("#FieldPrecoProduto");
			String stringPrecoProd = precoTF.getText();
			if (stringPrecoProd.isBlank()) {
				this.ErroEmNovoEEditarProd(LY);
				return;
			}else {
				stringPrecoProd = stringPrecoProd.replaceAll(",", ".");
				try {
					Double doublePrecoProd = Double.parseDouble(stringPrecoProd);
					prod.setPreco(doublePrecoProd);
				}catch (Exception e){
					this.ErroEmNovoEEditarProd(LY);
					return;
				}
			}
			
			//Tratando quantidade
			TextField quantidadeTF = (TextField) this.PaneEstoque.lookup("#FieldQuantidadeProduto");
			String stringQuantidadeProd = quantidadeTF.getText();
			if (stringQuantidadeProd.isBlank()) {
				this.ErroEmNovoEEditarProd(LY);
				return;
			}else {
				try {
					Double doubleQuantidadeProd = Double.parseDouble(stringQuantidadeProd);
					prod.setQuantidade(doubleQuantidadeProd);
				}catch (Exception e){
					this.ErroEmNovoEEditarProd(LY);
					return;
				}
			}
			if (this.prodBO.inserir(prod)) {
				prod.setId(this.prodBO.listarPorCampoEspecifico(prod, "cod_de_barras")[0].getId());//feião kkkk
				this.estoqueBO.inserir(prod);
				prod.setQuantidade(0.0);
				this.pratBO.inserir(prod);
				this.RemoveInfo(true);
				this.GerarTela(true);
			}else {
				Double LYErro = LY;
				Double LXErro = 300.0;
				
				LYErro -= 30;
				
				Label msgError = LabelFabrica(
						"Produto já existe no armazém ou a quantidade é decimal para produto unitario",
						LXErro,
						LYErro,
						12,
						false
						);
				msgError.setTextFill(Color.RED);
				this.PaneEstoque.getChildren().add(msgError);
			}
			
		}
		);
		this.PaneEstoque.getChildren().add(Adicionar);
	}

	public void ErroEmNovoEEditarProd( Double LY) {
		LY -= 20;
		Double LX = 390.0;
		Label msgError = LabelFabrica(
				"Algum item está errado",
				LX,
				LY,
				12,
				false
				);
		msgError.setTextFill(Color.RED);
		this.PaneEstoque.getChildren().add(msgError);
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
			this.PaneEstoque.getChildren().addAll(lb, tf);
			
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
		
		this.PaneEstoque.getChildren().add(Voltar);
		
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
		
		
		this.PaneEstoque.getChildren().addAll(IV, t);
	}
}
