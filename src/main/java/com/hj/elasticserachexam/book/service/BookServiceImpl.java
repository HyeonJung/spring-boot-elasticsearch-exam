package com.hj.elasticserachexam.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hj.elasticserachexam.book.model.Book;
import com.hj.elasticserachexam.book.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	private BookRepository bookRepository;
	
	
	@Autowired
	public void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@Override
	public Book save(Book book) {
		// TODO Auto-generated method stub
		return bookRepository.save(book);
	}

	@Override
	public void delete(Book book) {
		// TODO Auto-generated method stub
		bookRepository.delete(book);
	}

	@Override
	public Book findOne(String id) {
		// TODO Auto-generated method stub
		return bookRepository.findOne(id);
	}

	@Override
	public Iterable<Book> findAll() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

	@Override
	public Page<Book> findByAuthor(String author, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return bookRepository.findByAuthor(author, pageRequest);
	}

	@Override
	public List<Book> findByTitle(String title) {
		// TODO Auto-generated method stub
		return bookRepository.findByTitle(title);
	}

}
