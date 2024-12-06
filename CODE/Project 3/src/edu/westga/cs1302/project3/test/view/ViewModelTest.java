package edu.westga.cs1302.project3.test.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Tests the ViewModel class.
 * 
 * @version Fall 2024
 */
public class ViewModelTest {

    private ViewModel viewModel;

    @BeforeEach
    public void setUp() {
        this.viewModel = new ViewModel();
    }

    @Test
    public void testAddTaskWithValidData() {
        this.viewModel.getTaskTitle().set("Study");
        this.viewModel.getTaskDescription().set("Complete math homework");
        
        this.viewModel.addTask();
        
        assertEquals(4, this.viewModel.getTaskList().size(), "Task list size should increase to 4.");
        Task addedTask = this.viewModel.getTaskList().get(3);
        assertEquals("Study", addedTask.getTaskTitle(), "The added task title should match.");
        assertEquals("Complete math homework", addedTask.getTaskDescription(), "The added task description should match.");
    }


    @Test
    public void testRemoveTask() {
        Task taskToRemove = this.viewModel.getTaskList().get(0);
        this.viewModel.removeTask(taskToRemove);

        assertEquals(2, this.viewModel.getTaskList().size(), "Task list size should decrease to 2.");
        assertFalse(this.viewModel.getTaskList().contains(taskToRemove), "The removed task should no longer exist in the list.");
    }

    @Test
    public void testSetTaskListWithValidCollection() {
        Collection<Task> newTasks = new ArrayList<>();
        newTasks.add(new Task("Task 1", "Description 1"));
        newTasks.add(new Task("Task 2", "Description 2"));
        newTasks.add(new Task("Task 3", "Description 3"));

        this.viewModel.setTaskList(newTasks);

        assertEquals(3, this.viewModel.getTaskList().size(), "Task list size should be 3.");
        assertEquals("Task 1", this.viewModel.getTaskList().get(0).getTaskTitle(), "First task title should match.");
        assertEquals("Task 2", this.viewModel.getTaskList().get(1).getTaskTitle(), "Second task title should match.");
        assertEquals("Task 3", this.viewModel.getTaskList().get(2).getTaskTitle(), "Third task title should match.");
    }

    @Test
    public void testSetTaskListWithNullShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> this.viewModel.setTaskList(null),
                "Setting task list to null should throw an exception.");
    }
}
