package es.currexify.server.dao;

import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import es.currexify.server.model.TransactionModel;

public class TransactionDAOImplTest {

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
	public void testReadTransactions() {
		EntityManager em = EMFService.get().createEntityManager();
		TransactionDAOImpl tdao = TransactionDAOImpl.getInstance();
		TransactionModel tm = new TransactionModel("cardN", "sCoin", "dCoin", 10, new Date(), 1);
		tdao.createTransaction(em, tm);
		assertEquals(tdao.readTransactions(em).get(0).getCardN(), "cardN");
	}

	@Test
	public void testCreateTransaction() {
		EntityManager em = EMFService.get().createEntityManager();
		TransactionDAOImpl tdao = TransactionDAOImpl.getInstance();
		TransactionModel tm = new TransactionModel("cardN", "sCoin", "dCoin", 10, new Date(), 1);
		TransactionModel tm2 = tdao.createTransaction(em, tm);
		assertEquals(tm2.getCardN(), "cardN");
		em.close();	
	}

	@Test
	public void testReadByCurrency() {
		EntityManager em = EMFService.get().createEntityManager();
		TransactionDAOImpl tdao = TransactionDAOImpl.getInstance();
		TransactionModel tm = new TransactionModel("cardN", "sCoin", "dCoin", 10, new Date(), 1);
		tdao.createTransaction(em, tm);
		assertEquals(tdao.readByCurrency(em, "sCoin", "dCoin").get(0).getCardN(), "cardN");
		em.close();	
	}

	@Test
	public void testUpdateTransaction() {
		EntityManager em = EMFService.get().createEntityManager();
		TransactionDAOImpl tdao = TransactionDAOImpl.getInstance();
		TransactionModel tm = new TransactionModel("cardN", "sCoin", "dCoin", 10, new Date(), 1);
		tdao.createTransaction(em, tm);
		tm.setCardN("cardN2");
		tdao.updateTransaction(em, tm);
		assertEquals(tdao.readByCurrency(em, "sCoin", "dCoin").get(0).getCardN(), "cardN2");
		em.close();	
	}

}
