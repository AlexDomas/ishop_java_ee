
package main.java.by.bntu.poisit.service;

import java.math.BigDecimal;
import java.util.List;
import main.java.by.bntu.poisit.entity.Category;
import main.java.by.bntu.poisit.entity.Producer;
import main.java.by.bntu.poisit.entity.Product;
import main.java.by.bntu.poisit.form.SearcherProductForm;
import main.java.by.bntu.poisit.model.SocialAccount;


public interface ProductService {
    
    int countAllProducts();
    
    int countProductsByCategory(String categoryUrl);
    
    List<Product> listAllProducts(int page, int count);
    
    List<Product> listProductsByCategory(String categoryUrl, int page, int limit);
    
    List<Category> listAllCategories();
    
    List<Producer> listAllProducers();
    
    List<Product> listProductsBySearcherProductForm(SearcherProductForm sPF, int page, int limit);
    
    int countProductsBySearcherProductForm(SearcherProductForm sPF);
    
    void addProductToSystem(Product product, Integer idCategory, Integer idProducer);
    
    void deleteProductFromSystem(int idPr);
    
    int findIdFromProduct(Integer idProduct);
    
    
    int selectIdFromNameOfCategory(String nameCategory);
    int selectIdFromNameOfProducer(String nameProducer);
    
    int findIdFromProductForCheckEmpty(Integer idProduct);
    
    String findNameFromProductForCheckEmpty(String nameProduct);
    
    
   
    
}
