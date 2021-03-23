package application;
import application.ShuntingYardEvaluator;

import javafx.geometry.Insets; 
import javafx.geometry.Pos;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class Main extends Application {
	
	String display = "0";
	Text textField = new Text(display);
	
	// Set the text field
	public void setTextField(String tf) {
		display = tf;
		textField.setText(tf);
	}
	
	// Add's the expression to the display field. Taking into account mathematical syntax rules.
	public void addExpression(String expression) {
		// Clear display to "0"
		if(expression == "C") {
			setTextField("0");
		}
		else if(expression == "=") {
			// Parse expression using ShuntingYardAlgorithm. May implement my own parser eventually.
			ShuntingYardEvaluator SYE = new ShuntingYardEvaluator();
			double result = SYE.doTheShuntingYard(display);
			setTextField("" + result);
		}
		else {
			// Remove char at end of display string if display length is > 1
			if(expression == "<") {
				if(display.length() > 1) {
					setTextField(display.substring(0, display.length() - 1));
				}
				// Display length is 1. Just make display "0"
				else {
					setTextField("0");
				}
			}
			// Try to add the expression to display. Has to be regex tested.
			else {
					// If starting from C
					if(display == "0") {
						if(expression.matches("[0-9]")) {
							setTextField(expression);
						}
						else {
							setTextField(display + expression);
						}
					}
					// Not C
					else {
						String newDisplay = display + expression;
						if(newDisplay.matches("([0-9]+(\\.([0-9]+)?)?[/*%+-]?)+")) {
							// If not a digit expression check to see if last char of display is decimal. If it is then just make it a .0
							if(expression.matches("[+/*%-]")) {
								if(display.charAt(display.length() - 1) == '.') {
									setTextField(display + "0" + expression);
								}
								else {
									setTextField(newDisplay);
								}
							}
							else {
								setTextField(newDisplay);
							}
						}
					}
				}
			}
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			// Create Text Field
			Text altInfo = new Text("Press ALT for keyboard");
			
			// Create Buttons 
			Button button0 = new Button("_0");
			button0.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			Button button1 = new Button("_1");
			button1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			Button button2 = new Button("_2");
			button2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			Button button3 = new Button("_3");
			button3.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			Button button4 = new Button("_4");
			button4.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			Button button5 = new Button("_5");
			button5.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			Button button6 = new Button("_6");
			button6.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			Button button7 = new Button("_7");
			button7.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			Button button8 = new Button("_8");
			button8.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			Button button9 = new Button("_9");
			button9.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			Button buttonDECIMAL = new Button("_.");
			buttonDECIMAL.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			Button buttonPLUS = new Button("_+");
			buttonPLUS.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			Button buttonMINUS = new Button("_-");
			buttonMINUS.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			Button buttonMOD = new Button("_%");
			buttonMOD.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			Button buttonMULTIPLY = new Button("_*");
			buttonMULTIPLY.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			Button buttonEQUALS = new Button("_=");
			buttonEQUALS.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			Button buttonDIVIDE = new Button("_/");
			buttonDIVIDE.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			Button buttonCLEAR = new Button("_C");
			buttonCLEAR.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			Button buttonBACK = new Button("_<");
			buttonBACK.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			 
			// Create and set up GridPane
			GridPane gridPane = new GridPane();
			//gridPane.setMinSize(400, 200); // Size
			gridPane.setPadding(new Insets(5,5,5,5)); // Padding
			gridPane.setVgap(3); // Horizontal and Vertical gaps between columns
			gridPane.setHgap(3);
			gridPane.setAlignment(Pos.CENTER); // Grid alignment
			
			// Arrange Buttons / Fields
			gridPane.add(textField, 4, 0);
			gridPane.add(button7, 0, 1);
			gridPane.add(button8, 1, 1);
			gridPane.add(button9, 2, 1);
			gridPane.add(button4, 0, 2);
			gridPane.add(button5, 1, 2);
			gridPane.add(button6, 2, 2);
			gridPane.add(button1, 0, 3);
			gridPane.add(button2, 1, 3);
			gridPane.add(button3, 2, 3);
			gridPane.add(button0, 0, 4);
			gridPane.add(buttonPLUS, 3, 4);
			gridPane.add(buttonMINUS, 3, 3);
			gridPane.add(buttonMOD, 4, 3);
			gridPane.add(buttonMULTIPLY, 3, 2);
			gridPane.add(buttonDIVIDE, 3, 1);
			gridPane.add(buttonEQUALS, 4, 4);
			gridPane.add(buttonDECIMAL, 2, 4);
			gridPane.add(buttonCLEAR, 4, 1);
			gridPane.add(buttonBACK, 4, 2);
			gridPane.add(altInfo, 5, 0);
			
			// Handles all button inputs
			EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if(event.getSource() == button0) {
						addExpression("0");
					}
					else if(event.getSource() == button1) {
						addExpression("1");
					}
					else if(event.getSource() == button2) {
						addExpression("2");
					}
					else if(event.getSource() == button3) {
						addExpression("3");
					}
					else if(event.getSource() == button4) {
						addExpression("4");
					}
					else if(event.getSource() == button5) {
						addExpression("5");
					}
					else if(event.getSource() == button6) {
						addExpression("6");
					}
					else if(event.getSource() == button7) {
						addExpression("7");
					}
					else if(event.getSource() == button8) {
						addExpression("8");
					}
					else if(event.getSource() == button9) {
						addExpression("9");
					}
					else if(event.getSource() == buttonPLUS) {
						addExpression("+");
					}
					else if(event.getSource() == buttonMINUS) {
						addExpression("-");
					}
					else if(event.getSource() == buttonMOD) {
						addExpression("%");
					}
					else if(event.getSource() == buttonMULTIPLY) {
						addExpression("*");
					}
					else if(event.getSource() == buttonDIVIDE) {
						addExpression("/");
					}
					else if(event.getSource() == buttonDECIMAL) {
						addExpression(".");
					}
					else if(event.getSource() == buttonCLEAR) {
						addExpression("C");
					}
					else if(event.getSource() == buttonEQUALS) {
						addExpression("=");
					}
					else {
						addExpression("<");
					}
				}				
			};
								
			// Set button handlers
			button0.setOnAction(buttonHandler);
			button1.setOnAction(buttonHandler);
			button2.setOnAction(buttonHandler);
			button3.setOnAction(buttonHandler);
			button4.setOnAction(buttonHandler);
			button5.setOnAction(buttonHandler);
			button6.setOnAction(buttonHandler);
			button7.setOnAction(buttonHandler);
			button8.setOnAction(buttonHandler);
			button9.setOnAction(buttonHandler);
			buttonPLUS.setOnAction(buttonHandler);
			buttonMINUS.setOnAction(buttonHandler);
			buttonMOD.setOnAction(buttonHandler);
			buttonMULTIPLY.setOnAction(buttonHandler);
			buttonDIVIDE.setOnAction(buttonHandler);
			buttonEQUALS.setOnAction(buttonHandler);
			buttonDECIMAL.setOnAction(buttonHandler);
			buttonCLEAR.setOnAction(buttonHandler);
			buttonBACK.setOnAction(buttonHandler);
			
			// Style
			gridPane.setStyle("-fx-background-color: WHITE;"); 
			
			// Set the scene
			Scene scene = new Scene(gridPane);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Calculator"); //Window Title
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
