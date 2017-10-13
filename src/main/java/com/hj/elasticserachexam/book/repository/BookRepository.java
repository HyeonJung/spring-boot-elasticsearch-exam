package com.hj.elasticserachexam.book.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.hj.elasticserachexam.book.model.Book;

public interface BookRepository extends ElasticsearchRepository<Book, String> {
	
	Page<Book> findByAuthor(String author, Pageable pageable);
	
	List<Book> findByTitle(String title);

}
