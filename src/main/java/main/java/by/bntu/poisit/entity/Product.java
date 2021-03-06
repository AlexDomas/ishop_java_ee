package main.java.by.bntu.poisit.entity;

import java.math.BigDecimal;

public class Product extends AbstractEntity<Integer> {

    private Integer id;
    private String name;
    private String description;
    private String imageLink;
    private BigDecimal price;
    private String category;
    private String producer;

    public Product() {

    }
    
    public Product(int id) {
        this.id = id;
    }

    public Product(int id, String name, String description, String imageLink, BigDecimal price, String category, String producer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageLink = imageLink;
        this.price = price;
        this.category = category;
        this.producer = producer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public String toString() {
        return String.format("Product [id=%s, name=%s, description=%s, imageLink=%s, price=%s, category=%s, producer=%s]", getId(), name, description, imageLink, price, category, producer);
    }
}
