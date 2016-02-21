package com.tsystems.javaschool.services.interfaces;

import com.tsystems.javaschool.dao.entity.Genre;
import com.tsystems.javaschool.dao.entity.Publisher;

import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 11.02.2016
 */
public interface PublisherManager {
    public Publisher findByPublisherName(String name);

    public List<Publisher> loadAllPublishers();

    public void saveNewPublisher(Publisher publisher);

    public Publisher findPublisherById(long id);

    public void deletePublisher(Publisher publisher);

    public void updatePublisher(Publisher publisher);
}
