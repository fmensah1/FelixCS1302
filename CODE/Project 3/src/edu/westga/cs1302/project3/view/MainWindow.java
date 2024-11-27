package edu.westga.cs1302.project3.view;

import edu.westga.cs1302.project3.model.Task; 
import edu.westga.cs1302.project3.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/** Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {

	@FXML
    private ListView<Task> taskListView;
	
	private ViewModel vm;
	
	@FXML
	void initialize() {
		this.vm = new ViewModel();
		this.taskListView.setItems(this.vm.getTaskList());
	}

}
