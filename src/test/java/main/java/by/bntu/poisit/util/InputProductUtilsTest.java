/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.bntu.poisit.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import main.java.by.bntu.poisit.constants.ErrorConstant;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

public class InputProductUtilsTest {

    public InputProductUtilsTest() {
    }

    //test generateErrorMessage
    @Test
    public void testGenerateErrorMessageWithAllCorrectParametersThenResultEqualsExpResultStr() {
        Integer productID = 32132;
        String productName = "AppleI";
        String description = "very good product";
        BigDecimal price = new BigDecimal(3000);

        InputProductUtils iPrUtils = new InputProductUtils();

        StringBuilder expResult = new StringBuilder("Product Info:\n");

        StringBuilder result = iPrUtils.generateErrorMessage(productID, productName, description, price);

        assertEquals(expResult.toString(), result.toString());
    }

    @Test
    public void testGenerateErrorMessageWithAllIncorrectParametersThenResultEqualsExpResultStr() {
        Integer productID = 3123332;
        String productName = "Ap";
        String description = "";
        BigDecimal price = new BigDecimal(300032131);

        InputProductUtils iPrUtils = new InputProductUtils();

        StringBuilder expResult = new StringBuilder("Product Info:\n" + ErrorConstant.STR_INCORRECT_PRODUCT_ID + ErrorConstant.STR_INCORRECT_PRODUCT_NAME
                + ErrorConstant.STR_INCORRECT_DESCRIPTION
        );

        StringBuilder result = iPrUtils.generateErrorMessage(productID, productName, description, price);

        assertEquals(expResult.toString(), result.toString());
    }

    @Test
    public void testGenerateErrorMessageWithIncorrectParameterProductIDThenResultEqualsExpResultStr() {
        Integer productID = 31233321;
        String productName = "AppleI";
        String description = "very good product";
        BigDecimal price = new BigDecimal(3000);

        InputProductUtils iPrUtils = new InputProductUtils();

        StringBuilder expResult = new StringBuilder("Product Info:\n" + ErrorConstant.STR_INCORRECT_PRODUCT_ID
        );

        StringBuilder result = iPrUtils.generateErrorMessage(productID, productName, description, price);

        assertEquals(expResult.toString(), result.toString());
    }

    public void testGenerateErrorMessageWithIncorrectParameterProductNameThenResultEqualsExpResultStr() {
        Integer productID = 32132;
        String productName = "Ap";
        String description = "very good product";
        BigDecimal price = new BigDecimal(3000);

        InputProductUtils iPrUtils = new InputProductUtils();

        StringBuilder expResult = new StringBuilder("Product Info:\n" + ErrorConstant.STR_INCORRECT_PRODUCT_NAME
        );

        StringBuilder result = iPrUtils.generateErrorMessage(productID, productName, description, price);

        assertEquals(expResult.toString(), result.toString());
    }

    public void testGenerateErrorMessageWithIncorrectParameterDescriptionThenResultEqualsExpResultStr() {
        Integer productID = 32132;
        String productName = "AppleI";
        String description = "";
        BigDecimal price = new BigDecimal(3000);

        InputProductUtils iPrUtils = new InputProductUtils();

        StringBuilder expResult = new StringBuilder("Product Info:\n" + ErrorConstant.STR_INCORRECT_DESCRIPTION
        );

        StringBuilder result = iPrUtils.generateErrorMessage(productID, productName, description, price);

        assertEquals(expResult.toString(), result.toString());
    }

    public void testGenerateErrorMessageWithIncorrectParameterPriceThenResultEqualsExpResultStr() {
        Integer productID = 32132;
        String productName = "AppleI";
        String description = "very good product";
        BigDecimal price = new BigDecimal(1);

        InputProductUtils iPrUtils = new InputProductUtils();

        StringBuilder expResult = new StringBuilder("Product Info:\n" + ErrorConstant.STR_INCORRECT_PRICE
        );

        StringBuilder result = iPrUtils.generateErrorMessage(productID, productName, description, price);

        assertEquals(expResult.toString(), result.toString());
    }

    //test isValidProductID
    @Test

    public void testIsValidProductIDWithIncorrectAmountOfSymbolsThenResultIsFalse() {
        Integer productIDLowerBorder = null;
        Integer productIDUpperBorder = 1234567;

        InputProductUtils lPrUtils = new InputProductUtils();

        boolean resultWithEmptyLine = lPrUtils.isValidProductID(productIDLowerBorder);
        boolean resultWithTooMuchSymbols = lPrUtils.isValidProductID(productIDUpperBorder);

        assertFalse("ProductID is correct", resultWithEmptyLine);
        assertFalse("ProductID is correct", resultWithTooMuchSymbols);
    }
    
    @Test

    public void testIsValidProductIDWithCorrectAmountOfSymbolsThenResultIsFalse() {
        Integer productIDLowerBorder = 1;
        Integer productIDUpperBorder = 123456;

        InputProductUtils lPrUtils = new InputProductUtils();

        boolean resultWithEmptyLine = lPrUtils.isValidProductID(productIDLowerBorder);
        boolean resultWithTooMuchSymbols = lPrUtils.isValidProductID(productIDUpperBorder);

        assertTrue("ProductID is incorrect", resultWithEmptyLine);
        assertTrue("ProductID is incorrect", resultWithTooMuchSymbols);
    }

    @Test
    public void testIsValidProductIDWithCorrectNumbersThenResultIsTrue() {
        Integer productID = 12343;

        InputProductUtils lPrUtils = new InputProductUtils();

        boolean result = lPrUtils.isValidProductID(productID);

        assertTrue("productID is incorrect", result);
    }
    
    @Test
    public void testIsValidProductIDWithIncorrectNumbersThenResultIsFalse() {
        Integer productID = 3213214;

        InputProductUtils lPrUtils = new InputProductUtils();

        boolean result = lPrUtils.isValidProductID(productID);

        assertFalse("productID is correct", result);
    }
    
    @Test
    public void testIsValidProductIDWithIncorrectNumberNullThenResultIsFalse() {
        Integer productID = null;

        InputProductUtils lPrUtils = new InputProductUtils();

        boolean result = lPrUtils.isValidProductID(productID);

        assertFalse("productID is correct", result);
    }
    
    //test isValidProductName
    @Test
    public void testIsValidProductNameWithCorrectAmountOfSymbolsThenResultIsTrue() {
        String productNameLowerBorder = "abcde";
        String productNameUpperBorder = "abcdefghigklmnopkrst";

        InputProductUtils lPrUtils = new InputProductUtils();

        boolean resultWithOneSymbol = lPrUtils.isValidProductName(productNameLowerBorder);
        boolean resultWithCorrectAmountOfSymbols = lPrUtils.isValidProductName(productNameUpperBorder);

        assertTrue("productName is incorrect", resultWithOneSymbol);
        assertTrue("productName is incorrect", resultWithCorrectAmountOfSymbols);
    }
    
    @Test
    public void testIsValidProductNameWithIncorrectAmountOfSymbolsThenResultIsTrue() {
        String productNameLowerBorder = "abcd";
        String productNameUpperBorder = "abcdefghigklmnopkrsfh";

        InputProductUtils lPrUtils = new InputProductUtils();

        boolean resultWithOneSymbol = lPrUtils.isValidProductName(productNameLowerBorder);
        boolean resultWithCorrectAmountOfSymbols = lPrUtils.isValidProductName(productNameUpperBorder);

        assertFalse("productName is correct", resultWithOneSymbol);
        assertFalse("productName is correct", resultWithCorrectAmountOfSymbols);
    }

    @Test
    public void testIsValidProductNameWithCorrectParameterLatinSymbolsThenResultIsTrue() {
        String productName = "AppleI";
       

        InputProductUtils lPrUtils = new InputProductUtils();

        boolean resultWithNumbers = lPrUtils.isValidProductName(productName);
        
        assertTrue("productName is incorrect", resultWithNumbers);
        
    }
    
    @Test
    public void testIsValidProductNameWithCorrectParameterOfPuncMarksThenResultIsTrue() {
        String productName = "Apple/I.";
       

        InputProductUtils lPrUtils = new InputProductUtils();

        boolean resultWithNumbers = lPrUtils.isValidProductName(productName);
        
        assertTrue("productName is incorrect", resultWithNumbers);
        
    }
    
    @Test
    public void testIsValidProductNameWithCorrectParameterOfNumbersThenResultIsTrue() {
        String productName = "Apple123";
       

        InputProductUtils lPrUtils = new InputProductUtils();

        boolean resultWithNumbers = lPrUtils.isValidProductName(productName);
        
        assertTrue("productName is incorrect", resultWithNumbers);
        
    }
    
    @Test
    public void testIsValidProductNameWithIncorrectParameterThenResultIsFalse() {
        String productName = "AppleI@";
       

        InputProductUtils lPrUtils = new InputProductUtils();

        boolean resultWithNumbers = lPrUtils.isValidProductName(productName);
        
        assertFalse("productName is correct", resultWithNumbers);
        
    }
    
    @Test
    public void testIsValidDescriptionWithIncorrectAmountOfSymbolsThenResultIsFalse() {
        String descriptionLowerBorder = "";
        String descriptionUpperBorder = "abcdeacbcdabcdeacbcdabcdeacbcdabcdeacbcdabcdeacbcdabcdeacbcda";

        InputProductUtils lPrUtils = new InputProductUtils();

        boolean resultWithOneSymbol = lPrUtils.isValidDescription(descriptionLowerBorder);
        boolean resultWithCorrectAmountOfSymbols = lPrUtils.isValidDescription(descriptionUpperBorder);

        assertFalse("Description is correct", resultWithOneSymbol);
        assertFalse("Description is correct", resultWithCorrectAmountOfSymbols);
    }
    
    @Test
    public void testIsValidDescriptionWithCorrectAmountOfSymbolsThenResultIsFalse() {
        String descriptionLowerBorder = "a";
        String descriptionUpperBorder = "abcdeacbcdabcdeacbcdabcdeacbcdabcdeacbcdabcdeacbcdabcdeacbcd";

        InputProductUtils lPrUtils = new InputProductUtils();

        boolean resultWithOneSymbol = lPrUtils.isValidDescription(descriptionLowerBorder);
        boolean resultWithCorrectAmountOfSymbols = lPrUtils.isValidDescription(descriptionUpperBorder);

        assertTrue("Description is incorrect", resultWithOneSymbol);
        assertTrue("Description is incorrect", resultWithCorrectAmountOfSymbols);
    }

    
    
    @Test
    public void testIsValidPriceWithCorrectAmountOfNumberThenResultIsTrue() {
        BigDecimal priceLowerBorder = new BigDecimal(12);
        BigDecimal priceUpperBorder = new BigDecimal(123456789);

        InputProductUtils lPrUtils = new InputProductUtils();

        boolean resultWithOneSymbol = lPrUtils.isValidPrice(priceLowerBorder);
        boolean resultWithCorrectAmountOfSymbols = lPrUtils.isValidPrice(priceUpperBorder);

        assertTrue("Price is incorrect", resultWithOneSymbol);
        assertTrue("Price is incorrect", resultWithCorrectAmountOfSymbols);
    }
    
    @Test
    public void testIsValidPriceWithIncorrectAmountOfNumbersThenResultIsFalse() {
        BigDecimal priceLowerBorder = new BigDecimal(1);
        BigDecimal priceUpperBorder = new BigDecimal(1234567890);

        InputProductUtils lPrUtils = new InputProductUtils();

        boolean resultWithOneSymbol = lPrUtils.isValidPrice(priceLowerBorder);
        boolean resultWithCorrectAmountOfSymbols = lPrUtils.isValidPrice(priceUpperBorder);

        assertFalse("Price is correct", resultWithOneSymbol);
        assertFalse("Price is correct", resultWithCorrectAmountOfSymbols);
    }
    
    @Test
    public void testIsValidPriceWithIncorrectParameterThenResultIsTrue() {
        BigDecimal price = new BigDecimal(12344);

        InputProductUtils lPrUtils = new InputProductUtils();

        boolean resultWithNumbers = lPrUtils.isValidPrice(price);
        
        assertTrue("productName is incorrect", resultWithNumbers);
        
    }
    
    @Test
    public void testIsValidPriceWithCorrectParameterThenResultIsTrue() {
        BigDecimal price = new BigDecimal(12344);

        InputProductUtils lPrUtils = new InputProductUtils();

        boolean resultWithNumbers = lPrUtils.isValidPrice(price);
        
        assertTrue("productName is incorrect", resultWithNumbers);
        
    }
    
    @Test
    public void testIsValidPriceWithIncorrectParameterThenResultIsFalse() {
        BigDecimal price = new BigDecimal(2131231234);

        InputProductUtils lPrUtils = new InputProductUtils();

        boolean resultWithNumbers = lPrUtils.isValidPrice(price);
        
        assertFalse("Price is correct", resultWithNumbers);
        
    }

    
    @Test
    public void testAddCodeSystemInfoWithIncorrectProductIDThenResultListEqualsExpListWithSystemInfoCodes() {
        Integer productID = 1234324; 
        String productName = "AppleI";
        String description = "very good product";
        BigDecimal price = new BigDecimal(12);

        InputProductUtils lPrUtils = new InputProductUtils();

        List<Integer> expListWithSystemInfoCodes = Arrays.asList(1);
        List<Integer> resultListWithSystemInfoCodes = lPrUtils.addCodeSystemInfo(productID, productName, description, price);

        assertThat(resultListWithSystemInfoCodes, is(expListWithSystemInfoCodes));

    }
    
    @Test
    public void testAddCodeSystemInfoWithIncorrectProductNameThenResultListEqualsExpListWithSystemInfoCodes() {
        Integer productID = 12343; 
        String productName = "Apple@";
        String description = "very good product";
        BigDecimal price = new BigDecimal(12);

        InputProductUtils lPrUtils = new InputProductUtils();

        List<Integer> expListWithSystemInfoCodes = Arrays.asList(2);
        List<Integer> resultListWithSystemInfoCodes = lPrUtils.addCodeSystemInfo(productID, productName, description, price);

        assertThat(resultListWithSystemInfoCodes, is(expListWithSystemInfoCodes));

    }
    
    @Test
    public void testAddCodeSystemInfoWithIncorrectDescriptionThenResultListEqualsExpListWithSystemInfoCodes() {
        Integer productID = 12343; 
        String productName = "Apple";
        String description = "";
        BigDecimal price = new BigDecimal(12);

        InputProductUtils lPrUtils = new InputProductUtils();

        List<Integer> expListWithSystemInfoCodes = Arrays.asList(3);
        List<Integer> resultListWithSystemInfoCodes = lPrUtils.addCodeSystemInfo(productID, productName, description, price);

        assertThat(resultListWithSystemInfoCodes, is(expListWithSystemInfoCodes));

    }
    
    @Test
    public void testAddCodeSystemInfoWithIncorrectPriceThenResultListEqualsExpListWithSystemInfoCodes() {
        Integer productID = 12343; 
        String productName = "Apple";
        String description = "very good choice";
        BigDecimal price = new BigDecimal(1);

        InputProductUtils lPrUtils = new InputProductUtils();

        List<Integer> expListWithSystemInfoCodes = Arrays.asList(4);
        List<Integer> resultListWithSystemInfoCodes = lPrUtils.addCodeSystemInfo(productID, productName, description, price);

        assertThat(resultListWithSystemInfoCodes, is(expListWithSystemInfoCodes));

    }
    
    @Test
    public void testAddCodeSystemInfoWithAllIncorrectParametersThenResultListEqualsExpListWithSystemInfoCodes() {
        Integer productID = 123433232; 
        String productName = "App";
        String description = "";
        BigDecimal price = new BigDecimal(1);

        InputProductUtils lPrUtils = new InputProductUtils();

        List<Integer> expListWithSystemInfoCodes = Arrays.asList(1, 2, 3, 4);
        List<Integer> resultListWithSystemInfoCodes = lPrUtils.addCodeSystemInfo(productID, productName, description, price);

        assertThat(resultListWithSystemInfoCodes, is(expListWithSystemInfoCodes));

    }
    
    @Test
    public void testAddCodeSystemInfoWithAllCorrectParametersThenResultListEqualsExpListWithSystemInfoCodes() {
        Integer productID = 3214; 
        String productName = "AppleI";
        String description = "very good product";
        BigDecimal price = new BigDecimal(12);

        InputProductUtils lPrUtils = new InputProductUtils();

        List<Integer> expListWithSystemInfoCodes = Arrays.asList();
        List<Integer> resultListWithSystemInfoCodes = lPrUtils.addCodeSystemInfo(productID, productName, description, price);

        assertThat(resultListWithSystemInfoCodes, is(expListWithSystemInfoCodes));

    }

}
