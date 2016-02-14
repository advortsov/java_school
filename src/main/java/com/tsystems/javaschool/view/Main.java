package com.tsystems.javaschool.view;


import com.tsystems.javaschool.dao.entity.Author;
import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.entity.Genre;
import com.tsystems.javaschool.dao.entity.Publisher;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.impl.AuthorManagerImpl;
import com.tsystems.javaschool.services.impl.BookManagerImpl;
import com.tsystems.javaschool.services.impl.GenreManagerImpl;
import com.tsystems.javaschool.services.impl.PublisherManagerImpl;
import com.tsystems.javaschool.services.interfaces.AuthorManager;
import com.tsystems.javaschool.services.interfaces.BookManager;
import com.tsystems.javaschool.services.interfaces.GenreManager;
import com.tsystems.javaschool.services.interfaces.PublisherManager;

import java.util.Date;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 03.02.2016
 */
public class Main {
    public static void main(String[] args) {

        GenreManager genreManager = new GenreManagerImpl();

//        genreManager.saveNewGenre(new Genre("Algs"));
//        genreManager.saveNewGenre(new Genre("Maths"));
//        genreManager.saveNewGenre(new Genre("Development"));
//        genreManager.saveNewGenre(new Genre("Architecture"));
//        genreManager.saveNewGenre(new Genre("Patterns"));

        AuthorManager authorManager = new AuthorManagerImpl();

//        authorManager.saveNewAuthor(new Author("Laffore"));
//        authorManager.saveNewAuthor(new Author("Schildt"));
//        authorManager.saveNewAuthor(new Author("Ekkel"));
//        authorManager.saveNewAuthor(new Author("Straustrup"));
//        authorManager.saveNewAuthor(new Author("Sierra"));

        PublisherManager publisherManager = new PublisherManagerImpl();

//        publisherManager.saveNewPublisher(new Publisher("PITER"));
//        publisherManager.saveNewPublisher(new Publisher("EKSMO"));
//        publisherManager.saveNewPublisher(new Publisher("DROFA"));
//        publisherManager.saveNewPublisher(new Publisher("LITER"));


                BookManager bookManager = new BookManagerImpl();

        byte[] img = {4, 3, 5};

//        bookManager.saveNewBook(new Book("Java", 325, "ISBN-434352", 2013, img,
//                "descr", authorManager.findByAuthorName("Laffore"), genreManager.findByGenreName("Maths"),
//                publisherManager.findByPublisherName("PITER"), 3, 2184));
//
//        bookManager.saveNewBook(new Book("Maven", 854, "ISBN-434552", 2013, img,
//                "descr", authorManager.findByAuthorName("Schildt"), genreManager.findByGenreName("Development"),
//                publisherManager.findByPublisherName("EKSMO"), 73, 1658));
//
//        bookManager.saveNewBook(new Book("JSP", 234, "ISBN-4340352", 2013, img,
//                "descr", authorManager.findByAuthorName("Ekkel"), genreManager.findByGenreName("Algs"),
//                publisherManager.findByPublisherName("DROFA"), 43, 2543));
//
//        bookManager.saveNewBook(new Book("JSF", 196, "ISBN-4341352", 2013, img,
//                "descr", authorManager.findByAuthorName("Straustrup"), genreManager.findByGenreName("Architecture"),
//                publisherManager.findByPublisherName("EKSMO"), 43, 874));
//
//        bookManager.saveNewBook(new Book("JPA", 290, "ISBN-4343252", 2013, img,
//                "descr", authorManager.findByAuthorName("Schildt"), genreManager.findByGenreName("Patterns"),
//                publisherManager.findByPublisherName("DROFA"), 43, 923));
//
//        bookManager.saveNewBook(new Book("Hibernate", 596, "ISBN-4344352", 2013, img,
//                "descr", authorManager.findByAuthorName("Laffore"), genreManager.findByGenreName("Maths"),
//                publisherManager.findByPublisherName("LITER"), 43, 243));
//
//        bookManager.saveNewBook(new Book("JavaScript", 365, "ISBN-4134352", 2013, img,
//                "descr", authorManager.findByAuthorName("Ekkel"), genreManager.findByGenreName("Development"),
//                publisherManager.findByPublisherName("PITER"), 43, 756));
//
//        bookManager.saveNewBook(new Book("HTML/CSS вёрстка", 127, "ISBN-4352", 2013, img,
//                "descr", authorManager.findByAuthorName("Straustrup"), genreManager.findByGenreName("Architecture"),
//                publisherManager.findByPublisherName("LITER"), 43, 654));

        System.out.println(bookManager.loadAllBooks());
        System.out.println(genreManager.loadAllGenres());
        System.out.println(authorManager.loadAllAuthors());
        System.out.println(publisherManager.loadAllPublishers());

        JpaUtil.closeSession();
    }

}
