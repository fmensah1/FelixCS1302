package edu.westga.cs1302.project3.viewmodel;

import java.util.ArrayList;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskList;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Manages utilizing the model and makes properties available to bind the UI elements.
* 
* @author CS 1302
* @version Fall 2024
*/
public class ViewModel {
	private TaskList tasks;
	
	private ObservableList<Task> taskList;

	/**
	 * Instantiates a new student info view model.
	 */
	public ViewModel() {
		this.tasks = new TaskList();
		this.setDefaultTasks();
		this.taskList = new SimpleListProperty<Task>(FXCollections.observableArrayList(this.tasks.getTasks()));
	}
	
	private void setDefaultTasks() {
	this.tasks.addTask(new Task("Buy Groceries", "Get milk, eggs, and two loaves of bread."));
	this.tasks.addTask(new Task("Complete Assignment", "Finish the Java project by Friday."));
	this.tasks.addTask(new Task("Schedule Meeting", "Arrange a meeting with the project team."));
			      
	}
	
	/**
	 * Returns list property
	 * @return taskList
	 */
	public ObservableList<Task> getTaskList() {
		return this.taskList;
	}
	
	/** Set the taskList
	 * 
	 * @param tasks2 list of tasks aside default
	 */
	public void setTaskList(ArrayList<Task> tasks2) {
	    if (tasks2 == null) {
	        throw new IllegalArgumentException("Task list cannot be null");
	    }
	    this.taskList.setAll(tasks2);
	}
}
	
	
