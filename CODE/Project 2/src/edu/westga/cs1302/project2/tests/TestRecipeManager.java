//package edu.westga.cs1302.project2.tests;
//
//import edu.westga.cs1302.project2.model.Recipe;
//import edu.westga.cs1302.project2.model.RecipeManager;
//import edu.westga.cs1302.project2.model.Ingredient;
//import org.junit.jupiter.api.Test;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//
//class RecipeManagerTest {
//	public static String DATA_FILE = "data.txt";
//    private RecipeManager manager;
//    private Recipe recipe1;
//    private File tempFile;
//
//
//    @Test
//    public void testSaveRecipeDataWithValidRecipe() throws IOException {
//    	tempFile = File.createTempFile("data", ".txt");
//        RecipeManager.DATA_FILE = tempFile.getAbsolutePath();  // Set DATA_FILE to this temp file
//        manager = new RecipeManager();
//        
//        // Initialize a recipe for testing
//        recipe1 = new Recipe();
//        recipe1.setRecipeName("Salad");
//        recipe1.addItem(new Ingredient("Lettuce", "Vegetable"));
//        recipe1.addItem(new Ingredient("Tomato", "Vegetable"));
//        manager.saveRecipeData(recipe1);
//        List<String> lines = Files.readAllLines(tempFile.toPath());
//        assertFalse(lines.isEmpty());
//        assertTrue(lines.get(0).contains("Recipe Name : Salad"));
//        assertTrue(lines.get(0).contains("Lettuce"));
//        assertTrue(lines.get(0).contains("Tomato"));
//        Files.deleteIfExists(tempFile.toPath()); 
//    }
//
//    @Test
//    public void testSaveRecipeDataWithNullRecipeThrowsException() throws IOException {
//    	tempFile = File.createTempFile("data", ".txt");
//        RecipeManager.DATA_FILE = tempFile.getAbsolutePath();
//        manager = new RecipeManager();
//        
//        // Initialize a recipe for testing
//        recipe1 = new Recipe();
//        recipe1.setRecipeName("Salad");
//        recipe1.addItem(new Ingredient("Lettuce", "Vegetable"));
//        recipe1.addItem(new Ingredient("Tomato", "Vegetable"));
//        assertThrows(IllegalArgumentException.class, () -> manager.saveRecipeData(null));
//        Files.deleteIfExists(tempFile.toPath()); 
//    }
//
//    @Test
//    public void testSaveRecipeDataWithDuplicateRecipeThrowsException() throws IOException {
//    	tempFile = File.createTempFile("data", ".txt");
//        RecipeManager.DATA_FILE = tempFile.getAbsolutePath();
//        manager = new RecipeManager();
//  
//        recipe1 = new Recipe();
//        recipe1.setRecipeName("Salad");
//        recipe1.addItem(new Ingredient("Lettuce", "Vegetable"));
//        recipe1.addItem(new Ingredient("Tomato", "Vegetable"));
//        manager.saveRecipeData(recipe1);
//        assertThrows(IllegalStateException.class, () -> manager.saveRecipeData(recipe1));
//        Files.deleteIfExists(tempFile.toPath()); 
//    }
//
//    @Test
//    public void testRecipeExistsReturnsTrueForExistingRecipe() throws IOException {
//    	tempFile = File.createTempFile("data", ".txt");
//        RecipeManager.DATA_FILE = tempFile.getAbsolutePath(); 
//        manager = new RecipeManager();
//
//        recipe1 = new Recipe();
//        recipe1.setRecipeName("Salad");
//        recipe1.addItem(new Ingredient("Lettuce", "Vegetable"));
//        recipe1.addItem(new Ingredient("Tomato", "Vegetable"));
//        manager.saveRecipeData(recipe1);
//        assertTrue(manager.recipeExists("Salad"));
//        Files.deleteIfExists(tempFile.toPath()); 
//    }
//
//    @Test
//    public void testRecipeExistsReturnsFalseForNonExistentRecipe() throws IOException {
//    	tempFile = File.createTempFile("data", ".txt");
//        RecipeManager.DATA_FILE = tempFile.getAbsolutePath(); 
//        manager = new RecipeManager();
//        
//        recipe1 = new Recipe();
//        recipe1.setRecipeName("Salad");
//        recipe1.addItem(new Ingredient("Lettuce", "Vegetable"));
//        recipe1.addItem(new Ingredient("Tomato", "Vegetable"));
//        assertFalse(manager.recipeExists("NonExistentRecipe"));
//        Files.deleteIfExists(tempFile.toPath()); 
//    }
//
//    @Test
//    public void testSaveRecipeDataWritesMultipleRecipes() throws IOException {
//    	tempFile = File.createTempFile("data", ".txt");
//        RecipeManager.DATA_FILE = tempFile.getAbsolutePath();  
//        manager = new RecipeManager();
//
//        recipe1 = new Recipe();
//        recipe1.setRecipeName("Salad");
//        recipe1.addItem(new Ingredient("Lettuce", "Vegetable"));
//        recipe1.addItem(new Ingredient("Tomato", "Vegetable"));
//        Recipe recipe2 = new Recipe();
//        recipe2.setRecipeName("Soup");
//        recipe2.addItem(new Ingredient("Carrot", "Vegetable"));
//        recipe2.addItem(new Ingredient("Onion", "Vegetable"));
//        
//        manager.saveRecipeData(recipe1);
//        manager.saveRecipeData(recipe2);
//        
//        List<String> lines = Files.readAllLines(tempFile.toPath());
//        assertTrue(lines.size() > 1);
//        assertTrue(lines.stream().anyMatch(line -> line.contains("Recipe Name : Salad")));
//        assertTrue(lines.stream().anyMatch(line -> line.contains("Recipe Name : Soup")));
//        Files.deleteIfExists(tempFile.toPath()); 
//    }
//}
