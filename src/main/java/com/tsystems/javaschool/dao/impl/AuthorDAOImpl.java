package com.tsystems.javaschool.dao.impl;


import com.tsystems.javaschool.dao.entity.Author;
import com.tsystems.javaschool.dao.interfaces.AuthorDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 09.02.2016
 */
public class AuthorDAOImpl extends GenericDAOImpl<Author, Long> implements AuthorDAO {

    @Override
    public Author findByName(String fio) {
        Author author = null;
        String sql = "SELECT a FROM Author a WHERE a.fio = :fio";
        Query query = JpaUtil.getEntityManager().createQuery(sql).
                setParameter("fio", fio);
        author = findOne(query);
        return author;
    }
}
