package com.pengjunlee.utils;

/**
 * @author pengjunlee
 * @create 2019-09-20 17:50
 */

import com.pengjunlee.domain.SearchEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PageUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    private int total;
    private List<?> rows;

    private List<SearchEntity> searches;


    public PageUtil() {
    }

    public PageUtil(List<?> list, int total) {
        this.rows = list;
        this.total = total;
    }


    public PageUtil(List<?> list, int total, List<SearchEntity> searches) {
        this.rows = list;
        this.total = total;
        this.searches = searches;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public List<SearchEntity> getSearches() {
        return searches;
    }

    public void setSearches(List<SearchEntity> searches) {
        this.searches = searches;
    }

    public static Integer getOffsetFromParams(Map<String, Object> map) {
        int page = map.get("page") == null ? 1 : Integer.valueOf(map.get("page").toString());
        int limit = map.get("limit") == null ? 10 : Integer.valueOf(map.get("limit").toString());
        Integer offset = (page - 1) * limit;
        return offset;
    }
}
