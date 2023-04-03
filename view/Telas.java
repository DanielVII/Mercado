package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Telas extends Application {
	private static Stage primaryStage;
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void setPrimaryStage(Stage primaryStage) {
		Telas.primaryStage = primaryStage;
		
	
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		setPrimaryStage(primaryStage);
		primaryStage.setTitle("mercado");
		primaryStage.show();
		primaryStage.centerOnScreen();
		telaMenu();
	}

	private static void prepararCena(String caminho) throws Exception{
		Parent root = FXMLLoader.load(Telas.class.getResource(caminho));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}
	
	public static void telaMenu() throws Exception{
		prepararCena("ve/TelaMenu.fxml");
	}
	
	public static void telaEstoque() throws Exception{
		prepararCena("ve/TelaEstoque.fxml");
	}
	
	public static void telaCaixa() throws Exception{
		prepararCena("ve/TelaCaixa.fxml");
	}
	public static void main(String[] args) {
		launch();
	}
	
}
