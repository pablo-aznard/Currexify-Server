package es.currexify.server;


import java.io.IOException;
import java.util.*;

import javax.servlet.http.*;

import es.currexify.server.dao.*;
import es.currexify.server.model.*;

@SuppressWarnings("serial")
public class CurrexifyServlet extends HttpServlet {
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/plain");
    //resp.getWriter().println("Hello, world");
    
    /*
    CurrencyBudgetDAO cbdao = CurrencyBudgetDAOImpl.getInstance();
    CurrencyBudgetModel cbm = cbdao.createCurrencyBudget("1234", "Euro", 10.00);
    CurrencyBudgetModel cbm1 = cbdao.createCurrencyBudget("12", "Euro", 10.00);
    //resp.getWriter().println(cbm.getId()+" "+cbm.getCardN()+" "
    //  +cbm.getCurrency()+" "+cbm.getBudget()+" "+
    //  cbm1.getId()+" "+cbm1.getCardN()+" "+cbm1.getCurrency()+" "+cbm1.getBudget());
    List<CurrencyBudgetModel> cbml = cbdao.readCurrencyBudgetByCardN("1234");
    if(cbml.size()>0){
    	for(CurrencyBudgetModel cbmr : cbml){
    		//resp.getWriter().println(cbmr.getId()+" "+cbmr.getCardN()+" "
    	    //        +cbmr.getCurrency()+" "+cbmr.getBudget());
    		cbmr.setBudget(Math.random()*1000);
    		cbdao.updateCurrencyBudget(cbmr);
    		cbdao.deleteCurrencyBudgetById(cbmr.getId());
    		}
    	}    
    
    else {
    	resp.getWriter().println("IMBÉCIL");
    }    
    cbml = cbdao.readCurrencyBudgetByCardN("1234");
    if(cbml.size()>0){
    	for(CurrencyBudgetModel cbmr : cbml){
    		resp.getWriter().println(cbmr.getId()+" "+cbmr.getCardN()+" "
    	            +cbmr.getCurrency()+" "+cbmr.getBudget());}
    	}    
    
    else {
    	resp.getWriter().println("IMBÉCIL");
    }
    */
    
    
    /*
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
    */
    
    UsuariosDAO udao = UsuariosDAOImpl.getInstance();
    HistoryDAO hdao = HistoryDAOImpl.getInstance();
    UsuariosModel um = udao.createUser("Perico", "asdfjklh", "asdfjkh@gmail.com", "Calle Casas", "666666666", "12345");
    UsuariosModel um1 = udao.createUser("Pepe", "riuekbv", "ouitrb@gmail.com", "Calle Edificios", "777777777", "123");
    HistoryModel hm1 = hdao.createHistory("1234", "EURO", 10.5, "entrante", new Date());
    HistoryModel hm2 = hdao.createHistory("1234", "DOLLAR", 10.8, "saliente", new Date());
    HistoryModel hm3 = hdao.createHistory("1234", "POUND", 10.7, "entrante", new Date());
    
    hdao.createHistory(hm1.getCardN(), hm1.getCoin(), hm1.getAmount(), hm1.getType(), hm1.getDate());
    hdao.createHistory(hm2.getCardN(), hm2.getCoin(), hm2.getAmount(), hm2.getType(), hm2.getDate());
    hdao.createHistory(hm3.getCardN(), hm3.getCoin(), hm3.getAmount(), hm3.getType(), hm3.getDate());
    List<HistoryModel> empList = new ArrayList<HistoryModel>(); 
    empList.add(hm1);
    empList.add(hm2);
    empList.add(hm3);
    
    um.setHistories(empList);
    udao.updateUsuario(um);
    List<UsuariosModel> uml = udao.readUsers();
   /* if(uml.size()>0){
    	for(UsuariosModel umr : uml){
    		//resp.getWriter().println(cbmr.getId()+" "+cbmr.getCardN()+" "
    	    //        +cbmr.getCurrency()+" "+cbmr.getBudget());
    		String tamaño = String.valueOf(uml.size());
    		umr.setName("Perico"+tamaño);
    		udao.updateUsuario(umr);
    		udao.deleteUsuarioById(umr.getId());
    		}
    	}    
    
    else {
    	resp.getWriter().println("IMBÉCIL");
    }   */ 
    if(uml.size()>0){
    	for(UsuariosModel umr : uml){
    		resp.getWriter().println(umr.getId()+" "+umr.getName()+" "+umr.getPassword()+
    				" "+umr.getEmail()+" "+umr.getAddress()+" "+umr.getPhone()+" "+
    				umr.getCardN()+ " ");
    	}
    }
    
    
    /*for(CurrencyBudgetModel cbm: cbdao.read()) {
      resp.getWriter().println(cbm);
    }*/
  }
}