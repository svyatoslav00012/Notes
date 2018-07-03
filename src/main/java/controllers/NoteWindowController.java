package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Note;

import java.io.IOException;

public class NoteWindowController {

	private Note note;

	@FXML
	private TextField topicField;
	@FXML
	private TextArea contentArea;

	public void initialize() {
		if (note != null) {
			topicField.setText(note.getTopic());
			contentArea.setText(note.getContent());
		}
	}

	public void save(ActionEvent actionEvent) {
		if (note == null)
			note = new Note();
		note.setTopic(topicField.getText());
		note.setContent(contentArea.getText());
		((Stage) topicField.getScene().getWindow()).close();
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
		if(note != null) {
			topicField.setText(note.getTopic());
			contentArea.setText(note.getContent());
		}
	}

	public static Note createNote() {
		return editNote(null);
	}

	public static Note editNote(Note note) {
		Stage stage = new Stage();
		stage.setResizable(false);
		try {
			FXMLLoader loader = new FXMLLoader(
					NoteWindowController.class.getResource("/view/noteWindow.fxml")
			);
			stage.setScene(new Scene(loader.load()));
			NoteWindowController c = loader.getController();
			c.setNote(note);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setOnCloseRequest(cl -> {
				c.setNote(null);
			});
			stage.showAndWait();
			return c.getNote();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
