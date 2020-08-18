package br.inf.hu3diger.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@GetMapping()
	public List<Book> search(@RequestParam(value = "search", defaultValue = "peaky blinders") String param) {
		List<Book> listSearchedBooks = BookService.search(param);
		return listSearchedBooks;
	}
	
	@GetMapping("/all")
	public String loadAll() {
		return String.format("All books that we have favored");
	}
	
	@PostMapping("/favor")
	public Book favor(@RequestBody Book book) {
		BookService service = new BookService();
		return service.save(book);
	}
	
	@GetMapping("/{id}/delete")
	public String disfavor(@PathVariable int id) {
		return String.format("We will disfavor the book with id " + id + "<br> Remember to change to DELETE");
	}

}
