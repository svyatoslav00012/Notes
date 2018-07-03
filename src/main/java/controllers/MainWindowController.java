package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import model.Note;
import model.Notes;
import view.notePane.NotePane;

public class MainWindowController {

	@FXML
	private VBox notesBox;

	Notes notes = new Notes();

	public void initialize(){
		for(Note n : notes.notes)
			notesBox.getChildren().add(new NotePane(n, notes));
	}

	public void addNote(ActionEvent actionEvent) {
		Note n = NoteWindowController.createNote();
		if(n != null) {
			notesBox.getChildren().addAll(new NotePane(n, notes));
			notes.add(n);
		}
	}
}
