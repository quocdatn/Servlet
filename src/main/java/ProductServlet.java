import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "projectServlet",urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {
    List<Product> list = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("product.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String product_name = req.getParameter("product_name");
        String strQuantity = req.getParameter("quantity");
        System.out.println("Kiểm tra " + strQuantity);
        String strPrice = req.getParameter("price");
        int quantity = 0;
        double price = 0;
        // trim() : loại bỏ khoảng trắng
        if(!strQuantity.isEmpty()){
            quantity = Integer.parseInt(strQuantity);
        }
        if(!strPrice.isEmpty()){
            price = Integer.parseInt(strPrice);
        }

        Product product = new Product();
        product.setProductName(product_name);
        product.setPrice(price);
        product.setQuantity(quantity);

        list.add(product);
        req.setAttribute("listProduct",list);
        req.getRequestDispatcher("product.jsp").forward(req,resp);
    }
}
