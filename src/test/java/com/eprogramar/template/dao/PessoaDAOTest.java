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

import com.eprogramar.template.model.Pessoa;
import com.eprogramar.template.repositories.PessoaDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-context-test.xml", "classpath*:persistence-test.xml"})
@Transactional
public class PessoaDAOTest {

	@Autowired
	private PessoaDAO dao;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCRUDInsertPessoa() {
		Pessoa pessoa = new Pessoa(null, "testCRUDInsertPessoa");
		pessoa = dao.save(pessoa);
		Assert.assertNotNull( "deve ter id not null apos o save", pessoa.getId() );
	}
	
	@Test
	public void testCRUDUpdatePessoa() {
		Pessoa pessoaInsert = new Pessoa(null, "testCRUDUpdatePessoa");
		pessoaInsert = dao.save(pessoaInsert);
		Assert.assertNotNull( "deve ter id not null apos o save", pessoaInsert.getId() );
		
		pessoaInsert.setNome("Update");
		dao.save(pessoaInsert);
		
		Pessoa pessoaPosUpdate = dao.findById( pessoaInsert.getId(), Pessoa.class );
		Assert.assertEquals("pessoa no find após o Update deve estar com o nome atualizado", pessoaInsert.getNome(), pessoaPosUpdate.getNome());
	}

	@Test
	public void testCRUDFindByIdPessoa(){
		Pessoa pessoaInsert = new Pessoa(null, "testCRUDFindByIdPessoa");
		pessoaInsert = dao.save(pessoaInsert);
		Assert.assertNotNull( pessoaInsert.getId() );
		
		Pessoa pessoaFindById = dao.findById( pessoaInsert.getId(), Pessoa.class );
		Assert.assertEquals("o id do find deve ser o mesmo do insert", pessoaInsert.getId(), pessoaFindById.getId());
	}
	
	@Test
	public void testCRUDFindAllPessoa(){
		Pessoa pessoaInsert = new Pessoa(null, "testCRUDFindAllPessoa");
		pessoaInsert = dao.save(pessoaInsert);
		Assert.assertNotNull( pessoaInsert.getId() );
		
		List<Pessoa> list = dao.findAll(Pessoa.class);
		Assert.assertNotNull("a lista deve ser diferente de nula", list);
		
		Assert.assertTrue("deve ter pelo menos 1 item na lista, por foi inserido durante o test", list.size() > 0);
	}
	
	@Test
	public void testCRUDRemovePessoa(){
		Pessoa pessoaInsert = new Pessoa(null, "testCRUDRemovePessoa");
		pessoaInsert = dao.save(pessoaInsert);
		Assert.assertNotNull( pessoaInsert.getId() );
		
		dao.remove(pessoaInsert.getId(), Pessoa.class);
		Pessoa findById = dao.findById(pessoaInsert.getId(), Pessoa.class);
		Assert.assertNull("pessoa deve ser nula/Não localizada após o remove", findById);
	}
}
