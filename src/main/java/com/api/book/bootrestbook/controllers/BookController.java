package com.api.book.bootrestbook.controllers;

import java.util.List;

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

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookService;


// @Controller
@RestController /*RestController is used for REST APIs */
public class BookController {

    @Autowired
    private BookService bookService;
    
    // @RequestMapping(value="/books", method = RequestMethod.GET)
    // GetMapping is basically combined RequestMapping and GET method

    //get all books
    @GetMapping("/books")
    // @ResponseBody
    /*
    ResponseBody is used to return same string as response (no html)
    In case of RESTController, no ResponseBody is required
    */
    
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> list = bookService.getAllBooks();
        if (list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // return ResponseEntity.of(Optional.of(list));

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    /*
    public Book getBooks() {

        Book book = new Book();
        book.setId(10074);
        book.setName("The Alchemist");
        book.setAuthor("Panlo Coelho");

        return book;
        // SpringBoot automatically changes book object into JSON because of JACKSON dependency

        // return "This is book 1";
    }
    */


    //get single book handler
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
        Book b = bookService.getBookById(id);
        if (b == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } 
        return ResponseEntity.status(HttpStatus.OK).body(b);
    }

    //new book handler
    @PostMapping("/book")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book b = null;
        try {
            b = bookService.addBook(book);
            System.out.println(b);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //remove book handler
    @DeleteMapping(value="/books/{id}")
    public ResponseEntity<Void> removeBook(@PathVariable("id") int id) {
        try {
            bookService.removeBook(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    //update book handler
    @PutMapping(value="books/{id}")
    public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable("id") int id) {
        try {
            bookService.updateBook(book, id);
            return ResponseEntity.status(HttpStatus.OK).body(book);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
