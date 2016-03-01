package com.tsystems.javaschool.dao.interfaces;


import com.tsystems.javaschool.dao.entity.Author;

import javax.persistence.EntityManager;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 09.02.2016
 */
public interface AuthorDAO extends GenericDAO<Author, Long> {
    Author findByName(String name);

    void setNullBeforeDelete(Author author, EntityManager em);
}
