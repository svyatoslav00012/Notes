package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Note{

//	@JsonProperty
	private int id;
	private String topic;
	private String content;

	public Note(){
		topic = "";
		content = "";
	}

	public Note(String topic, String content){
		this.topic = topic;
		this.content = content;
	}

	public Note(int id, String topic, String content){
		this(topic, content);
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString(){
		return "ID: " + id + "\nTopic:\n" + topic + "\nContent:\n" + content + "\n";
	}
}
