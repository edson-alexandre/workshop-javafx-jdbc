package gui.util;

import javafx.scene.control.TextField;

public class Constraints {
	public void setTextFieldInteger(TextField txt) {
		txt.textProperty().addListener((obs, oldValue, newValue) -> {
			if(newValue!=null && !newValue.matches("\\d*")) {
				txt.setText(oldValue);
			}
		});
	}
	
	public void setMaxLength(TextField txt, Integer max ) {
		txt.textProperty().addListener((obs , oldValue , newValue) ->{
			if( newValue!=null && newValue.length()>max) {
				txt.setText(oldValue);
			}
		});
	}
	
	public void settextFieldDouble(TextField txt) {
		txt.textProperty().addListener((obs, oldValue, newValue ) -> {
			if(newValue != null && newValue.matches("\\d*([\\.]\\d*)?")) {
				txt.setText(oldValue);
			}
		});
	}
}
