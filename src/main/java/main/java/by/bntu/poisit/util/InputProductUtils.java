package main.java.by.bntu.poisit.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import main.java.by.bntu.poisit.constants.ErrorConstant;
import main.java.by.bntu.poisit.constants.ProductConstants;

public class InputProductUtils {

    public StringBuilder generateErrorMessage(Integer productID, String productName, String description, BigDecimal price) {
        List<Integer> errorInfoCodes = addCodeSystemInfo(productID, productName, description, price);

        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("Product Info:\n");
        for (Integer errorId : errorInfoCodes) {
            switch (errorId) {
                case ErrorConstant.INCORRECT_PRODUCT_ID:
                    errorMessage.append(ErrorConstant.STR_INCORRECT_PRODUCT_ID);
                    break;
                case ErrorConstant.INCORRECT_PRODUCT_NAME:
                    errorMessage.append(ErrorConstant.STR_INCORRECT_PRODUCT_NAME);
                    break;
                case ErrorConstant.INCORRECT_DESCRIPTION:
                    errorMessage.append(ErrorConstant.STR_INCORRECT_DESCRIPTION);
                    break;
                case ErrorConstant.INCORRECT_PRICE:
                    errorMessage.append(ErrorConstant.STR_INCORRECT_PRICE);
                    break;
                
            }

        }
        return errorMessage;
    }

    public boolean isValidProductID(Integer id) {
        int lowerCharIdProductRange = 1;
        int upperCharIdProductRange = 6;

        String numbStr = String.valueOf(id);
        int idLength = numbStr.length();

        if (idLength < lowerCharIdProductRange || idLength > upperCharIdProductRange) {
            return false;
        }

        for (int i = 0; i < idLength; i++) {
            char symbolNumb = numbStr.charAt(i);

            if (!(symbolNumb >= ProductConstants.NUMBER_LOWER_BORDER_ID
                    && symbolNumb <= ProductConstants.NUMBER_UPPER_BORDER_ID)) {
                return false;

            }
        }
        return true;
    }

    public boolean isValidProductName(String productName) {
        int lowerCharProductNameRange = 5;
        int upperCharProductNameRange = 20;
        int productNameLength = productName.length();

        if (productNameLength < lowerCharProductNameRange || productNameLength > upperCharProductNameRange) {
            return false;
        }

        for (int i = 0; i < productNameLength; i++) {
            char symbol = productName.charAt(i);

            if (!(symbol >= ProductConstants.LATIN_LOWER_BORDER_ID
                    && symbol <= ProductConstants.LATIN_UPPER_BORDER_ID)) {
                if (!(symbol >= ProductConstants.PUNC_MARKS_LOWER_BORDER_ID
                        && symbol <= ProductConstants.PUNC_MARKS_UPPER_BORDER_ID)) {
                    if (!(symbol >= ProductConstants.NUMBER_LOWER_BORDER_ID
                            && symbol <= ProductConstants.NUMBER_UPPER_BORDER_ID)) {
                        return false;
                    }
                }

            }
        }

        return true;
    }

    public boolean isValidDescription(String description) {
        int lowerCharDescriptionRange = 1;
        int upperCharDescriptionRange = 60;
        int descriptionLength = description.length();

        if (descriptionLength < lowerCharDescriptionRange || descriptionLength > upperCharDescriptionRange) {
            return false;
        }

        return true;
    }

    public boolean isValidPrice(BigDecimal price) {
        int lowerCharPriceRange = 2;
        int upperCharPriceRange = 9;

        String priceStr = String.valueOf(price);
        int priceLength = priceStr.length();

        if (priceLength < lowerCharPriceRange || priceLength > upperCharPriceRange) {
            return false;
        }

        for (int i = 0; i < priceLength; i++) {
            char symbol = priceStr.charAt(i);

            if (!(symbol >= ProductConstants.NUMBER_LOWER_BORDER_ID
                    && symbol <= ProductConstants.NUMBER_UPPER_BORDER_ID)) {
                return false;
            }

        }
        return true;
    }

    public List<Integer> addCodeSystemInfo(Integer productID, String productName, String description, BigDecimal price) {
        List<Integer> listWithSystemInfoCodes = new ArrayList<>();

        if (!isValidProductID(productID)) {
            listWithSystemInfoCodes.add(ErrorConstant.INCORRECT_PRODUCT_ID);
        }

        if (!isValidProductName(productName)) {
            listWithSystemInfoCodes.add(ErrorConstant.INCORRECT_PRODUCT_NAME);
        }

        if (!isValidDescription(description)) {
            listWithSystemInfoCodes.add(ErrorConstant.INCORRECT_DESCRIPTION);
        }

        if (!isValidPrice(price)) {
            listWithSystemInfoCodes.add(ErrorConstant.INCORRECT_PRICE);
        }
        
        

        return listWithSystemInfoCodes;
    }

}
