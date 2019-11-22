package com.pengjunlee.utils;

/**
 * @author pengjunlee
 * @create 2019-09-20 17:50
 */

import com.pengjunlee.domain.SearchEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class EntityUtil<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private T row;

    private List<SearchEntity> searches;


    public EntityUtil() {
    }

    public EntityUtil(T row) {
        this.row = row;
    }

    public EntityUtil(T row, List<SearchEntity> searches) {
        this.row = row;
        this.searches = searches;
    }

    public T getRow() {
        return row;
    }

    public void setRow(T row) {
        this.row = row;
    }

    public List<SearchEntity> getSearches() {
        return searches;
    }

    public void setSearches(List<SearchEntity> searches) {
        this.searches = searches;
    }
}
