package com.cms.model.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法描述: DataTables参数
 * author 小刘同学、
 * version v1.0
 * date 2018/5/7 16:30
 */
public class Columns {

    private Integer draw;


    private Integer start;


    private Integer length;


    private Map<Search, String> search = new HashMap<Search, String>();


    private List<Map<Order, String>> order = new ArrayList<Map<Order, String>>();


    private List<Map<Column, String>> columns = new ArrayList<Map<Column, String>>();


    public Columns() {


    }


    public enum Search {
        value, regex
    }


    public enum Order {
        column, dir
    }


    public enum Column {
        data, name, searchable, orderable, searchValue, searchRegex
    }


    public Integer getDraw() {
        return draw;
    }


    public void setDraw(Integer draw) {
        this.draw = draw;
    }


    public Integer getStart() {
        return start;
    }


    public void setStart(Integer start) {
        this.start = start;
    }


    public Integer getLength() {
        return length;
    }


    public void setLength(Integer length) {
        this.length = length;
    }


    public Map<Search, String> getSearch() {
        return search;
    }


    public void setSearch(Map<Search, String> search) {
        this.search = search;
    }


    public List<Map<Order, String>> getOrder() {
        return order;
    }


    public void setOrder(List<Map<Order, String>> order) {
        this.order = order;
    }


    public List<Map<Column, String>> getColumns() {
        return columns;
    }


    public void setColumns(List<Map<Column, String>> columns) {
        this.columns = columns;
    }

}

