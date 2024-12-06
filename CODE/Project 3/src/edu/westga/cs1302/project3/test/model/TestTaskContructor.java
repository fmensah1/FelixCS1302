package edu.westga.cs1302.project3.test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;

/**
 * Tests the Task class constructor and methods.
 * 
 * @version Fall 2024
 */
public class TestTaskContructor {

    /**
     * Test creating a Task with valid title and description.
     */
    @Test
    public void testValidTaskCreation() {
        Task task = new Task("Study", "Complete math homework");
        assertEquals("Study", task.getTaskTitle(), "Task title should be 'Study'");
        assertEquals("Complete math homework", task.getTaskDescription(), "Task description should be 'Complete math homework'");
    }

    /**
     * Test creating a Task with null title.
     */
    @Test
    public void testTaskWithNullTitleShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, "Description");
        });
        assertEquals("Invalid title", exception.getMessage(), "Expected exception message for null title");
    }

    /**
     * Test creating a Task with empty title.
     */
    @Test
    public void testTaskWithEmptyTitleShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("", "Description");
        });
        assertEquals("Invalid title", exception.getMessage(), "Expected exception message for empty title");
    }

    /**
     * Test creating a Task with null description.
     */
    @Test
    public void testTaskWithNullDescriptionShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("Title", null);
        });
        assertEquals("Invalid description", exception.getMessage(), "Expected exception message for null description");
    }

    /**
     * Test creating a Task with empty description.
     */
    @Test
    public void testTaskWithEmptyDescriptionShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("Title", "");
        });
        assertEquals("Invalid description", exception.getMessage(), "Expected exception message for empty description");
    }
}
