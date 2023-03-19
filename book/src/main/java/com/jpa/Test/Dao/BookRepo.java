package com.jpa.Test.Dao;

import org.springframework.data.repository.CrudRepository;

import com.jpa.Test.entities.Book;

public interface BookRepo extends CrudRepository<Book, Integer>{

	public Book findById(int id); 
}
