import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.model.Cartao;
import br.com.service.CartaoService;
import br.com.service.TransacaoService;
import br.com.vo.TransacaoVO;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTests {

	@InjectMocks
	private TransacaoService service;
	@Mock
	private CartaoService cartaoService;

	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void execute_Success() throws Exception {
		TransacaoVO vo = new TransacaoVO();
		vo.setNumeroCartao(123L);
		vo.setSenha("123");
		vo.setValor(new BigDecimal("100"));

		Cartao c = new Cartao();
		c.setId(1L);
		c.setNumeroCartao(123L);
		c.setSaldo(new BigDecimal("500"));
		c.setSenha("123");

		Cartao c2 = new Cartao();
		c2.setId(1L);
		c2.setNumeroCartao(123L);
		c2.setSaldo(new BigDecimal("400"));
		c2.setSenha("123");

		Mockito.when(cartaoService.findByNumeroCartao(Mockito.anyLong())).thenReturn(c);
		Mockito.when(cartaoService.save(c)).thenReturn(c2);
		service.execute(vo);
		Mockito.verify(cartaoService).findByNumeroCartao(Mockito.anyLong());
		Mockito.verify(cartaoService).save(Mockito.any(Cartao.class));
	}

	@Test
	public void execute_Failed_Senha_Invalida() throws Exception {
		TransacaoVO vo = new TransacaoVO();
		vo.setNumeroCartao(123L);
		vo.setSenha("1234");
		vo.setValor(new BigDecimal("100"));

		Cartao c = new Cartao();
		c.setId(1L);
		c.setNumeroCartao(123L);
		c.setSaldo(new BigDecimal("500"));
		c.setSenha("123");

		Mockito.when(cartaoService.findByNumeroCartao(Mockito.anyLong())).thenReturn(c);

		try {
			service.execute(vo);
			assertTrue(false);
		} catch (Exception e) {
			assertNotNull(e);
			assertEquals("SENHA_INVALIDA", e.getMessage());
		}
		Mockito.verify(cartaoService).findByNumeroCartao(Mockito.anyLong());
	}
	
	@Test
	public void execute_Failed_Saldo_Insuficiente() throws Exception {
		TransacaoVO vo = new TransacaoVO();
		vo.setNumeroCartao(123L);
		vo.setSenha("123");
		vo.setValor(new BigDecimal("600"));

		Cartao c = new Cartao();
		c.setId(1L);
		c.setNumeroCartao(123L);
		c.setSaldo(new BigDecimal("500"));
		c.setSenha("123");

		Mockito.when(cartaoService.findByNumeroCartao(Mockito.anyLong())).thenReturn(c);

		try {
			service.execute(vo);
			assertTrue(false);
		} catch (Exception e) {
			assertNotNull(e);
			assertEquals("SALDO_INSUFICIENTE", e.getMessage());
		}
		Mockito.verify(cartaoService).findByNumeroCartao(Mockito.anyLong());
	}

}
