import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class FourInRowController {

	@FXML
	private GridPane buttonGrid;

	@FXML
	private GridPane gameGrid;

	private Button[] buttons;
	private Button btnClick;
	private final int AMOUNTBUTTONS = 7 ,COLUMNSIZE = 7 , ROWSIZE = 6;
	private int [][] gameLogic;
	private int player = 1;
	private Circle [][] circles;
	GameLogic game;

	public void initialize() {
		gameLogic = new int [COLUMNSIZE][ROWSIZE];
		initialFill();
		game = new GameLogic ();
		buttons = new Button [AMOUNTBUTTONS];
		for(int i = 0; i < AMOUNTBUTTONS ; i++) {
			buttons[i] = new Button((i+1) + "");
			buttons[i].setPrefSize(gameGrid.getPrefWidth()/AMOUNTBUTTONS,gameGrid.getPrefHeight()/AMOUNTBUTTONS);
			buttonGrid.add(buttons[i], i % AMOUNTBUTTONS, i / AMOUNTBUTTONS);
			buttons[i].setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					handleButton(e);
				}
			});
		}
	}
	private void handleButton(ActionEvent e) {
		btnClick = (Button)e.getSource();
		String numClick = btnClick.getText();
		int buttonNumber = Integer.parseInt(numClick);
		if(game.isColumnFull(gameLogic, buttonNumber -1))
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Column Full");
			alert.setHeaderText(null);
			alert.setContentText("There is no more place in this column. Please select another column.");

			alert.showAndWait();
		}
		else {
			gameLogic = game.gameBoardFill(gameLogic, buttonNumber -1, ROWSIZE -1 , player);
			colorCell(buttonNumber -1,game.getRow(), player);
		}
		if(player == 1) {
			if((game.checkIfWin(gameLogic, player))) {
				playerWin(player);
			}
			player = 2;
		}
		else {
			if((game.checkIfWin(gameLogic, player)))
				playerWin(player);
			player = 1;
		}
	}

	@FXML
	void clearPressed(ActionEvent event) {
		clearMatrix();
	}
	private void initialFill() {
		gameGrid.setAlignment(Pos.CENTER);
		circles = new Circle [COLUMNSIZE][ROWSIZE];
		for(int i = 0 ; i < COLUMNSIZE ; i++) {
			for(int j = 0 ; j < ROWSIZE ; j++) {
				circles[i][j] = new Circle(15);
				circles[i][j].setFill(Color.TRANSPARENT);
				gameGrid.setHalignment(circles[i][j], javafx.geometry.HPos.CENTER);
				gameGrid.setValignment(circles[i][j], javafx.geometry.VPos.CENTER);
				gameGrid.add(circles[i][j], i, j);
				gameLogic[i][j] = 0;
			}
		}
	}
	private void colorCell(int col,int row, int player) {
		if(player == 1)
		{
			circles[col][row].setFill(Color.BLUE);
		}
		if(player == 2) {
			circles[col][row].setFill(Color.RED);
		}
	}
	private void playerWin(int payer) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Over");
		alert.setHeaderText(null);
		alert.setContentText("Player " + player + " wins!");
		alert.showAndWait();
		clearMatrix();
	}
	private void clearMatrix() {
		for(int i = 0 ; i < COLUMNSIZE ; i++) {
			for(int j = 0 ; j < ROWSIZE ; j++) {
				circles[i][j].setFill(Color.TRANSPARENT);
				gameGrid.setHalignment(circles[i][j], javafx.geometry.HPos.CENTER);
				gameGrid.setValignment(circles[i][j], javafx.geometry.VPos.CENTER);
				gameLogic[i][j] = 0;
			}
		}
	}

}
