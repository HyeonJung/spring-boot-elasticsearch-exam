package com.hj.elasticserachexam;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.hj.elasticserachexam.book.model.Book;
import com.hj.elasticserachexam.book.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ElasticsearchTemplate esTemplate;
	
	
	@Before
	public void before() {
		esTemplate.deleteIndex(Book.class);
		esTemplate.createIndex(Book.class);
		esTemplate.putMapping(Book.class);
		esTemplate.refresh(Book.class);
	}
	
	@Test
	public void testSave() {
		
		Book book = new Book("1001","Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
		Book testBook = bookService.save(book);
		
		assertNotNull(testBook.getId());
		assertEquals(testBook.getTitle(), book.getTitle());
		assertEquals(testBook.getAuthor(), book.getAuthor());
		assertEquals(testBook.getReleaseDate(), book.getReleaseDate());
		
		
	}
	
	@Test
	public void testFindByAuthor() {
		
		List<Book> bookList = new ArrayList<>();
		
		bookList.add(new Book("1001","Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
		bookList.add(new Book("1002", "Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017"));
	    bookList.add(new Book("1003", "Apache Solr Basics", "Rambabu Posa", "21-MAR-2017"));
	    bookList.add(new Book("1007", "Spring Data + ElasticSearch", "Rambabu Posa", "01-APR-2017"));
	    bookList.add(new Book("1008", "Spring Boot + MongoDB", "Mkyong", "25-FEB-2017"));
	    
	    for (Book book : bookList) {
	    	bookService.save(book);
	    }
	    
	    Page<Book> byAuthor = bookService.findByAuthor("Rambabu Posa",  new PageRequest(0,10));
	    assertThat(byAuthor.getTotalElements(), is(4L));
	    
	    Page<Book> byAuthore2 = bookService.findByAuthor("Mkyoun",new PageRequest(0,10));
	    assertThat(byAuthore2.getTotalElements(), is(1L));
		
		
	}
	
	@Test
	public void testDelete() {
		
		Book book = new Book("1001","Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
		bookService.save(book);
		bookService.delete(book);;
		
		Book testBook = bookService.findOne(book.getId());
		assertNull(testBook);
	}
	

}
