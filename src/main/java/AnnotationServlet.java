import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "annotationServelet", urlPatterns = {"/demo"})
public class AnnotationServlet extends HttpServlet {
    //Phương thức
    //GET: mặc định khi gọi link. Không bảo mật tham số mà client truyền lên
    //      cách truyền tham số: ?tenthamso=giatri&tenthamso=giatri
    //      có giới hạn về tham số gọi trên trình duyệt
    //POST: tham số đã đc truyền ngầm. Tham số đc bảo mật
    //      tham số đc truyền thông qua code và thẻ form trong html
    //      không có giới hạn về tham số
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("passwold");

        PrintWriter writer = resp.getWriter();
        writer.println("Hello " + username + " - passwold : " + password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello Do Post");
    }
}
