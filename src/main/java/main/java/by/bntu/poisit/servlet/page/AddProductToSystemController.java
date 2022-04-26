package main.java.by.bntu.poisit.servlet.page;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import main.java.by.bntu.poisit.constants.Constants;
import main.java.by.bntu.poisit.constants.ErrorConstant;
import main.java.by.bntu.poisit.entity.Category;
import main.java.by.bntu.poisit.entity.Producer;
import main.java.by.bntu.poisit.entity.Product;
import main.java.by.bntu.poisit.service.impl.ServiceManager;
import main.java.by.bntu.poisit.servlet.AbstractController;
import main.java.by.bntu.poisit.util.FileUtils;
import main.java.by.bntu.poisit.util.InputProductUtils;
import main.java.by.bntu.poisit.util.RoutingUtils;

@MultipartConfig
@WebServlet("/add-product")
public class AddProductToSystemController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoutingUtils.forwardToPage("add-product.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        Part image = req.getPart("image");
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        String categoryName = req.getParameter("category");
        String producerName = req.getParameter("producer");

        System.out.println("image" + image);
        System.out.println("category: " + categoryName);

        String imageLink = "/media/" + image.getSubmittedFileName();

        Integer categoryId = getProductService().selectIdFromNameOfCategory(categoryName);

        Integer producerId = getProductService().selectIdFromNameOfProducer(producerName);

        InputProductUtils iPUtils = new InputProductUtils();

        StringBuilder errorMessage = iPUtils.generateErrorMessage(id, name, desc, price);

        req.setAttribute("errorId", errorMessage.toString());
        
       

        if (iPUtils.isValidProductID(id) && iPUtils.isValidProductName(name)
                && iPUtils.isValidDescription(desc) && iPUtils.isValidPrice(price)) {
            if (getProductService().findIdFromProductForCheckEmpty(id) == 0
                    && getProductService().findNameFromProductForCheckEmpty(name) == null) {

                Product p = new Product();
                p.setId(id);
                p.setName(name);
                p.setDescription(desc);
                p.setImageLink(imageLink);
                p.setPrice(price);

                getProductService().addProductToSystem(p, categoryId, producerId);

                FileUtils.downloadImage(image, Constants.IMAGE_PATH);
                
                req.setAttribute("errorIdRepeat", ErrorConstant.STR_CORRECT_INFO);

                
            }
            
            else{
            req.setAttribute("errorIdRepeat", ErrorConstant.STR_REPEAT_IN_SYSTEM);
             
        } 
            
        }
        
       RoutingUtils.forwardToPage("add-product.jsp", req, resp);

    }

}
