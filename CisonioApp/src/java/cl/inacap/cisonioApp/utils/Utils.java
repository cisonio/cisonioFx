package cl.inacap.cisonioApp.utils;

import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class Utils{

	public void validate(String mensaje, VBox lugar) {
		Label validationMsg = new Label();
		validationMsg.setText(mensaje);
		validationMsg.getStyleClass().add("validation");
		lugar.getChildren().add(validationMsg);
		PauseTransition pause = new PauseTransition(Duration.seconds(3));
		pause.setOnFinished(event ->
			lugar.getChildren().remove(validationMsg)
				);
		pause.play();
	}
	
	public void toast(String mensaje, VBox lugar) {
		Label validationMsg = new Label();
		validationMsg.setText(mensaje);
		validationMsg.getStyleClass().add("done");
		lugar.getChildren().add(validationMsg);
		PauseTransition pause = new PauseTransition(Duration.seconds(3));
		pause.setOnFinished(event ->
			lugar.getChildren().remove(validationMsg)
				);
		pause.play();
	}
	
	
	
	public ChangeListener<String> addNumberChecker(TextField tf) {
		/** The number checker method restringe el ingreso de texto en un txtinput a solo digitos */
		//https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
		ChangeListener<String> numberChecker = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, 
				String newValue) {
				if (!newValue.matches("\\d*")) {
					tf.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		};
		return numberChecker;
	}
	
	public ChangeListener<String> addTelefonoChecker(TextField tf) {
		/** The number checker method restringe el ingreso de texto en un txtinput a solo digitos */
		//https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
		ChangeListener<String> numberChecker = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, 
				String newValue) {
				if (newValue.length() > 4 || newValue.length() < 11) {
					if (!newValue.matches("^(\\+?56)?(\\s?)(0?9)(\\s?)[9876543]\\d*")) {
						tf.setText(oldValue);
					}
				}else{
					if (!newValue.matches("^(0?9)(\\s?)[9876543]\\d*")) { //si el numero tiene mas de 10 digitos no tiene +56 
						tf.setText(oldValue);
					}
					if(newValue.length() > 13) {
						tf.setText(oldValue);
					}
				}
			}
		};
		return numberChecker;
	}
}
