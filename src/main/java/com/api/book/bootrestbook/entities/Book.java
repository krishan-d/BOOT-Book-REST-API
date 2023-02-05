package com.api.book.bootrestbook.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// import org.hibernate.dialect.SQLServer2016Dialect;

@Entity
@Table(name="books")
// @Setter
// @Getter
// @NoArgsConstructor
// @AllArgsConstructor
// @ToString
public class Book {

    // SQLServer2016Dialect
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "book_id")
    private int id;
    private String name;


    // @OneToOne(cascade = CascadeType.ALL)
    @ManyToOne(cascade = CascadeType.MERGE)
    private Author author;
    
    @Override
    public String toString() {
        return "Book [id=" + id + ", name=" + name + ", author=" + author + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book() {
    }

    public Book(int id, String name, Author author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }


}
