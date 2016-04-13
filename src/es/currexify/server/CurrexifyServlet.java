package es.currexify.server;

import java.io.IOException;

import javax.servlet.http.*;

import es.currexify.server.dao.*;
import es.currexify.server.model.*;

@SuppressWarnings("serial")
public class CurrexifyServlet extends HttpServlet {
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/plain");
    //resp.getWriter().println("Hello, world");
    
    CurrencyBudgetDAO cbdao = CurrencyBudgetDAOImpl.getInstance();
    CurrencyBudgetModel cbm = cbdao.createCurrencyBudget(1234, "Euro", 10.0);
    CurrencyBudgetModel cbm1 = cbdao.createCurrencyBudget(12, "Euro", 10.0);
    resp.getWriter().println(cbm.getId()+" "+cbm.getCardN()+" "
        +cbm.getCurrency()+" "+cbm.getBudget()+" "+
        cbm1.getId()+" "+cbm1.getCardN()+" "+cbm1.getCurrency()+" "+cbm1.getBudget());
    
    CurrencyExRateDAO cerdao = CurrencyExRateDAOImpl.getInstance();
    CurrencyExRateModel cer = cerdao.createCurrencyExRate(1.1, "Perico");
    CurrencyExRateModel cer1 = cerdao.createCurrencyExRate(1.2, "Periquito");
    resp.getWriter().println(cer.getId()+" "+cer.getCurrency()+" "
        +cer.getEuroEx()+" "+
        cer1.getId()+" "+cer1.getCurrency()+" "+cer1.getEuroEx());
    
    
    HistoryDAO hmdao = HistoryDAOImpl.getInstance();
    HistoryModel hm = hmdao.createHistory("1234", "Euro", 5.00, "Entrante", "Ayer");
    HistoryModel hm1 = hmdao.createHistory("123456", "Libra", 10.00, "Saliente", "Hoy");
    resp.getWriter().println(hm.getId()+" "+hm.getCardN()+" "
        +hm.getCoin()+" "+hm.getAmount()+" "+hm.getType()+" "+hm.getDate()+" "+
        hm1.getId()+" "+hm1.getCardN()+" "
        +hm1.getCoin()+" "+hm1.getAmount()+" "+hm1.getType()+" "+hm1.getDate());
    
    
    UsuariosDAO udao = UsuariosDAOImpl.getInstance();
    UsuariosModel um = udao.createUser("Perico", "asdfjklh", "asdfjkh@gmail.com", "Calle Casas", "666666666", "12345");
    UsuariosModel um1 = udao.createUser("Pepe", "riuekbv", "ouitrb@gmail.com", "Calle Edificios", "777777777", "123");
    resp.getWriter().println(um.getId()+" "+um.getName()+" "+um.getPassword()+
    		" "+um.getEmail()+" "+um.getAddress()+" "+um.getPhone()+" "+
    		um.getCardN()+ " "+
    		um1.getId()+" "+um1.getName()+" "+um1.getPassword()+
    		" "+um1.getEmail()+" "+um1.getAddress()+" "+um1.getPhone()+" "+
    		um1.getCardN());
    
    
    /*for(CurrencyBudgetModel cbm: cbdao.read()) {
      resp.getWriter().println(cbm);
    }*/
  }
}