package br.inf.hu3diger.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import br.inf.hu3diger.model.Book;
import br.inf.hu3diger.model.Book.BookModel;
import br.inf.hu3diger.repository.BookRepository;

@Service
public class BookService implements IBookService{
	
	private static final String GOOGLE_BOOKS_URL = "https://www.googleapis.com/books/v1/volumes";
	
	@Autowired
	private BookRepository repository;

	@Override
	public List<Book> findAll() {
		List<Book> books = (List<Book>) repository.findAll();
		return books;
	}
	
	public static List<Book> search(String param){
		List<Book> books = new ArrayList<Book>();
		try {
			return processRequest("?q=" + param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}
	
	protected static List<Book> processRequest(String path) throws Exception {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		
		String strResponse = "";
		Client client = Client.create(new DefaultClientConfig());
		WebResource webResource = client.resource(GOOGLE_BOOKS_URL + path.replace(" ", "%20"));
		ClientResponse doGet = webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		strResponse = doGet.getEntity(String.class);
		
		BookModel readValues = new ObjectMapper().readValue(strResponse, BookModel.class);
		return readValues.getBooks();
	}
	
	

}
