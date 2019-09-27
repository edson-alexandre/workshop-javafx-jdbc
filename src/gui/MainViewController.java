package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable {

	@FXML
	MenuItem menuItemSeller;
	
	@FXML
	MenuItem menuItemDepartment;
	
	@FXML
	MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	
	@FXML
	public void onMenuItemDepartmentrAction() {
		System.out.println("onMenuItemDepartmentrAction");
	}

	@FXML
	public void onMenuItemAboutAction() {
		System.out.println("onMenuItemAboutAction");
	}

	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}

}
