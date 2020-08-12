package br.inf.hu3diger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
	
	@GetMapping()
	public String search(@RequestParam(value = "search", defaultValue = "peaky blinders") String name) {
		return String.format("We will search a book about %s!", name);
	}
	
	@GetMapping("/all")
	public String loadAll() {
		return String.format("All books that we have favored");
	}
	
	@GetMapping("/{id}/favor")
	public String favor(@PathVariable int id) {
		return String.format("We will favor the book with id " + id + "<br> Remember to change to POST");
	}
	
	@GetMapping("/{id}/delete")
	public String disfavor(@PathVariable int id) {
		return String.format("We will disfavor the book with id " + id + "<br> Remember to change to DELETE");
	}

}
