package com.jpa.Test.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.Test.entities.Book;
import com.jpa.Test.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	// add all books handler 
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> list = bookService.getAllBook();
		if(list.size()<=0)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	
	}	
	// single book handler 
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id")int id) {
		Book book = bookService.getsinglBook(id);
		if(book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else {
			
			return ResponseEntity.of(Optional.of(book));

		}
	}

	//add data 
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		Book b = null;
		try {
			b = this.bookService.addbook(book);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	//delete mapping
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<Void>  deleteRequest(@PathVariable("bookId") int bookId) {
		try {
			
			this.bookService.DeleteBook(bookId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	//Update bookUpdate handler 
	
	@PutMapping("/books/{bookId}")
	public Book updateBook(@PathVariable("bookId") int bookId , @RequestBody Book book)
	{
		
		this.bookService.updatebook(book , bookId);
		return book;
		
	}	
}	
