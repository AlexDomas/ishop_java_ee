package main.java.by.bntu.poisit.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearcherProductForm {

    private String query;
    private List<Integer> categories;
    private List<Integer> producers;

    public SearcherProductForm(String query, String[] categories, String[] producers) {
        super();
        this.query = query;
        this.categories = convertToListOfInt(categories);
        this.producers = convertToListOfInt(producers);
    }

    private List<Integer> convertToListOfInt(String[] args) {
        if (args == null) {
            return Collections.emptyList();
        } else {
            List<Integer> res = new ArrayList<>(args.length);
            for (String arg : args) {
                res.add(Integer.parseInt(arg));
            }
            return res;
        }

    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public List<Integer> getProducers() {
        return producers;
    }

    public void setProducers(List<Integer> producers) {
        this.producers = producers;
    }

    public boolean isCategoriesNotEmpty(){
        return !categories.isEmpty();
        
    }
    
    public boolean isProducersNotEmpty(){
        return !producers.isEmpty();
        
    }
}
