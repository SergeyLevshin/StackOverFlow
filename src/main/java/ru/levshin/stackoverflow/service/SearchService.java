package ru.levshin.stackoverflow.service;

import java.util.List;

/**
 * Service to find some resources
 * @param <T> resource type
 */
public interface SearchService<T> {

    /**
     * Search by parameter
     * @param param parameter of search
     * @return found objects
     */
    List<T> getItems(String param);
}
