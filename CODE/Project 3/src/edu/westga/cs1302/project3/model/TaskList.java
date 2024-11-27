package edu.westga.cs1302.project3.model;

import java.util.ArrayList;

/**
 * Manages all tasks stores in a set.
 *
 * @author fmensah1
 * @version Fall 24
 */
public class TaskList {
	private ArrayList<Task> setOfTasks;
	
	/**
	 *  Creates an initially empty list of task.
	 */
	public TaskList() {
		this.setOfTasks = new ArrayList<Task>();
	}

	/**
	 * Gets the tasks.
	 *
	 * @return the tasks
	 */
	public ArrayList<Task> getTasks() {
		return this.setOfTasks;
	}

	/**
	 * Adds the task.
	 *
	 * @param task a Task object
	 */
	public void addTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("Invalid Task");
		}
		this.setOfTasks.add(task);
	}
	
	/**
	 * Remove a task
	 * 
	 * @param task a task objects
	 */
	public void removeTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("Invalid Task");
		}
		this.setOfTasks.remove(task);
	}
	
}
