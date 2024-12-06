package edu.westga.cs1302.project3.model;

/**
 * Stores information for a Task.
 *
 * @author fmensah1
 * @version Fall 2024
 */
public class Task {

	private String taskTitle;
	private String taskDescription;

	/**
	 * Creates a new Task object with the specified title and description.
	 *
	 * @param title       the title
	 * @param description the description
	 * @preconditon title != null || title.isEmpty() title != null || description !=
	 *              null
	 * @postcondition getTitle() == taskTitle && description.isEmpty()) =
	 *                taskDescription
	 */
	public Task(String title, String description) {
		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("Invalid title");
		}
		if (description == null || description.isEmpty()) {
			throw new IllegalArgumentException("Invalid description");
		}

		this.taskTitle = title;
		this.taskDescription = description;
	}

	/**
	 * Gets the task title.
	 *
	 * @return the task title
	 */
	public String getTaskTitle() {
		return this.taskTitle;
	}

	/**
	 * Gets the task description.
	 *
	 * @return the task description
	 */
	public String getTaskDescription() {
		return this.taskDescription;
	}

	/**
	 * Sets the task title.
	 *
	 * @param taskTitle the new task title
	 */
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	/**
	 * Sets the task description.
	 *
	 * @param taskDescription the new task description
	 */
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	@Override
	public String toString() {
		return this.taskTitle;
	}

}
