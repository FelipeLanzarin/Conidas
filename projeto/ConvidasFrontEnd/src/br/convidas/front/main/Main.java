package br.convidas.front.main;
	
import java.io.IOException;
import java.net.URL;

import br.convidas.banco.EntityManagerUtil;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.XmlPathUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;


public class Main extends Application {
	
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		try {
			LogTools.logDebug("\n\n************************* Start Convidas *************************");
			setPrimaryStage(primaryStage);
			URL arquivoFXML = getClass().getResource(XmlPathUtils.LOAD_BD);
			Parent fxmlParent;
			FXMLLoader loader = new FXMLLoader(arquivoFXML);
			fxmlParent = (Parent) loader.load();
			primaryStage.setScene(new Scene(fxmlParent));
			primaryStage.setResizable(false);
			primaryStage.setTitle("Convidas");
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent t) {
					Platform.exit();
					System.exit(0);
				}
			});
			primaryStage.show();
			ManagerInitBD dt = new ManagerInitBD();
			dt.setMain(this);// envia a propria classe para que a thread possa atualizar as informacoes
			Thread thread = new Thread(dt);
			thread.start();
		} catch (IOException e) {
			LogTools.logError(e);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void sucessConectBD(){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					URL arquivoFXML = getClass().getResource(XmlPathUtils.MAIN);
					FXMLLoader loader = new FXMLLoader(arquivoFXML);
					Parent fxmlParent;
					fxmlParent = (Parent) loader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(fxmlParent));
					stage.setResizable(false);
					stage.show();
					stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
						public void handle(WindowEvent t) {
							EntityManagerUtil.finalizefinalize();
							Platform.exit();
							System.exit(0);
						}
					});
					primaryStage.close();
				} catch (IOException e) {
					LogTools.logError(e);
				}
			}
		});
	}
	
	public void failedConectBD(){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
				dialogoInfo.setTitle("Erro!");
				dialogoInfo.setHeaderText("Erro ao Conectar com o Banco de dados");
				dialogoInfo.setContentText("Verifique a sua conexão com a internet!");
				dialogoInfo.showAndWait();
				Platform.exit();
				System.exit(0);
			}
		});
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}
