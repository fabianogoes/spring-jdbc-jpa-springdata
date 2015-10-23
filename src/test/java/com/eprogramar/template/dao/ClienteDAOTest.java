package com.eprogramar.template.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.eprogramar.template.model.Cliente;
import com.eprogramar.template.repositories.ClienteDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-context-test.xml", "classpath*:persistence-test.xml"})
@Transactional
public class ClienteDAOTest {

	@Autowired
	private ClienteDAO dao;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCRUDInsertCliente() {
		Cliente cliente = new Cliente(null, "testCRUDInsertCliente", "123");
		cliente = dao.save(cliente);
		Assert.assertNotNull( "deve ter id not null apos o save", cliente.getId() );
	}
	
	@Test
	public void testCRUDUpdateCliente() {
		Cliente clienteInsert = new Cliente(null, "testCRUDUpdateCliente", "123");
		clienteInsert = dao.save(clienteInsert);
		Assert.assertNotNull( "deve ter id not null apos o save", clienteInsert.getId() );
		
		clienteInsert.setNome("Update");
		dao.save(clienteInsert);
		
		Cliente clientePosUpdate = dao.findById( clienteInsert.getId(), Cliente.class );
		Assert.assertEquals("cliente no find após o Update deve estar com o nome atualizado", clienteInsert.getNome(), clientePosUpdate.getNome());
	}

	@Test
	public void testCRUDFindByIdCliente(){
		Cliente clienteInsert = new Cliente(null, "testCRUDFindByIdCliente", "123");
		clienteInsert = dao.save(clienteInsert);
		Assert.assertNotNull( clienteInsert.getId() );
		
		Cliente clienteFindById = dao.findById( clienteInsert.getId(), Cliente.class );
		Assert.assertEquals("o id do find deve ser o mesmo do insert", clienteInsert.getId(), clienteFindById.getId());
	}
	
	@Test
	public void testCRUDFindAllCliente(){
		Cliente clienteInsert = new Cliente(null, "testCRUDFindAllCliente", "123");
		clienteInsert = dao.save(clienteInsert);
		Assert.assertNotNull( clienteInsert.getId() );
		
		List<Cliente> list = dao.findAll(Cliente.class);
		Assert.assertNotNull("a lista deve ser diferente de nula", list);
		
		Assert.assertTrue("deve ter pelo menos 1 item na lista, por foi inserido durante o test", list.size() > 0);
	}
	
	@Test
	public void testCRUDRemoveCliente(){
		Cliente clienteInsert = new Cliente(null, "testCRUDRemoveCliente", "123");
		clienteInsert = dao.save(clienteInsert);
		Assert.assertNotNull( clienteInsert.getId() );
		
		dao.remove(clienteInsert.getId(), Cliente.class);
		Cliente findById = dao.findById(clienteInsert.getId(), Cliente.class);
		Assert.assertNull("cliente deve ser nula/Não localizada após o remove", findById);
	}
}
