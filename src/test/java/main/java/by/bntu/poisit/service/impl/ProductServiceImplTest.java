
package main.java.by.bntu.poisit.service.impl;

import java.math.BigDecimal;
import java.util.List;
import main.java.by.bntu.poisit.entity.Category;
import main.java.by.bntu.poisit.entity.Producer;
import main.java.by.bntu.poisit.entity.Product;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

public class ProductServiceImplTest {

    @Mock
    ServiceManager service;

    private final Integer ID_PRODUCT = 123;
    private final String PRODUCT_NAME= "Apple";
    private final String DESCRIPTION = "very good choice";
    private final String IMAGE_LINK = "/media/honor.jpg";
    private final BigDecimal PRICE = new BigDecimal(1233);
    private final BigDecimal PRICE_NOT_VALID = new BigDecimal(1233321322);
    private final String CATEGORY = "Smartphone";
    private final String PRODUCER = "Sony";
    
    private final String CATEGORY_URL = "/smartphone";
    private final Integer CURRENT_CATEGORY = 1;
    
    private final Integer CURRENT_AMOUNT_OF_PRODUCTS = 675;
    private final Integer CURRENT_AMOUNT_OF_PRODUCTS_BY_CATEGORY_SMARTPHONE = 219;
    
    
    public ProductServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        service = mock(ServiceManager.class);
    }

    @Test
    public void testCallListAllProductsThenResultIsNotNull() {
        int limit = anyInt();
        int offset = anyInt();
        List<Product> productsList = service.getProductService().listAllProducts(limit, offset);
        when(service.getProductService().listAllProducts(limit, offset)).thenReturn(productsList);
        assertNotNull("ProductsList is Empty " + productsList);

    }

    @Test
    public void testCallListAllProductsThenResultIsNull() {
        int limit = 0;
        int offset = 0;
        List<Product> productsList = service.getProductService().listAllProducts(limit, offset);
        when(service.getProductService().listAllProducts(limit, offset)).thenReturn(productsList);
        assertNull("ProductsList is Not Empty " + productsList);

    }

    @Test
    public void testCallSelectIdFromExistNameOfCategoryThenFindIdCategory() {
        Integer idCategory = service.getProductService().selectIdFromNameOfCategory("E-book");
        Integer expIdCategory = CURRENT_CATEGORY;

        assertEquals(expIdCategory, idCategory);

    }

    @Test
    public void testCallSelectIdFromNotExistNameOfCategoryThenNotFindIdCategory() {
        Integer idCategory = service.getProductService().selectIdFromNameOfCategory("AbcCategory");
        Integer expIdCategory = CURRENT_CATEGORY;

        assertNotEquals(expIdCategory, idCategory);

    }

    public void testAddProductToSystemThenCheckInsertedIdProductIsPresent(Product pr, Integer idCategory, Integer idProducer) {
        int expectedId = 123;
        int limit = anyInt();
        int offset = anyInt();
        Product product = new Product(ID_PRODUCT,PRODUCT_NAME,DESCRIPTION ,IMAGE_LINK,PRICE, CATEGORY, PRODUCER);
        service.getProductService().addProductToSystem(product, 1, 1);

        int productId = service.getProductService().findIdFromProduct(idProducer);

        List<Product> productsList = service.getProductService().listAllProducts(limit, offset);

        assertTrue(productsList.contains(product));

        assertEquals(expectedId, productId);
    }

    @Test
    public void testCallListProductsByCategoryThenListCategoriesIsPresentInSystem() {
        int limit = anyInt();
        int page = anyInt();
        int offset = (page - 1) * limit;
        List<Product> listProductsByCategory = service.getProductService().listProductsByCategory(CATEGORY_URL, limit, page);
        when(service.getProductService().listProductsByCategory(CATEGORY_URL, limit, page)).thenReturn(listProductsByCategory);
        List<Product> productsList = service.getProductService().listAllProducts(limit, offset);
        when(service.getProductService().listAllProducts(limit, offset)).thenReturn(productsList);
        assertTrue("ProductsList don't contain listProductsByCategory", productsList.contains(listProductsByCategory));
        assertNotNull("ListProductsByCategory is Null" + listProductsByCategory);
    }

    @Test
    public void testCallListAllCategoriesThenListOfCategoriesIsNotNull() {
        List<Category> listOfCategories = service.getProductService().listAllCategories();
        when(service.getProductService().listAllCategories()).thenReturn(listOfCategories);
        assertNotNull("ListOfCategories is Empty " + listOfCategories);
    }

    @Test
    public void testCallListAllProducersThenListOfProducersIsNotNull() {
        List<Producer> listOfProducers = service.getProductService().listAllProducers();
        when(service.getProductService().listAllProducers()).thenReturn(listOfProducers);
        assertNotNull("ListOfProducers is Empty " + listOfProducers);
    }

    @Test
    public void testCallCountAllProductsThenCountIsCorrect() {
        int countOfProductsInSystem = service.getProductService().countAllProducts();
        when(service.getProductService().countAllProducts()).thenReturn(countOfProductsInSystem);
        int expResult = CURRENT_AMOUNT_OF_PRODUCTS;
        assertEquals(expResult, countOfProductsInSystem);
    }

    @Test
    public void testCallCountProductsByCategoryThenCountIsCorrect() {
        int countOfProductsByCategoryInSystem = service.getProductService().countProductsByCategory(CATEGORY_URL);
        when(service.getProductService().countProductsByCategory(CATEGORY_URL)).thenReturn(countOfProductsByCategoryInSystem);
        int expResult = CURRENT_AMOUNT_OF_PRODUCTS_BY_CATEGORY_SMARTPHONE;
        assertEquals(expResult, countOfProductsByCategoryInSystem);
    }

}
