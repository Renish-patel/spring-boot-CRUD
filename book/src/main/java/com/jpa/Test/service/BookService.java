package com.jpa.Test.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jpa.Test.Dao.*;
import com.jpa.Test.entities.Book;


@Component	
public class BookService {

	@Autowired
	private BookRepo bookRepo;

	// get all books
	public List<Book> getAllBook() {
		List<Book> list =  (List<Book>) this.bookRepo.findAll();
		return list;
	}

	// get a single book;
	public Book getsinglBook(int id) {
		Book book = null;
		try {
			book = this.bookRepo.findById(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	// add book
	public Book addbook(Book b) {
		Book result = bookRepo.save(b);
		return result;
	}

	// delete Book
	public void DeleteBook(int bid) {
		
		bookRepo.deleteById(bid);
	}

	// update book
	public void updatebook(Book book, int bookId) {

		book.setId(bookId);
		bookRepo.save(book);

	}
}
