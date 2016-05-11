package es.currexify.server.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import es.currexify.server.model.CurrencyExRateModel;

public class CurrencyExRateDAOImplTest {

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
	public void testCreateCurrencyExRate() {
		EntityManager em = EMFService.get().createEntityManager();
		CurrencyExRateModel cerm = new CurrencyExRateModel(100, "EUR");
		CurrencyExRateDAOImpl cermdao = CurrencyExRateDAOImpl.getInstance();
		CurrencyExRateModel cerm2 = cermdao.createCurrencyExRate(em, cerm);
		assertEquals(cerm2.getCurrency(), "EUR");
		em.close();		
	}

	@Test
	public void testReadCurrencyExRates() {
		EntityManager em = EMFService.get().createEntityManager();
		CurrencyExRateDAOImpl cermdao = CurrencyExRateDAOImpl.getInstance();
		CurrencyExRateModel cerm = new CurrencyExRateModel(100, "EUR");
		cermdao.createCurrencyExRate(em, cerm);
		assertEquals(cermdao.readCurrencyExRates(em).get(0).getCurrency(), "EUR");
		em.close();
	}

	@Test
	public void testReadCurrencyExRatesByCurrency() {
		EntityManager em = EMFService.get().createEntityManager();
		CurrencyExRateDAOImpl cermdao = CurrencyExRateDAOImpl.getInstance();
		CurrencyExRateModel cerm = new CurrencyExRateModel(100, "EUR");
		cermdao.createCurrencyExRate(em, cerm);
		assertEquals(cermdao.readCurrencyExRatesByCurrency(em, "EUR").getCurrency(), "EUR");
		em.close();
	}

	@Test
	public void testUpdateCurrencyExRate() {
		EntityManager em = EMFService.get().createEntityManager();
		CurrencyExRateDAOImpl cermdao = CurrencyExRateDAOImpl.getInstance();
		CurrencyExRateModel cerm = new CurrencyExRateModel(100, "EUR");
		cermdao.createCurrencyExRate(em, cerm);
		cerm.setCurrency("USD");
		cermdao.updateCurrencyExRate(em, cerm);
		assertEquals(cermdao.readCurrencyExRates(em).get(0).getCurrency(), "USD");
		em.close();
	}

	@Test
	public void testDeleteCurrencyExRate() {
		EntityManager em = EMFService.get().createEntityManager();
		CurrencyExRateDAOImpl cermdao = CurrencyExRateDAOImpl.getInstance();
		CurrencyExRateModel cerm = new CurrencyExRateModel(100, "EUR");
		cermdao.createCurrencyExRate(em, cerm);
		cermdao.deleteCurrencyExRate(em, cerm);
		assertEquals(cermdao.readCurrencyExRatesByCurrency(em, "EUR"), null);
		em.close();
	}

}
