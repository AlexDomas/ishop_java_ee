
package main.java.by.bntu.poisit.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import main.java.by.bntu.poisit.constants.ErrorConstant;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

public class InputIdUtilsTest {

    public InputIdUtilsTest() {
    }

    @Test
    public void testGenerateErrorMessageForAdminPanelWithCorrectParameterInputIDThenResultStrWithNoErrors() {
        Integer inputID = 1233;

        InputIdUtils iIdUtils = new InputIdUtils();

        StringBuilder expResult = new StringBuilder("Errors:\n");

        StringBuilder result = iIdUtils.generateErrorMessageForAdminPanel(inputID);

        assertEquals(expResult.toString(), result.toString());

    }

    @Test
    public void testGenerateErrorMessageForAdminPanelWithIncorrectParameterInputIDThenResultStrWithErrorMessage() {
        Integer inputID = 1233434;

        InputIdUtils iIdUtils = new InputIdUtils();

        StringBuilder expResult = new StringBuilder("Errors:\n" + ErrorConstant.STR_INCORRECT_ID);

        StringBuilder result = iIdUtils.generateErrorMessageForAdminPanel(inputID);

        assertEquals(expResult.toString(), result.toString());

    }

    @Test

    public void testIsValidInputIDWithIncorrectAmountOfNumbersThenFalseResult() {
        Integer inputIDLowerBorder = null;
        Integer inputIDUpperBorder = 1234567;

        InputIdUtils iIdUtils = new InputIdUtils();

        boolean resultWithEmptyLine = iIdUtils.isValidID(inputIDLowerBorder);
        boolean resultWithTooMuchNumbers = iIdUtils.isValidID(inputIDUpperBorder);

        assertFalse("InputID is correct", resultWithEmptyLine);
        assertFalse("InputID is correct", resultWithTooMuchNumbers);
    }

    @Test

    public void testIsValidInputIDWithCorrectAmountOfNumbersThenTrue() {
        Integer inputIDLowerBorder = 1;
        Integer inputIDUpperBorder = 123456;

        InputIdUtils iIdUtils = new InputIdUtils();

        boolean resultWithOneNumber = iIdUtils.isValidID(inputIDLowerBorder);
        boolean resultWithTooMuchSymbols = iIdUtils.isValidID(inputIDUpperBorder);

        assertTrue("InputID is incorrect", resultWithOneNumber);
        assertTrue("InputID is incorrect", resultWithTooMuchSymbols);
    }

    @Test
    public void testIsValidInputIDWithCorrectNumbersIDThenResultISTrue() {
        Integer inputID = 123;

        InputIdUtils iIdUtils = new InputIdUtils();

        boolean result = iIdUtils.isValidID(inputID);

        assertTrue("InputID is incorrect", result);
    }

    @Test
    public void testIsValidInputIDWithIncorrectNumbersIDThenResultIsTrue() {
        Integer inputID = 12332132;

        InputIdUtils iIdUtils = new InputIdUtils();

        boolean result = iIdUtils.isValidID(inputID);

        assertFalse("InputID is correct", result);
    }

    @Test
    public void testAddErrorCodeForGenerateMessageWithIncorrectParameterThenResultWithINCORRECT_ID() {
        Integer inputID = 1234332;

        InputIdUtils iIdUtils = new InputIdUtils();

        List<Integer> expListWithSystemInfoCodes = Arrays.asList(1);
        List<Integer> resultListWithSystemInfoCodes = iIdUtils.addErrorCodeForGenerateMessage(inputID);

        assertThat(resultListWithSystemInfoCodes, is(expListWithSystemInfoCodes));

    }
    
    @Test
    public void testAddErrorCodeForGenerateMessageWithCorrectParameterThenResultWithEmptyList() {
        Integer inputID = 1234;

        InputIdUtils iIdUtils = new InputIdUtils();

        List<Integer> expListWithSystemInfoCodes = new ArrayList<>();
        List<Integer> resultListWithSystemInfoCodes = iIdUtils.addErrorCodeForGenerateMessage(inputID);

        assertThat(resultListWithSystemInfoCodes, is(expListWithSystemInfoCodes));

    }

}
        
