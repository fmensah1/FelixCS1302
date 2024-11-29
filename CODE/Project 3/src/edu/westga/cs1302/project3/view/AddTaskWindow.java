package edu.westga.cs1302.project3.view;

import edu.westga.cs1302.project3.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** Codebehind for the Add Task Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class AddTaskWindow {

    @FXML
    private Button addTaskButton;
	@FXML
	private TextArea descriptonTextArea;

	@FXML
	private AnchorPane guiPane;

	@FXML
	private TextField titleTextfield;
	@FXML	
    private Button cancelButton;
	
	/**
	 * Binds to view model
	 * @param vm view modelS
	 */
	public void bindToVM(ViewModel vm) {
		vm.getTaskTitle().bind((this.titleTextfield.textProperty()));
		vm.getTaskDescription().bind((this.descriptonTextArea.textProperty()));
		
		this.addTaskButton.setOnAction((event)-> {
		 vm.addTask();
		 this.closeStage();
		});
		
		this.cancelButton.setOnAction((event) -> {
    		this.closeStage();
    	});
    	
	}

	private void closeStage() {
		Stage stage = (Stage) (this.guiPane).getScene().getWindow();
 		stage.close();
	}
}
