package edu.westga.cs1302.password_generator.view;

import edu.westga.cs1302.password_generator.viewmodel.PasswordGeneratorViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
    @FXML private TextArea output;
    @FXML private Label minimumLengthError;
    private PasswordGeneratorViewModel viewModel;
    
    @FXML
    void initialize() {
        this.viewModel = new PasswordGeneratorViewModel();

        this.mustIncludeDigits.selectedProperty().bindBidirectional(this.viewModel.getMustIncludeDigits());
        this.mustIncludeLowerCaseLetters.selectedProperty().bindBidirectional(this.viewModel.getMustIncludeLowerCaseLetters());
        this.mustIncludeUpperCaseLetters.selectedProperty().bindBidirectional(this.viewModel.getMustIncludeUpperCaseLetters());

        this.minimumLength.textProperty().bindBidirectional(this.viewModel.getMinimumLength());
        this.output.textProperty().bind(this.viewModel.getOutput());
  
        this.minimumLengthError.textProperty().bind(this.viewModel.getMinimumIntegerErrorLabel());
    }

    @FXML
    void generatePassword(ActionEvent event) {
        this.viewModel.generatePassword();
       
    }    
}
