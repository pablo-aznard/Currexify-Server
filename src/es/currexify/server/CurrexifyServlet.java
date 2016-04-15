package es.currexify.server;

import java.io.IOException;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.currexify.server.dao.*;
import es.currexify.server.model.*;

@SuppressWarnings("serial")
public class CurrexifyServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/plain");
		//ESTO ES LA PUTA CLAVE, CUANDO SE USE LA BBDD HAY QUE CREAR UN EM GLOBAL AL SERVLET
		EntityManager em = EMFService.get().createEntityManager(); 
		UsuariosDAOImpl umdao = UsuariosDAOImpl.getInstance();
		UsuariosModel um = new UsuariosModel("Perico", "qwerasdf", "qwer@asdf.com", "Calle Pinos", "123456789");
		umdao.createUser(em, um);
		UsuariosModel um1 = umdao.readUserById(em, um.getId());
		resp.getWriter().println(um1.getId()+" "+um1.getCardN());
		em.close();
		/*HistoryModel hm = new HistoryModel(um.getCardN(), "Euro", 10.0, "entrante", new Date());
		umdao.addHistoryToUser(em, hm, um);
		List<UsuariosModel> uml = umdao.readUsers(em);
		for(UsuariosModel umr : uml){
			resp.getWriter().println(um.getId()+" "+um.getName());
			List<HistoryModel> hmlTemp = umr.getHistories();
			for(HistoryModel hmr : hmlTemp){
				resp.getWriter().println(hmr.getId()+" "+hmr.getCardN()+" "+hmr.getCoin()+" "+hmr.getAmount()+" "+hmr.getDate());
			}
		}*/
	}
  }