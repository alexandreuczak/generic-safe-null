package br.com.safenull.test;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.safenull.SafeNullFactory;
import br.com.safenull.exception.SafeNullException;
import br.com.safenull.test.entidades.Email;
import br.com.safenull.test.entidades.Entidade;
import br.com.safenull.test.entidades.Lista;
import br.com.safenull.test.entidades.Pessoa;

public class SafeNullTest {

	private static final String EMAIL = "teste@teste.com.br";

	@Test
	public void deveCriarInstanciaSemUsarVariavelBase() {
		Pessoa pessoa = new SafeNullFactory().getInstance().create(Pessoa.class);

		Assert.assertNotNull(pessoa);		
		Assert.assertNotNull(pessoa.getEndereco());
		Assert.assertNotNull(pessoa.getEndereco().getPais());
		Assert.assertNotNull(pessoa.getEndereco().getPais().getEstado());
		Assert.assertNotNull(pessoa.getEndereco().getPais().getEstado().getCidade());
		Assert.assertNotNull(pessoa.getEndereco().getPais().getEstado().getCidade().getBairro());
		
		Assert.assertNotNull(pessoa.getEmail());
		Assert.assertNotNull(pessoa.getTelefone());
		Assert.assertNotNull(pessoa.getJob());
		Assert.assertNotNull(pessoa.getTelefone().getDdd());
		
		Assert.assertNotNull(pessoa.getEmailPadrao());
	}

	@Test
	public void naoDeveTerAtributosComplexosNulosEDeveManterValoresPreDefinidos() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCodigo(1);
		List<Email> emails = new ArrayList<Email>();
		Email email = new Email();
		email.setEmail(EMAIL);
		emails.add(email);
		pessoa.setEmail(emails);
		
		pessoa = new SafeNullFactory().getInstance().create(pessoa, Pessoa.class);

		Assert.assertEquals(pessoa.getEmail().size(), 1);
		Assert.assertNotNull(pessoa.getEmail().get(0));
		Assert.assertEquals(pessoa.getEmail().get(0).getEmail(), EMAIL);

	}

	@Test
	public void naoDeveSerNullUsandoVariavelBase() {
		Pessoa pessoa = null;

		pessoa = new SafeNullFactory().getInstance().create(pessoa, Pessoa.class);

		Assert.assertNotNull(pessoa);
		Assert.assertNotNull(pessoa.getEndereco());
		Assert.assertNotNull(pessoa.getEndereco().getPais());
		Assert.assertNotNull(pessoa.getEndereco().getPais().getEstado());
		Assert.assertNotNull(pessoa.getEndereco().getPais().getEstado().getCidade());
		Assert.assertNotNull(pessoa.getEndereco().getPais().getEstado().getCidade().getBairro());
		Assert.assertNotNull(pessoa.getEmail());
		Assert.assertNotNull(pessoa.getTelefone());
		Assert.assertNotNull(pessoa.getTelefone().getDdd());

		Assert.assertNotNull(pessoa.getJob());

		Assert.assertNotNull(pessoa.getEmailPadrao());
	}

	@Test
	public void naoDeveCriarInstanciaDeAtributosDosPacotesIgnorados() {
		Pessoa pessoa = new SafeNullFactory().ignorePackages("br.com.safenull.test.entidades.extras").getInstance().create(Pessoa.class);

		Assert.assertNotNull(pessoa);
		Assert.assertNotNull(pessoa.getEndereco());
		Assert.assertNotNull(pessoa.getEndereco().getPais());
		Assert.assertNotNull(pessoa.getEndereco().getPais().getEstado());
		Assert.assertNotNull(pessoa.getEndereco().getPais().getEstado().getCidade());
		Assert.assertNotNull(pessoa.getEndereco().getPais().getEstado().getCidade().getBairro());
		Assert.assertNotNull(pessoa.getEmail());
		Assert.assertNotNull(pessoa.getTelefone());
		Assert.assertNotNull(pessoa.getTelefone().getDdd());
	
		Assert.assertNotNull(pessoa.getEmailPadrao());
		
		Assert.assertNull(pessoa.getJob());
	}
	
	@Test(expectedExceptions = {SafeNullException.class})
	public void deveLancarExcecaoQuandoEntidadeForDoPacoteJavaLang(){
		new SafeNullFactory().getInstance().create(Integer.class);
	}
	
	@Test
	public void deveIgnorarAtributoDoPacoteJavaLang(){
		Entidade entidade = new SafeNullFactory().getInstance().create(Entidade.class);
		
		Assert.assertNotNull(entidade);		
		
		Assert.assertNull(entidade.getCodigo());
		Assert.assertNotNull(entidade.getEmailPadrao());
		
	}
	
	@Test
	public void deveInstanciarItensDataLista(){
		Lista lista = new Lista();
		lista.setEmails(new ArrayList<Email>());
		
		lista.getEmails().add(new Email());
		
		lista = new SafeNullFactory().getInstance().create(lista, Lista.class);
		
		Assert.assertNotNull(lista);
		
	}
	
	

}
