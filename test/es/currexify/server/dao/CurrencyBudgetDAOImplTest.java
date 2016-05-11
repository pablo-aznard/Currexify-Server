package es.currexify.server.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import es.currexify.server.model.CurrencyBudgetModel;

public class CurrencyBudgetDAOImplTest {

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
	public void testCreateCurrencyBudget() {
		EntityManager em = EMFService.get().createEntityManager();
		CurrencyBudgetDAOImpl cbdao = CurrencyBudgetDAOImpl.getInstance();
		CurrencyBudgetModel cbm = new CurrencyBudgetModel("cardN", "currency", 1);
		CurrencyBudgetModel cbm2 = cbdao.createCurrencyBudget(em, cbm);
		assertEquals(cbm2.getCardN(), "cardN");
	}

	@Test
	public void testReadCurrencyBudgetByCardN() {
		EntityManager em = EMFService.get().createEntityManager();
		CurrencyBudgetDAOImpl cbdao = CurrencyBudgetDAOImpl.getInstance();
		CurrencyBudgetModel cbm = new CurrencyBudgetModel("cardN", "currency", 1);
		cbdao.createCurrencyBudget(em, cbm);
		assertEquals(cbdao.readCurrencyBudgetByCardN(em, "cardN").get(0).getCurrency(), "currency");
	}

	@Test
	public void testUpdateCurrencyBudget() {
		EntityManager em = EMFService.get().createEntityManager();
		CurrencyBudgetDAOImpl cbdao = CurrencyBudgetDAOImpl.getInstance();
		CurrencyBudgetModel cbm = new CurrencyBudgetModel("cardN", "currency", 1);
		cbdao.createCurrencyBudget(em, cbm);
		cbm.setCardN("cardN2");
		cbdao.updateCurrencyBudget(em, cbm);
		assertEquals(cbdao.readCurrencyBudgetByCardN(em, "cardN2").get(0).getCurrency(), "currency");
	}

}
