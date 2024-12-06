package edu.westga.cs1302.project3.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages all tasks stored in a map.
 * 
 * Ensures no two tasks have the same title.
 * 
 * @author fmensah1
 * @version Fall 24
 */
public class TaskList {
    private Map<String, Task> taskLookup;

    /**
     * Creates an initially empty task list.
     */
    public TaskList() {
        this.taskLookup = new HashMap<>();
    }

    /**
     * Gets all tasks as a collection.
     *
     * @return the collection of tasks
     */
    public Map<String, Task> getTasks() {
        return this.taskLookup;
    }

    /**
     * Adds a task if no task with the same title exists.
     *
     * @param task a Task object
     */
    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Invalid Task");
        }
        if (this.taskLookup.containsKey(task.getTaskTitle())) {
            throw new IllegalArgumentException("A task with the same title already exists.");
        }
        this.taskLookup.put(task.getTaskTitle(), task);
    }

    /**
     * Removes a task by its title.
     *
     * @param title the title of the task to remove
     * @return the removed task, or null if no task was found
     */
    public Task removeTask(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Invalid Title");
        }
        return this.taskLookup.remove(title);
    }

    /**
     * Gets a task by its title.
     *
     * @param title the title of the task to retrieve
     * @return the Task object, or null if no task was found
     */
    public Task getTask(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Invalid Title");
        }
        return this.taskLookup.get(title);
    }
}

