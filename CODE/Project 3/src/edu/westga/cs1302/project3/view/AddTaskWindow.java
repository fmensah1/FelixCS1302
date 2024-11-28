package edu.westga.cs1302.project3.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** Codebehind for the Add Task Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class AddTaskWindow {
	
    @FXML
    private AnchorPane guiPane;
	@FXML	
    private Button cancelButton;
	
	@FXML
	void initialize() {
		this.cancelButton.setOnAction((event) -> {
    		Stage stage = (Stage) (this.guiPane).getScene().getWindow();
    		stage.close();
    	});
    	
	}
}
