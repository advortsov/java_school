package com.tsystems.javaschool.dao.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 09.02.2016
 *
 * Pojo
 */

@Entity
@Table(name="book")
@NamedQuery(name = "Book.getAll", query = "SELECT b from Book b")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "source_pkey")
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="page_count")
    private int pageCount;
    @Column(name="isbn")
    private String isbn;
    @Column(name="publish_year")
    private int publishYear;
    @Column(name="image")
    private byte[] image;
    @Column(name="descr")
    private String descr;

//
//    @Entity
//    public class Troop {
//        @OneToMany(mappedBy="troop")
//        public Set<Soldier> getSoldiers() {
//            ...
//        }
//
//        @Entity
//        public class Soldier {
//            @ManyToOne
//            @JoinColumn(name="troop_fk")
//            public Troop getTroop() {
//                ...
//            }
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="author_id") // this column inda book table
    private Author author;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="genre_id") // this column inda book table
    private Genre genre;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="publisher_id") // this column inda book table
    private Publisher publisher;

    @Column(name="quantity")
    private int quantity;

    @Column(name="price")
    private int price;

    //private List<OrderLine> orderLinesById;

    public Book() {
    }

    public Book(String name, int pageCount, String isbn, int publishYear, byte[] image,
                String descr, Author author, Genre genre, Publisher publisher, int quantity, int price) {
        this.name = name;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.publishYear = publishYear;
        this.image = image;
        this.descr = descr;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.quantity = quantity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pageCount=" + pageCount +
                ", isbn='" + isbn + '\'' +
                ", publishYear=" + publishYear +
                ", image=" + Arrays.toString(image) +
                ", descr='" + descr + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", publisher=" + publisher +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
