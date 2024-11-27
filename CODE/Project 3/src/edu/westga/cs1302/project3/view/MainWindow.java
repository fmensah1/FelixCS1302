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
	
    private ViewModel vm;
    private TaskManager taskManager;
	
	@FXML
	void initialize() {
		this.vm = new ViewModel();
		this.taskManager = new TaskManager();
		this.taskListView.setItems(this.vm.getTaskList());
		this.loadMenuItem.setOnAction((event)-> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open task file");
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("Text files", "*.txt"),
					new ExtensionFilter("All Files", "*,*"));
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			try {
	            // Call loadTasks and pass the selected file's path
	            TaskList loadedTasks = this.taskManager.loadTasks(selectedFile.getAbsolutePath());

	            // Update your TaskList or UI elements here
	            this.vm.setTaskList(loadedTasks.getTasks()); // Assuming ViewModel has setTaskList method
	        } catch (Exception e) {
	            // Handle any exceptions (e.g., invalid file format)
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setContentText("Failed to load tasks: " + e.getMessage());
	            alert.show();
	        }
		}
			
		});
	}

}
