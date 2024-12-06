package edu.westga.cs1302.project3.viewmodel;

import java.util.Collection;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskList;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/** Manages utilizing the model and makes properties available to bind the UI elements.
* 
* @author CS 1302
* @version Fall 2024
*/
public class ViewModel {
	private StringProperty taskTitle;
	private StringProperty taskDescription;
	
	private ObservableList<Task> taskList;
	private TaskList tasks;
	
	/**
	 * Instantiates a new student info view model.
	 */
	public ViewModel() {
		this.taskTitle = new SimpleStringProperty("");
		this.taskDescription = new SimpleStringProperty("");
		this.tasks = new TaskList();
		this.setDefaultTasks();
		this.taskList = new SimpleListProperty<Task>(FXCollections.observableArrayList(this.tasks.getTasks().values()));
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
	public void setTaskList(Collection<Task> tasks2) {
	    if (tasks2 == null) {
	        throw new IllegalArgumentException("Task list cannot be null");
	    }
	    this.taskList.setAll(tasks2);
	}

	/** Gets the string property for title
	 * 
	 * @return string property
	 */
	public StringProperty getTaskTitle() {
		return this.taskTitle;
	}
	
	/** Gets the task description
	 * 
	 * @return string property
	 */
	public StringProperty getTaskDescription() {
		return this.taskDescription;
	}
	
	/** Adds a new task to the list
	 * 
	 */
	public void addTask() {
		String title = this.taskTitle.get();
		String description = this.taskDescription.get();
		try {
		Task task = new Task(title, description);
		this.tasks.addTask(task);
		this.taskList.add(task);
		} catch (IllegalArgumentException invalidLengthError) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Inalid Task");
			alert.setContentText("Provide a valid Task title and description that doesn't exists already");
			alert.showAndWait();
		}
	}
	
	/**
	 * remove task
	 * @param task to be removed
	 */
	public void removeTask(Task task) {
		if (task != null) {
			this.taskList.remove(task);
		}
	}
}
	
	
