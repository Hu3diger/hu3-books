package br.inf.hu3diger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.inf.hu3diger.model.Book;
import br.inf.hu3diger.service.BookService;

@RestController
@RequestMapping("/api/v1/book")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {
	
	@Autowired
	private BookService service;
	
	@GetMapping()
	public List<Book> search(@RequestParam(value = "search", defaultValue = "peaky blinders") String param) {
		List<Book> listSearchedBooks = BookService.search(param);
		return listSearchedBooks;
	}
	
	@GetMapping("/all")
	public List<Book> loadAll() {
		return service.findAll();
	}
	
	@PostMapping("/favor")
	public Book favor(@RequestBody Book book) {
		return service.save(book);
	}
	
	@DeleteMapping("/{id}/unfavor")
	public ResponseEntity<Long> disfavor(@PathVariable String id) {
		service.delete(Long.parseLong(id));
		return new ResponseEntity<Long>(HttpStatus.OK);
	}

}
