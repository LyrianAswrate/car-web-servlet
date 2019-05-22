package hu.gondag.bs33ut.car.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import hu.gondag.bs33ut.car.security.service.CarService;

@WebServlet(urlPatterns = { "/DeleteCarServlet" })
public class DeleteCarServlet extends HttpServlet {

    private static final long serialVersionUID = 3647898607694493077L;

    private static final Logger LOG = LoggerFactory.getLogger(DeleteCarServlet.class);

    @Autowired
    private CarService carService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("GET hívás nem támogatott!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Object obj = request.getParameter("carId");

            if (obj instanceof String) {
                Long carId = Long.parseLong((String) obj);
                carService.deleteById(carId);
                request.setAttribute("carDeleteSuccessful", "Törlés sikeres!");
            } else {
                throw new UnsupportedOperationException("Az azonosító nem található a kérésben!");
            }
        } catch (Exception e) {
            DeleteCarServlet.LOG.error(e.getMessage(), e);
            request.setAttribute("carDeleteError", e.getMessage());
        }
        getServletContext().getRequestDispatcher("/welcome").forward(request, response);
    }

}
