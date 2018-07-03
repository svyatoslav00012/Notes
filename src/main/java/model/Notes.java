package model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpHeaders.USER_AGENT;

public class Notes {

	public ArrayList<Note> notes = new ArrayList<>();
	File data = new File("data.xml");

	public Notes() {
		read();
	}

	private void read() {
		try {
			String url = "http://localhost:8080/notes";

			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);

			// add request header
			request.addHeader("User-Agent", USER_AGENT);
			HttpResponse response = client.execute(request);

			BufferedReader rd = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

			ObjectMapper mapper = new ObjectMapper();
			notes = mapper.readValue(result.toString(), new TypeReference<List<Note>>() {
			});

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void add(Note n) {
		try {
			HttpClient httpclient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost("http://localhost:8080/note");

// Request parameters and other properties.
			List<NameValuePair> params = new ArrayList<NameValuePair>(2);
			params.add(new BasicNameValuePair("topic", n.getTopic()));
			params.add(new BasicNameValuePair("content", n.getContent()));
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

//Execute and get the response.
			httpclient.execute(httppost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		notes.add(n);
		n.setId(notes.indexOf(n) + 1);
	}

	public void delete(Note note) {

		try {
			HttpClient httpclient = HttpClients.createDefault();
			HttpDelete httpDelete = new HttpDelete("http://localhost:8080/note/" + note.getId());
			httpclient.execute(httpDelete);
		} catch (Exception e) {
			e.printStackTrace();
		}

		notes.remove(note);
	}

	public void update(Note note) {
//		for(Note n : notes)
//			if(n.getId() == note.getId()){
//				n.setTopic(note.getTopic());
//				n.setContent(note.getContent());
//			}
		try {
			HttpClient client = HttpClients.createDefault();
			HttpPut put = new HttpPut("http://localhost:8080/note/" + note.getId());
			put.setEntity(
					EntityBuilder.create().setParameters(
							new BasicNameValuePair("topic", note.getTopic()),
							new BasicNameValuePair("content", note.getContent())
					).build()
			);
			client.execute(put);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
