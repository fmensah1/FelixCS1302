package edu.westga.cs1302.project3.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** Supports saving and loading bill data,
 * 
 * @author fmensah1
 * @version Fall 2024
 */
public class TaskManager {
	
	/** Saves the tasks
	 * 
	 * Writes all tasks data to a file
	 * 
	 * @precondition taskList != null
	 * @postcondition none
	 * 
	 * @param tasklist the task list to save
	 * @param filePath the file to be saved to.
	 * @throws IOException 
	 */
	public void saveTaskList(TaskList tasklist, String filePath) throws IOException, IllegalArgumentException {
		if (tasklist == null) {
			throw new IllegalArgumentException("Must provide a valid task list");
		}
		try (FileWriter writer = new FileWriter(filePath)) {
			for (Task task : tasklist.getTasks()) {
				writer.write(task.getTaskTitle() + System.lineSeparator()
				+ task.getTaskDescription()  + System.lineSeparator());
			}
			
		}
	}
	
	/** Load the tasks!
	 * 
	 * Reads from provided file
	 * File is assumed to use the same format described by saveStudentData
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param filePath to save file
	 * 
	 * @return the set of students loaded
	 */
	public TaskList loadTasks(String filePath) {
	    TaskList tasks = new TaskList();
	    File inputFile = new File(filePath);

	    try (Scanner reader = new Scanner(inputFile)) {
	        while (reader.hasNextLine()) {
	            String title = reader.nextLine();
	            if (reader.hasNextLine()) {
	                String description = reader.nextLine();
	                tasks.addTask(new Task(title, description));
	            } else {
	            	tasks = new TaskList();
	            }
	        }
	    } catch (FileNotFoundException error) {
	    	tasks = new TaskList();
	    } catch (Exception error) {
	    	tasks = new TaskList();
	    }
	    return tasks;
	}

}
