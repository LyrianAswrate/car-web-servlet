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

import hu.gondag.bs33ut.car.security.service.CarModelService;
import hu.gondag.bs33ut.car.security.service.CarService;

@WebServlet(urlPatterns = { "/DeleteCarModelServlet" })
public class DeleteCarModelServlet extends HttpServlet {

    private static final long serialVersionUID = 3647898607694493077L;

    private static final Logger LOG = LoggerFactory.getLogger(DeleteCarServlet.class);

    @Autowired
    private CarService carService;

    @Autowired
    private CarModelService carModelService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("GET hívás nem támogatott!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Object obj = request.getParameter("carModelId");

            if (obj instanceof String) {

                Long carModelId = Long.parseLong((String) obj);

                // Lehetne SQL lekéréssel is de lusta vagyok. :D
                boolean hasCarConnected = carService.getAll().stream()
                        .anyMatch(pre -> pre.getCarModelId().longValue() == carModelId.longValue());

                if (hasCarConnected) {
                    throw new UnsupportedOperationException("Van kapcsolódó \"Gépjármű\" a törlendő modelhez!");
                }

                carModelService.deleteById(carModelId);
                request.setAttribute("carModelDeleteSuccessful", "Törlés sikeres!");
            } else {
                throw new UnsupportedOperationException("Az azonosító nem található a kérésben!");
            }
        } catch (Exception e) {
            DeleteCarModelServlet.LOG.error(e.getMessage(), e);
            request.setAttribute("carModelDeleteError", e.getMessage());
        }
        getServletContext().getRequestDispatcher("/welcome").forward(request, response);
    }

}
