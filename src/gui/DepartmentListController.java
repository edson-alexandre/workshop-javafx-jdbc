package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {
	
	private DepartmentService service;
	
	@FXML
	private Button btNew;
		
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	private ObservableList<Department> obsList;
	
	public void onBtNewAction() {
		System.out.println("onBtAction");
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.service=service;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNotes();
	}
	
	public void updateTableView() {
		if(service == null ) {
			throw new IllegalStateException("Service was null");
		}
		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewDepartment.setItems(obsList);
	}

	private void initializeNotes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		Stage stage = (Stage) Main.getMainScne().getWindow();
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
	}

}
