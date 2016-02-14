package com.tsystems.javaschool.dao.interfaces;


import com.tsystems.javaschool.dao.entity.Genre;

import java.math.BigDecimal;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 08.02.2016
 */
public interface GenreDAO extends GenericDAO<Genre, Long> {
    //add some methods to GenericDAO standart methods:
    public Genre findByName(String name);

}
