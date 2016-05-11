package es.currexify.server.dao;

import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import es.currexify.server.model.HistoryModel;

public class HistoryDAOImplTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());
	
	@Before
	public void setUp() throws Exception {
		helper.setUp();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	@Test
	public void testCreateHistory() {
		EntityManager em = EMFService.get().createEntityManager();
		HistoryDAOImpl hdao = HistoryDAOImpl.getInstance();
		HistoryModel hm = new HistoryModel("1234", "EUR", 100, "Entrante", new Date());
		HistoryModel hm2 = hdao.createHistory(em, hm);				
		assertEquals(hm2.getCardN(), "1234");
		em.close();
	}

	@Test
	public void testReadHistory() {
		EntityManager em = EMFService.get().createEntityManager();
		HistoryDAOImpl hdao = HistoryDAOImpl.getInstance();
		HistoryModel hm = new HistoryModel("1234", "EUR", 100, "Entrante", new Date());
		hdao.createHistory(em, hm);	
		assertEquals(hdao.readHistory(em).get(0).getCardN(), "1234");
		em.close();
	}

	@Test
	public void testUpdateHistory() {
		EntityManager em = EMFService.get().createEntityManager();
		HistoryDAOImpl hdao = HistoryDAOImpl.getInstance();
		HistoryModel hm = new HistoryModel("1234", "EUR", 100, "Entrante", new Date());
		hdao.createHistory(em, hm);
		hm.setCardN("12345");
		hdao.updateHistory(em, hm);
		assertEquals(hdao.readHistory(em).get(0).getCardN(), "12345");
		em.close();
	}

}
