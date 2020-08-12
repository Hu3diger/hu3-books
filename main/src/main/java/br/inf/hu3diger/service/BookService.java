package br.inf.hu3diger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inf.hu3diger.model.Book;
import br.inf.hu3diger.repository.BookRepository;

@Service
public class BookService implements IBookService{
	
	@Autowired
	private BookRepository repository;

	@Override
	public List<Book> findAll() {
		List<Book> books = (List<Book>) repository.findAll();
		return books;
	}

}
