package edu.westga.cs1302.project2.model;

import java.util.ArrayList;

/**
 * Manages a set of ingredients.
 * 
 * @author fmensah1
 * @version Fall 24
 */
public class Recipe {
	private ArrayList<Ingredient> items;
	private String recipeName;
	
	/**
	 * Create a new empty recipe with no name set
	 */
	public Recipe() {
		this.recipeName = "No name set";
		this.items = new ArrayList<Ingredient>();
	}
	
	/** Return the recipe name for the ingredients
	 * 
	 * @return the recipe name for the ingredients
	 */
	public String getRecipeName() {
		return this.recipeName;
	}
	
	/** Sets the recipe name for the ingredients
	 * 
	 * @precondition name != null && !name.isEmpty()
	 * @postcondition getServerName() == name
	 * 
	 * @param name the name of the server for the bill
	 */
	public void setRecipeName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("name must not be null.");
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name must not be empty.");
		}
		this.recipeName = name;
	}
	
	/**
	 * Adds the item to the bill
	 * 
	 * @precondition item != null 
	 * @postcondition ingredient is added to the list of items in the recipe.
	 * 
	 * @param item the item to be added to the bill
	 */
	public void addItem(Ingredient item) {
		if (item == null) {
			throw new IllegalArgumentException("item must not be null.");
		}
		//if (this.size == Bill.MAX_NUMBER_OF_ITEMS) {
		//throw new IllegalStateException("bill items list is full");
		//}
		this.items.add(item);
	}
	
	/** Return the number of items in the recipe
	 * 
	 * @return the number of items in the recipe
	 */
	public int getSize() {
		return this.items.size();
	}

	/**
	 * Return the items in the recipe
	 * 
	 * @return the items in the recipe
	 */
	public ArrayList<Ingredient> getItems() {
		return this.items;
	}
}
