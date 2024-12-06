package edu.westga.cs1302.project3.test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskList;

	/**
	 * Tests the TaskList class.
	 * 
	 * @version Fall 2024
	 */
	public class TaskListTest {

	    private TaskList taskList;

	    

	    /**
	     * Test adding a valid task.
	     */
	    @Test
	    public void testAddValidTask() {
	    	 this.taskList = new TaskList();
	        Task task = new Task("Study", "Complete math homework");
	        this.taskList.addTask(task);
	        assertEquals(task, this.taskList.getTask("Study"), "The task should be added to the task list");
	    }

	    /**
	     * Test adding a null task.
	     */
	    @Test
	    public void testAddNullTaskShouldThrowException() {
	    	 this.taskList = new TaskList();
	        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
	            this.taskList.addTask(null);
	        });
	        assertEquals("Invalid Task", exception.getMessage(), "Expected exception message for null task");
	    }

	    /**
	     * Test adding a task with a duplicate title.
	     */
	    @Test
	    public void testAddTaskWithDuplicateTitleShouldThrowException() {
	    	 this.taskList = new TaskList();
	        Task task1 = new Task("Study", "Complete math homework");
	        Task task2 = new Task("Study", "Prepare for history exam");
	        this.taskList.addTask(task1);

	        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
	            this.taskList.addTask(task2);
	        });
	        assertEquals("A task with the same title already exists.", exception.getMessage(),
	                "Expected exception message for duplicate title");
	    }

	    /**
	     * Test removing a task by its title.
	     */
	    @Test
	    public void testRemoveTaskByTitle() {
	    	this.taskList = new TaskList();
	        Task task = new Task("Study", "Complete math homework");
	        this.taskList.addTask(task);
	        Task removedTask = this.taskList.removeTask("Study");
	        assertEquals(task, removedTask, "The removed task should match the added task");
	        assertNull(this.taskList.getTask("Study"), "The task list should no longer contain the removed task");
	    }

	    /**
	     * Test removing a task with a null title.
	     */
	    @Test
	    public void testRemoveTaskWithNullTitleShouldThrowException() {
	    	 this.taskList = new TaskList();
	        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
	            this.taskList.removeTask(null);
	        });
	        assertEquals("Invalid Title", exception.getMessage(), "Expected exception message for null title");
	    }

	    /**
	     * Test removing a task with an empty title.
	     */
	    @Test
	    public void testRemoveTaskWithEmptyTitleShouldThrowException() {
	    	this.taskList = new TaskList();
	        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
	            this.taskList.removeTask("");
	        });
	        assertEquals("Invalid Title", exception.getMessage(), "Expected exception message for empty title");
	    }

	    /**
	     * Test getting a task by its title.
	     */
	    @Test
	    public void testGetTaskByTitle() {
	    	this.taskList = new TaskList();
	        Task task = new Task("Study", "Complete math homework");
	        this.taskList.addTask(task);
	        assertEquals(task, this.taskList.getTask("Study"), "The retrieved task should match the added task");
	    }

	    /**
	     * Test getting a task with a null title.
	     */
	    @Test
	    public void testGetTaskWithNullTitleShouldThrowException() {
	    	this.taskList = new TaskList();
	        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
	            this.taskList.getTask(null);
	        });
	        assertEquals("Invalid Title", exception.getMessage(), "Expected exception message for null title");
	    }

	    /**
	     * Test getting a task with an empty title.
	     */
	    @Test
	    public void testGetTaskWithEmptyTitleShouldThrowException() {
	    	this.taskList = new TaskList();
	        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
	            this.taskList.getTask("");
	        });
	        assertEquals("Invalid Title", exception.getMessage(), "Expected exception message for empty title");
	    }

	    /**
	     * Test getting all tasks.
	     */
	    @Test
	    public void testGetAllTasks() {
	    	this.taskList = new TaskList();
	        Task task1 = new Task("Study", "Complete math homework");
	        Task task2 = new Task("Code", "Finish Java project");
	        this.taskList.addTask(task1);
	        this.taskList.addTask(task2);

	        assertEquals(2, this.taskList.getTasks().size(), "The task list should contain two tasks");
	        assertTrue(this.taskList.getTasks().containsKey("Study"), "The task list should contain 'Study'");
	        assertTrue(this.taskList.getTasks().containsKey("Code"), "The task list should contain 'Code'");
	    }
	}


