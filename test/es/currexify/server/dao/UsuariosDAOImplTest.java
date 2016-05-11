package es.currexify.server.dao;

import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import es.currexify.server.model.CurrencyBudgetModel;
import es.currexify.server.model.HistoryModel;
import es.currexify.server.model.TransactionModel;
import es.currexify.server.model.UsuariosModel;

public class UsuariosDAOImplTest {

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
	public void testCreateUser() {
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um = new UsuariosModel("nombre", "password", "email", "address", "phone");
		UsuariosModel um2 = udao.createUser(em, um);
		assertEquals(um2.getAddress(), "address");	
	}

	@Test
	public void testReadUsers() {
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um = new UsuariosModel("nombre", "password", "email", "address", "phone");
		udao.createUser(em, um);
		assertEquals(udao.readUsers(em).get(0).getAddress(), "address");
		em.close();		
	}

	@Test
	public void testReadUserById() {
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um = new UsuariosModel("nombre", "password", "email", "address", "phone");
		udao.createUser(em, um);
		assertEquals(udao.readUserById(em, um.getId()).getAddress(), "address");
		em.close();	
	}

	@Test
	public void testReadUserByName() {
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um = new UsuariosModel("nombre", "password", "email", "address", "phone");
		udao.createUser(em, um);
		assertEquals(udao.readUserByName(em, "nombre").getAddress(), "address");
		em.close();	
	}

	@Test
	public void testReadUserByEmail() {
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um = new UsuariosModel("nombre", "password", "email", "address", "phone");
		udao.createUser(em, um);
		assertEquals(udao.readUserByEmail(em, "email").getAddress(), "address");
		em.close();	
	}

	@Test
	public void testUpdateUsuario() {
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um = new UsuariosModel("nombre", "password", "email", "address", "phone");
		udao.createUser(em, um);
		um.setAddress("address2");
		udao.updateUsuario(em, um);
		assertEquals(udao.readUserByName(em, "nombre").getAddress(), "address2");
		em.close();	
	}

	@Test
	public void testUpdateTransaction() {
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um1 = new UsuariosModel("nombre", "password", "email", "address", "phone");
		udao.createUser(em, um1);
		TransactionModel tm = new TransactionModel("cardN", "sCoin", "dCoin", 1, new Date(), 1);
		udao.addTransactionToUser(em, tm, um1);
		tm.setCardN("cardN2");
		udao.updateTransaction(em, um1, tm);
		assertEquals(udao.readUserByEmail(em, "email").getUserTransactions().get(0).getCardN(), "cardN2");
	}

	@Test
	public void testUpdateCurrency() {
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um1 = new UsuariosModel("nombre", "password", "email", "address", "phone");
		udao.createUser(em, um1);
		CurrencyBudgetModel cbm = new CurrencyBudgetModel("cardN", "currency", 1);
		udao.addCurrencyBudgetToUser(em, cbm, um1);
		cbm.setCardN("cardN2");
		udao.updateCurrency(em, um1, cbm);
		assertEquals(udao.readUserByEmail(em, "email").getUserCurrencies().get(0).getCardN(), "cardN2");
	}

	@Test
	public void testAddFriend() {
		
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um1 = new UsuariosModel("nombre", "password", "email", "address", "phone");
		udao.createUser(em, um1);
		udao.addFriend(em, um1, "friend");
		assertTrue(udao.readUserByName(em, "nombre").getFriends().contains("friend"));
		em.close();
	}

	@Test
	public void testDeleteFriend() {
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um1 = new UsuariosModel("nombre", "password", "email", "address", "phone");
		udao.createUser(em, um1);
		udao.addFriend(em, um1, "friend");
		udao.deleteFriend(em, um1, "friend");
		assertFalse(udao.readUserByName(em, "nombre").getFriends().contains("friend"));
		em.close();
	}

	@Test
	public void testAddHistoryToUser() {
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um1 = new UsuariosModel("nombre", "password", "email", "address", "phone");
		udao.createUser(em, um1);
		HistoryModel hm = new HistoryModel("cardN", "coin", 10, "type", new Date());
		udao.addHistoryToUser(em, hm, um1);
		assertTrue(udao.readUserByEmail(em, "email").getHistories().contains(hm));
	}

	@Test
	public void testAddCurrencyBudgetToUser() {
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um1 = new UsuariosModel("nombre", "password", "email", "address", "phone");
		udao.createUser(em, um1);
		CurrencyBudgetModel cbm = new CurrencyBudgetModel("cardN", "currency", 1);
		udao.addCurrencyBudgetToUser(em, cbm, um1);
		assertTrue(udao.readUserByEmail(em, "email").getUserCurrencies().contains(cbm));
	}

	@Test
	public void testAddTransactionToUser() {
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um1 = new UsuariosModel("nombre", "password", "email", "address", "phone");
		udao.createUser(em, um1);
		TransactionModel tm = new TransactionModel("cardN", "sCoin", "dCoin", 1, new Date(), 1);
		udao.addTransactionToUser(em, tm, um1);
		assertTrue(udao.readUserByEmail(em, "email").getUserTransactions().contains(tm));
	}

	@Test
	public void testDeleteUserTransaction() {
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um1 = new UsuariosModel("nombre", "password", "email", "address", "phone");
		udao.createUser(em, um1);
		TransactionModel tm = new TransactionModel("cardN", "sCoin", "dCoin", 1, new Date(), 1);
		udao.addTransactionToUser(em, tm, um1);
		udao.deleteUserTransaction(em, tm, um1);
		assertFalse(udao.readUserByEmail(em, "email").getUserTransactions().contains(tm));
	}

	@Test
	public void testDeleteUsuario() {
		EntityManager em = EMFService.get().createEntityManager();
		UsuariosDAOImpl udao = UsuariosDAOImpl.getInstance();
		UsuariosModel um = new UsuariosModel("nombre", "password", "email", "address", "phone");
		udao.createUser(em, um);
		udao.deleteUsuario(em, um);
		assertEquals(udao.readUserByName(em, "nombre"), null);
		em.close();	
	}
	
}
