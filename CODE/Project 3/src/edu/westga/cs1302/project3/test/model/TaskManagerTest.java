package edu.westga.cs1302.project3.test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskList;
import edu.westga.cs1302.project3.model.TaskManager;

import java.io.File;
import java.io.IOException;

/**
 * Tests the TaskManager class.
 * 
 * @version Fall 2024
 */
public class TaskManagerTest {

    private TaskManager taskManager;
    private TaskList taskList;
    private final String TEST_FILE = "test_tasks.txt";

    @BeforeEach
    public void setUp() {
        this.taskManager = new TaskManager();
        this.taskList = new TaskList();

        this.taskList.addTask(new Task("Task 1", "Description 1"));
        this.taskList.addTask(new Task("Task 2", "Description 2"));
        this.taskList.addTask(new Task("Task 3", "Description 3"));
    }

    @AfterEach
    public void tearDown() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSaveTaskListAndLoadTasks() {
    	 this.taskManager = new TaskManager();
         this.taskList = new TaskList();
         
         this.taskList.addTask(new Task("Task 1", "Description 1"));
         this.taskList.addTask(new Task("Task 2", "Description 2"));
         this.taskList.addTask(new Task("Task 3", "Description 3"));
        try {
            this.taskManager.saveTaskList(this.taskList, TEST_FILE);

            TaskList loadedTaskList = this.taskManager.loadTasks(TEST_FILE);

            assertEquals(this.taskList.getTasks().size(), loadedTaskList.getTasks().size(),
                    "Loaded task list size should match the saved task list size.");

            for (String title : this.taskList.getTasks().keySet()) {
                Task savedTask = this.taskList.getTasks().get(title);
                Task loadedTask = loadedTaskList.getTasks().get(title);

                assertNotNull(loadedTask, "Loaded task should not be null.");
                assertEquals(savedTask.getTaskTitle(), loadedTask.getTaskTitle(),
                        "Task title should match for task: " + title);
                assertEquals(savedTask.getTaskDescription(), loadedTask.getTaskDescription(),
                        "Task description should match for task: " + title);
            }
        } catch (IOException e) {
            fail("Exception should not occur during save or load: " + e.getMessage());
        }
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSaveTaskListWithNullTaskListThrowsException() {
    	 this.taskManager = new TaskManager();
         this.taskList = new TaskList();
         this.taskList.addTask(new Task("Task 1", "Description 1"));
         this.taskList.addTask(new Task("Task 2", "Description 2"));
         this.taskList.addTask(new Task("Task 3", "Description 3"));
        assertThrows(IllegalArgumentException.class, () -> {
            this.taskManager.saveTaskList(null, TEST_FILE);
        }, "Saving a null task list should throw an IllegalArgumentException.");
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testLoadTasksFromNonExistentFile() {
    	 this.taskManager = new TaskManager();
         this.taskList = new TaskList();
         this.taskList.addTask(new Task("Task 1", "Description 1"));
         this.taskList.addTask(new Task("Task 2", "Description 2"));
         this.taskList.addTask(new Task("Task 3", "Description 3"));
        TaskList loadedTaskList = this.taskManager.loadTasks("non_existent_file.txt");
        assertNotNull(loadedTaskList, "Loaded task list should not be null.");
        assertTrue(loadedTaskList.getTasks().isEmpty(), "Loaded task list should be empty.");
    }
}
