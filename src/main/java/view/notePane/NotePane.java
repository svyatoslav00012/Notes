package view.notePane;

import controllers.NoteWindowController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Note;
import model.Notes;

public class NotePane extends VBox {

	private Note note;

	private TextField topic;
	private TextArea content;
	private Button delete = new Button();
	private Button edit = new Button();

	public NotePane(Note note, Notes notes) {
		setSpacing(10);
		topic = new TextField(note.getTopic());
		topic.setEditable(false);
		topic.setId("topic");
		topic.setFocusTraversable(false);
		content = new TextArea(note.getContent());
		content.setEditable(false);
		content.setWrapText(true);
		content.setMinHeight(200);
		content.setId("content");
		content.setFocusTraversable(false);
		setId("pane");
		getStylesheets().add("/style/notePane.css");
		setFillWidth(true);

		delete.setPrefSize(40, 40);
		delete.setMinSize(40, 40);
		delete.setOnAction(del -> {
			if (new Alert(
					Alert.AlertType.CONFIRMATION,
					"Are you sure",
					ButtonType.YES, ButtonType.NO)
					.showAndWait()
					.get() == ButtonType.YES
					) {
				notes.delete(note);
				((VBox) getParent()).getChildren().remove(this);
			}
		});
		delete.setId("deleteButton");

		edit.setPrefSize(40, 40);
		edit.setMinSize(40, 40);
		edit.setOnAction(e -> {
			Note n = NoteWindowController.editNote(note);
			if(n == null)
				return;
			notes.update(n);
			topic.setText(n.getTopic());
			content.setText(n.getContent());
		});
		edit.setId("editButton");

		HBox b = new HBox(30, delete, edit);
		b.setPadding(new Insets(10, 10, 10, 10));
		b.setPrefHeight(100);
		getChildren().addAll(topic, content, b);
	}
}
