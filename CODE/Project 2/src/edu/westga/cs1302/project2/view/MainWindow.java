package edu.westga.cs1302.project2.view;


import java.io.IOException;
import java.util.Comparator;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeManager;
import edu.westga.cs1302.project2.model.IngredientNameComparator;
import edu.westga.cs1302.project2.model.IngredientTypeComparator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	@FXML private ComboBox<String> ingredientType;
	@FXML private ListView<Ingredient> ingredientsList;
	@FXML private TextField ingredientName;
	@FXML private ComboBox<Comparator<Ingredient>> sortByComboBox;
	@FXML private ListView<Ingredient> recipeListView;
	@FXML private TextField recipeName;
	@FXML private Recipe recipe;
	@FXML private RecipeManager recipeManager;
	@FXML private TextArea recipeArea;
	
	@FXML
	void addIngredient(ActionEvent event) {
		try {
			this.ingredientsList.getItems().add(new Ingredient(this.ingredientName.getText(), this.ingredientType.getValue()));
			this.ingredientName.clear();
			this.ingredientType.getSelectionModel().clearSelection();
		} catch (IllegalArgumentException error) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Unable to add ingredient");
			alert.setContentText(error.getMessage());
			alert.showAndWait();
		}
		this.sortIngredientsList();
	}

	@FXML
	void removeIngredient(ActionEvent event) {
		Ingredient selectedIngredient = this.ingredientsList.getSelectionModel().getSelectedItem();
		if (selectedIngredient != null) {
			this.ingredientsList.getItems().remove(selectedIngredient);
		}
		this.sortIngredientsList();
	}
	
	@FXML
	void sortIngredients(ActionEvent event) {
		this.ingredientsList.getItems().sort(this.sortByComboBox.getValue());
	}
	
	private void sortIngredientsList() {
		this.ingredientsList.getItems().sort(this.sortByComboBox.getValue());
	}
	
    @FXML
    void addToRecipe(ActionEvent event) {
    	Ingredient selectedIngredient = this.ingredientsList.getSelectionModel().getSelectedItem();
    try {	
    	if (selectedIngredient != null) {
    	this.recipe.addItem(selectedIngredient);
    	this.recipeListView.getItems().add(selectedIngredient);
    	} 
    } catch (IllegalArgumentException error) {
    	if (this.recipeListView.getItems().contains(selectedIngredient)) {
    	  Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setHeaderText("Ingredient already exists");
          alert.setContentText(error.getMessage());
		}
    }
    
    }
    
    @FXML
    void addToBook(ActionEvent event) {
    	if (this.recipeName.getText().isEmpty()) {
    		   Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Recipe name is required");
               alert.setContentText("Please enter a name for the recipe.");
               alert.showAndWait();
               return;
    	}
    	
    	this.recipe.setRecipeName(this.recipeName.getText());
    	
    	try {
    		this.recipeManager.saveRecipeData(this.recipe);
    		Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setHeaderText("Recipe saved successfully!");
            successAlert.showAndWait();
    	}  catch (IllegalStateException error) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Recipe already exists");
            alert.setContentText(error.getMessage());
            alert.showAndWait();
    	} catch (IOException error) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error saving recipe");
            alert.setContentText("An error occurred while saving the recipe.");
            alert.showAndWait();
        }
    }
    
    @FXML
    void displayRecipes(ActionEvent event) {
       //r
    }

	@FXML
	void initialize() {
		this.recipe = new Recipe();
		this.recipeManager = new RecipeManager();
		this.ingredientType.getItems().add("Vegetable");
		this.ingredientType.getItems().add("Meat");
		this.ingredientType.getItems().add("Bread");
		this.ingredientType.getItems().add("Fruit");
		this.ingredientType.getItems().add("Spice");
		
		this.sortByComboBox.getItems().add(new IngredientNameComparator());
		this.sortByComboBox.getItems().add(new IngredientTypeComparator());
		this.sortByComboBox.setValue(this.sortByComboBox.getItems().get(0));   

	}
}
