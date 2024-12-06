package edu.westga.cs1302.project3.view;

import java.io.File;
import java.io.IOException;

import edu.westga.cs1302.project3.Main;
import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskList;
import edu.westga.cs1302.project3.model.TaskManager;
import edu.westga.cs1302.project3.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {

	@FXML
	private ListView<Task> taskListView;
	@FXML
	private MenuItem loadMenuItem;
	@FXML
	private MenuItem saveMenuItem;
	@FXML
	private Button addTaskButton;
	@FXML
	private Button removeTask;
	@FXML
	private AnchorPane guiPane;

	private ViewModel vm;
	private TaskManager taskManager;

	@FXML
	void initialize() {
		this.vm = new ViewModel();
		this.taskManager = new TaskManager();
		this.taskListView.setItems(this.vm.getTaskList());
		
		this.loadMenuItem.setOnAction((event) -> {
			this.loadTask();
		});
		this.saveMenuItem.setOnAction((event) -> {
			this.saveTasks();
		});
		this.addTaskButton.setOnAction((event) -> {
			this.openAddTaskWindow();
		});
		this.removeTask.setOnAction((event) -> {
			Task selectedTask = this.taskListView.getSelectionModel().getSelectedItem();
			if (selectedTask != null) {
				this.vm.removeTask(selectedTask);
			} else {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setContentText("No task selected to remove.");
				alert.show();
			}
		});

	}

	private void openAddTaskWindow() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(Main.ADD_TASK_WINDOW));
		try {
			loader.load();
			Parent parent = loader.getRoot();
			Scene scene = new Scene(parent);
			Stage setTaskWindowStage = new Stage();
			setTaskWindowStage.setTitle(Main.ADD_TASK_WINDOW_TITLE);
			setTaskWindowStage.setScene(scene);
			setTaskWindowStage.initModality(Modality.APPLICATION_MODAL);
			AddTaskWindow addTaskWindow = (AddTaskWindow) loader.getController();
			addTaskWindow.bindToVM(this.vm);
			setTaskWindowStage.showAndWait();
		} catch (IOException error) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Unable to add task window.");
			alert.showAndWait();
		}
	}

	private void saveTasks() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose task file");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text files", "*.txt"),
				new ExtensionFilter("All files", "*."));
		Stage stage = (Stage) this.guiPane.getScene().getWindow();
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
			try {
				TaskList taskList = new TaskList();
				for (Task task : this.taskListView.getItems()) {
					taskList.addTask(task);
				}
				this.taskManager.saveTaskList(taskList, selectedFile.getAbsolutePath());
			} catch (IllegalArgumentException error) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("File cannot be overwritten: " + error.getMessage());
				alert.show();
			} catch (Exception error) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Failed to save tasks: " + error.getMessage());
				alert.show();
			}
		}
	}

	private void loadTask() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open task file");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = (Stage) this.guiPane.getScene().getWindow();
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
			try {
				TaskList loadedTasks = this.taskManager.loadTasks(selectedFile.getAbsolutePath());
				this.vm.setTaskList(loadedTasks.getTasks().values());
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
