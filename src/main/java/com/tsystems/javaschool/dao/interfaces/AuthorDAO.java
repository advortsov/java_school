package com.tsystems.javaschool.dao.interfaces;


import com.tsystems.javaschool.dao.entity.Author;

import java.math.BigDecimal;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 09.02.2016
 */
public interface AuthorDAO extends GenericDAO<Author, Long> {
    public Author findByName(String name);

}
