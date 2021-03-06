package com.algaworks.brewer.session;



import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.session.TabelaItensVenda;



public class TabelaItensVendaTest {
	
	private TabelaItensVenda tabelaItensVenda;
	
	@Before
	public void setUp() {
		this.tabelaItensVenda = new TabelaItensVenda("1");
	}
	
	@Test
	public void deveCalcularVaorTotaSemItens() throws Exception {		
		assertEquals(BigDecimal.ZERO, tabelaItensVenda.getValorTotal());
		
	}
	
	@Test
	public void deveCalcularValorTotalComUmItem() throws Exception {
		Cerveja cerveja = new Cerveja();
		BigDecimal valor = new BigDecimal("8.90");
		cerveja.setValor(valor);
		
		tabelaItensVenda.adicionarItem(cerveja, 1);
		
		assertEquals(valor, tabelaItensVenda.getValorTotal());
	}
	
	
	@Test
	public void deveCalcularValorTotalComVariosItens() throws Exception {
		Cerveja c1 = new Cerveja();
		c1.setCodigo(1L);
		BigDecimal v1 = new BigDecimal("8.90");
		c1.setValor(v1);
		
		Cerveja c2 = new Cerveja();
		c2.setCodigo(2L);
		BigDecimal v2 = new BigDecimal("8.90");
		c2.setValor(v2);
		
		tabelaItensVenda.adicionarItem(c1, 1);
		tabelaItensVenda.adicionarItem(c2, 2);
		
		assertEquals(new BigDecimal("26.70"), tabelaItensVenda.getValorTotal());
				
	}
	
	@Test
	public void deveManterTamanhoDaListaParaCervejasRepetidas() throws Exception {
		Cerveja c1 = new Cerveja();
		c1.setCodigo(1L);
		c1.setValor(new BigDecimal("4.50"));
		
		tabelaItensVenda.adicionarItem(c1, 1);		
		tabelaItensVenda.adicionarItem(c1, 1);
		assertEquals(1, tabelaItensVenda.total());
		assertEquals(new BigDecimal("9.00"), tabelaItensVenda.getValorTotal());
		
	}
	
	@Test
	public void deveAlterarQuantidadeDigitadaDoItem() throws Exception {
		Cerveja c1 = new Cerveja();
		c1.setCodigo(1L);
		c1.setValor(new BigDecimal("3.30"));
		
		tabelaItensVenda.adicionarItem(c1, 1);
		tabelaItensVenda.alteraQuantidadeItens(c1, 4);
		
		
		assertEquals(1, tabelaItensVenda.total());
		assertEquals(new BigDecimal("13.20"), tabelaItensVenda.getValorTotal());
		
	}
	
	@Test
	public void deveExcluirCerveja() throws Exception {
		
		Cerveja c1 = new Cerveja();
		c1.setCodigo(1L);
		BigDecimal v1 = new BigDecimal("8.90");
		c1.setValor(v1);
		
		Cerveja c2 = new Cerveja();
		c2.setCodigo(2L);
		BigDecimal v2 = new BigDecimal("2.00");
		c2.setValor(v2);
		
		Cerveja c3 = new Cerveja();
		c3.setCodigo(3L);
		BigDecimal v3 = new BigDecimal("1.00");
		c3.setValor(v3);
		
		tabelaItensVenda.adicionarItem(c1, 1);
		tabelaItensVenda.adicionarItem(c2, 1);
		tabelaItensVenda.adicionarItem(c3, 1);
		
		tabelaItensVenda.excluir(c2);
		
		assertEquals(2, tabelaItensVenda.total());
		assertEquals(new BigDecimal("9.90"), tabelaItensVenda.getValorTotal());
	}
}
