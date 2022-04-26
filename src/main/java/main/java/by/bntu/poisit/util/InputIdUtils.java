
package main.java.by.bntu.poisit.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import main.java.by.bntu.poisit.constants.ErrorConstant;
import main.java.by.bntu.poisit.constants.IDConstants;



public class InputIdUtils {
    
    public StringBuilder generateErrorMessageForAdminPanel(Integer ID) {
        List<Integer> errorInfoCodes = addErrorCodeForGenerateMessage(ID);

        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("Errors:\n");
        for (Integer errorId : errorInfoCodes) {
            if(errorId == ErrorConstant.INCORRECT_ID){
                    errorMessage.append(ErrorConstant.STR_INCORRECT_ID);
            }
        }
        return errorMessage;
    }
    
    public boolean isValidID(Integer id) {
        int lowerCharIdUserRange = 1;
        int upperCharIdUserRange = 6;

        String numbStr = String.valueOf(id);
        int idLength = numbStr.length();

        if (idLength < lowerCharIdUserRange || idLength > upperCharIdUserRange) {
            return false;
        }

        for (int i = 0; i < idLength; i++) {
            char symbolNumb = numbStr.charAt(i);

            if (!(symbolNumb >= IDConstants.NUMBER_LOWER_BORDER_ID
                    && symbolNumb <= IDConstants.NUMBER_UPPER_BORDER_ID)) {
                return false;

            }
        }
        return true;
    }
    
    public List<Integer> addErrorCodeForGenerateMessage(Integer ID) {
        List<Integer> listWithSystemInfoCodes = new ArrayList<>();

        if (!isValidID(ID)) {
            listWithSystemInfoCodes.add(ErrorConstant.INCORRECT_ID);
        }

        return listWithSystemInfoCodes;
    }
    
}
