package edu.westga.cs1302.project3.view;

import java.io.File;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskList;
import edu.westga.cs1302.project3.model.TaskManager;
import edu.westga.cs1302.project3.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/** Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {

	@FXML private ListView<Task> taskListView;
    @FXML private MenuItem loadMenuItem;
    @FXML private MenuItem saveMenuItem;
	
    private ViewModel vm;
    private TaskManager taskManager;
	
	@FXML
	void initialize() {
		this.vm = new ViewModel();
		this.taskManager = new TaskManager();
		this.taskListView.setItems(this.vm.getTaskList());
		this.loadMenuItem.setOnAction((event)-> {
			this.loadTask();
		});
		this.saveMenuItem.setOnAction((event)-> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choose task file");
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("Text files", "*.txt", "*.pdf"),
					new ExtensionFilter("All files", "*."));
			File selectedFile = fileChooser.showOpenDialog(null);
			if (selectedFile != null) {
				try {
					TaskList taskList = new TaskList();
					for (Task task : this.taskListView.getItems()) {
		                taskList.addTask(task);
		            }
					this.taskManager.saveTaskList(taskList, selectedFile.getAbsolutePath());
				}	 catch (IllegalArgumentException error) {
				    Alert alert = new Alert(Alert.AlertType.ERROR);
				    alert.setContentText("File cannot be overwritten: " + error.getMessage());
				    alert.show();
				} catch (Exception error) {
				    Alert alert = new Alert(Alert.AlertType.ERROR);
				    alert.setContentText("Failed to save tasks: " + error.getMessage());
				    alert.show();
				}
			}
			
		});
	}

	private void loadTask() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open task file");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text files", "*.txt", "*.pdf"),
				new ExtensionFilter("All Files", "*.*"));
File selectedFile = fileChooser.showOpenDialog(null);
if (selectedFile != null) {
		try {
		    TaskList loadedTasks = this.taskManager.loadTasks(selectedFile.getAbsolutePath());
		    this.vm.setTaskList(loadedTasks.getTasks()); 
		} catch (IllegalArgumentException error) {
		    Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setContentText("Invalid file format: " + error.getMessage());
		    alert.show();
		} catch (Exception error) {
		    Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setContentText("Failed to load tasks: " + error.getMessage());
		    alert.show();
		}
}
	}

}
