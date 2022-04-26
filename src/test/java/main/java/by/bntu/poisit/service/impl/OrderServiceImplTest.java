package main.java.by.bntu.poisit.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import main.java.by.bntu.poisit.entity.Account;
import main.java.by.bntu.poisit.entity.Order;
import main.java.by.bntu.poisit.entity.Product;
import main.java.by.bntu.poisit.form.ProductForm;
import main.java.by.bntu.poisit.model.CurrentAccount;
import main.java.by.bntu.poisit.model.ShoppingCart;
import main.java.by.bntu.poisit.model.ShoppingCartItem;
import main.java.by.bntu.poisit.model.SocialAccount;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

public class OrderServiceImplTest {

    @Mock
    ServiceManager service;

    @Mock
    ShoppingCart shoppingCart;

    @Mock
    ProductForm productForm;

    @Mock
    CurrentAccount currentAccount;

    public static final Integer CURRENT_ID_PRODUCT = 333;
    public static final Integer CURRENT_COUNT_OF_PRODUCT = 3;

    private final Integer ID_PRODUCT = 123;
    private final String PRODUCT_NAME = "Apple";
    private final String DESCRIPTION = "very good choice";
    private final String IMAGE_LINK = "/media/honor.jpg";
    private final BigDecimal PRICE = new BigDecimal(1233);
    private final BigDecimal PRICE_NOT_VALID = new BigDecimal(1233321322);
    private final String CATEGORY = "Smartphone";
    private final String PRODUCER = "Sony";

    private final int CURRENT_AMOUNT_OF_ORDERS = 21;

    private final int CURRENT_ORDER_ID = 1;

    private final int CURRENT_USER_ID = 1;

    private final int ORDER_DAY = 12;
    private final int ORDER_MONTH = 11;
    private final int ORDER_YEAR = 2021;
    private final int ORDER_HOUR = 15;
    private final int ORDER_MINUTE = 46;
    private final int ORDER_SECONDS = 20;

    private final String name = "Arsen";
    private final String email = "arsen@mail.ru";

    public OrderServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        service = mock(ServiceManager.class);
        shoppingCart = mock(ShoppingCart.class);
        productForm = mock(ProductForm.class);
        currentAccount = mock(CurrentAccount.class);
    }

    @Test
    public void testAddProductToShoppingCartFromGetOrderServiceThenProductListContainsProduct() {
        int limit = anyInt();
        int offset = anyInt();
        Product product = new Product(ID_PRODUCT, PRODUCT_NAME, DESCRIPTION, IMAGE_LINK, PRICE, CATEGORY, PRODUCER);
        service.getOrderService().addProductToShoppingCart(new ProductForm(CURRENT_ID_PRODUCT, CURRENT_COUNT_OF_PRODUCT), shoppingCart);
        verify(service).getOrderService().addProductToShoppingCart(productForm, shoppingCart);
        List<Product> productsList = service.getProductService().listAllProducts(limit, offset);
        when(service.getProductService().listAllProducts(limit, offset)).thenReturn(productsList);
        verify(shoppingCart, times(1)).addProduct(product, productForm.getCount());
        assertTrue("The Product is not presented in the ProductList", productsList.contains(product));
    }

    @Test
    public void testCallListAllUsersThenResultListOfUsersIsNotNull() {
        List<Account> resultListOfUsers = service.getOrderService().listAllUsers();
        when(service.getOrderService().listAllUsers()).thenReturn(resultListOfUsers);
        assertNotNull("ResultListOfUsers is Empty", resultListOfUsers);
    }

    @Test
    public void testCallSerializeShoppingCartThenResultIsSerializeString() {
        StringBuilder res = new StringBuilder();
        for (ShoppingCartItem item : shoppingCart.getItems()) {
            res.append(item.getProduct().getId()).append("-").append(item.getCount()).append("|");
        }
        if (res.length() > 0) {
            res.deleteCharAt(res.length() - 1);
        }
        assertNotNull("ShoppingCart is not serializable", res.toString());
    }

    @Test
    public void testCallDeserializeShoppingCartThenResultIsShoppingCartNotEmpty() {
        String strSerializable = "1234|5";
        String[] items = strSerializable.split("\\|");
        for (String item : items) {
            try {
                String data[] = item.split("-");
                int idProduct = Integer.parseInt(data[0]);
                int count = Integer.parseInt(data[1]);
                service.getOrderService().addProductToShoppingCart(new ProductForm(idProduct, count), shoppingCart);
            } catch (RuntimeException e) {
                fail("Can't add product to ShoppingCart during deserialization: item=" + item);
            }
        }
        assertFalse("ShoppingCart is null or empty ", shoppingCart.getItems().isEmpty());
    }

    @Test
    public void testCallAuthentificateUserWithGetOrderServiceThenFindUserInListOfUsers() {
        SocialAccount socialAccount = new SocialAccount(name, email);
        CurrentAccount currentAccount = service.getOrderService().authentificate(socialAccount);
        when(service.getOrderService().authentificate(socialAccount)).thenReturn(currentAccount);
        List<Account> resultListOfUsers = service.getOrderService().listAllUsers();
        when(service.getOrderService().listAllUsers()).thenReturn(resultListOfUsers);
        assertTrue("Can't find this account", resultListOfUsers.contains(currentAccount));
    }

    @Test
    public void testCallMakeOrderThenResultIsListOrdersContainsIdMakeOrder() {
        int page = anyInt();
        int limit = anyInt();
        if (shoppingCart == null || shoppingCart.getItems().isEmpty()) {
            fail("ShoppingCart is null or empty");
        }
        long idMakeOrder = service.getOrderService().makeOrder(shoppingCart, currentAccount);
        when(service.getOrderService().makeOrder(shoppingCart, currentAccount)).thenReturn(idMakeOrder);
        List<Order> listOrders = service.getOrderService().listMyOrders(currentAccount, page, limit);
        when(service.getOrderService().listMyOrders(currentAccount, page, limit)).thenReturn(listOrders);
        assertTrue("List orders not contains idMakeOrder", listOrders.contains(idMakeOrder));
    }

    @Test
    public void testFindIdFromUserThenResultUserIDEqualsExpUserID() {
        int userID = service.getOrderService().findIdFromUser(CURRENT_USER_ID);
        when(service.getOrderService().findIdFromUser(CURRENT_USER_ID)).thenReturn(userID);
        int expUserID = CURRENT_USER_ID;
        assertEquals(expUserID, userID);
    }

    @Test
    public void testCallFindOrderByIdThenCurrentIdIsCorrect() {
        Order orderById = service.getOrderService().findOrderById(CURRENT_ORDER_ID, currentAccount);
        if (orderById == null) {
            fail("Order not found by id: " + CURRENT_ORDER_ID);
        }
        if (!orderById.getIdAccount().equals(currentAccount.getId())) {
            fail("Account with id=" + currentAccount.getId() + " is not owner for order with id=" + CURRENT_ORDER_ID);
        }
        when(service.getOrderService().findOrderById(1, currentAccount)).thenReturn(orderById);
        Order expOrder = new Order(11, new Timestamp(ORDER_YEAR, ORDER_MONTH, ORDER_DAY, ORDER_HOUR, ORDER_MINUTE, ORDER_SECONDS, 1));

        assertEquals(expOrder, orderById);
    }

    @Test
    public void testCallListMyOrdersThenResultIsNotNull() {
        int page = anyInt();
        int limit = anyInt();
        List<Order> listOrders = service.getOrderService().listMyOrders(currentAccount, page, limit);
        when(service.getOrderService().listMyOrders(currentAccount, page, limit)).thenReturn(listOrders);
        assertNotNull("ListOrders is Empty", listOrders);
    }

    @Test
    public void testCountMyOrdersThenResultIsCorrectCountOfOrders() {
        int countOfOrdersInSystem = service.getOrderService().countMyOrders(currentAccount);
        when(service.getOrderService().countMyOrders(currentAccount)).thenReturn(countOfOrdersInSystem);
        int expResultOfOrdersInSystem = CURRENT_AMOUNT_OF_ORDERS;
        assertEquals(expResultOfOrdersInSystem, countOfOrdersInSystem);
    }

}
