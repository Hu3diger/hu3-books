package br.inf.hu3diger.service;

import java.util.List;

import br.inf.hu3diger.model.Book;

public interface IBookService {
	
	List<Book> findAll();
	
}
