package com.api.book.bootrestbook.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.bootrestbook.dao.BookRepository;
import com.api.book.bootrestbook.entities.Book;

// @Component
@Service // Specialization of Component annotation
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;

    // private static List<Book> books = new ArrayList<Book>();

    // static {
    //     books.add(new Book(10012, "IKIGAI", "HECTOR GRACIA"));
    //     books.add(new Book(10140, "Tuesday with Morrie", "Mitch Albom"));
    //     books.add(new Book(10490, "The Psychology of Money", "Morgan Housel"));
    // }

    //get all books
    public List<Book> getAllBooks() {
        List<Book> list = (List<Book>) bookRepository.findAll();
        return list;
        // return books;
    }

    //get single book by id
    public Book getBookById(int id) {
        Book book = null;
        try {
            // book =  books.stream().filter(b -> b.getId() == id).findFirst().get();
            book = bookRepository.findById(id);
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return book;
    }

    //adding a book
    public Book addBook(Book book) {
        Book res = bookRepository.save(book);
        return res;
        // books.add(book);
        // return book;
    }

    //removing book
    public void removeBook(int id) {
        // books = books.stream().filter(b -> b.getId() != id).collect(Collectors.toList());
        bookRepository.deleteById(id);
    }

    //update book
    public void updateBook(Book book, int id) {
        // try {
        //     books.stream().map(b -> { 
        //         if (b.getId() == id) {
        //             b.setName(book.getName());
        //             b.setAuthor(book.getAuthor());
        //         }
        //         return b; 
        //     }).collect(Collectors.toList());
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        book.setId(id);
        bookRepository.save(book);
        
    }

}
