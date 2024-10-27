package edu.westga.cs1302.project2.model;

import java.util.Comparator;

/**
 * Compares ingredients objects based on their name.
 * 
 * @author fmensah1
 * @version Fall 24
 * 
 */
public class IngredientNameComparator implements Comparator<Ingredient> {
    @Override 
	public int compare(Ingredient o1, Ingredient o2) {
		return o1.getName().compareTo(o2.getName());
				
	}
}
