package edu.westga.cs1302.password_generator.view;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/** Codebehind for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {

    @FXML private CheckBox mustIncludeDigits;
    @FXML private CheckBox mustIncludeLowerCaseLetters;
    @FXML private CheckBox mustIncludeUpperCaseLetters;
    @FXML private TextField minimumLength;
    @FXML private ListView<String> output;
    @FXML private Label errorTextLabel;
    @FXML private Button generatePasswordButton;
    
    private ViewModel vm;
    
    @FXML
    void initialize() {
    	this.vm = new ViewModel();
    	this.vm.getRequireDigits().bind(this.mustIncludeDigits.selectedProperty());
    	this.vm.getRequireLowercase().bind(this.mustIncludeLowerCaseLetters.selectedProperty());
    	this.vm.getRequireUppercase().bind(this.mustIncludeUpperCaseLetters.selectedProperty());
    	this.minimumLength.setText(this.vm.getMinimumLength().getValue());
    	this.vm.getMinimumLength().bind(this.minimumLength.textProperty());
    	 this.output.setItems(this.vm.getPastPasswords());

    	this.errorTextLabel.textProperty().bind(this.vm.getErrorText());
    	this.errorTextLabel.setVisible(false);
    	this.generatePasswordButton.setOnAction(
    			(event) -> { 
    				this.vm.generatePassword();
    			} 
    	);
    	
    	this.minimumLength.textProperty().addListener(
    			(observable, oldValue, newValue) -> {
    				try {
    					if (newValue.matches("\\d+")) {
    						this.errorTextLabel.setVisible(false);
    					} else {
    						this.errorTextLabel.setVisible(true);
    					}
    					if (newValue.isBlank()) {
    						this.errorTextLabel.setVisible(true);
    					}
    				} catch (NumberFormatException error) {
    					this.errorTextLabel.setVisible(true);
    					}
    			
    	});
    }
}
