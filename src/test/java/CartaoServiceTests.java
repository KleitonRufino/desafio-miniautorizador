import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.model.Cartao;
import br.com.repository.CartaoRepository;
import br.com.service.CartaoService;
import br.com.vo.CartaoVO;

@ExtendWith(MockitoExtension.class)
public class CartaoServiceTests {

	@InjectMocks
	private CartaoService service;
	@Mock
	private CartaoRepository repository;

	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void insert_Success() throws Exception {
		CartaoVO vo = new CartaoVO();
		vo.setNumeroCartao("123");
		vo.setSenha("123");
		Mockito.when(repository.findByNumeroCartao(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
		Mockito.when(repository.save(Mockito.any(Cartao.class))).thenReturn(new Cartao());
		Cartao result = service.insert(vo);
		assertNotNull(result);

		Mockito.verify(repository).save(Mockito.any(Cartao.class));
	}

	@Test
	public void insert_Failed_Cartao_Existente() throws Exception {
		CartaoVO vo = new CartaoVO();
		vo.setNumeroCartao("123");
		vo.setSenha("123");
		Mockito.when(repository.findByNumeroCartao(Mockito.anyLong())).thenReturn(Optional.of(new Cartao()));
		Mockito.when(repository.save(Mockito.any(Cartao.class))).thenReturn(new Cartao());
		Cartao result = service.insert(vo);
		assertNull(result);
	}

	@Test
	public void save() throws Exception {
		CartaoVO vo = new CartaoVO();
		vo.setNumeroCartao("123");
		vo.setSenha("123");
		Mockito.when(repository.save(Mockito.any(Cartao.class))).thenReturn(new Cartao());
		Cartao result = service.insert(vo);
		assertNotNull(result);

		Mockito.verify(repository).save(Mockito.any(Cartao.class));
	}

	@Test
	public void recoverySaldoByNumeroCartao_Success() throws Exception {
		Cartao c = new Cartao();
		c.setSaldo(new BigDecimal("200"));
		Mockito.when(repository.findByNumeroCartao(Mockito.anyLong())).thenReturn(Optional.ofNullable(c));
		BigDecimal result = service.recoverySaldoByNumeroCartao(123L);
		assertNotNull(result);
		assertTrue(c.getSaldo().compareTo(result) == 0);
		Mockito.verify(repository).findByNumeroCartao(Mockito.anyLong());
	}

	@Test
	public void recoverySaldoByNumeroCartao_Failed_Cartao_Existente() throws Exception {
		Mockito.when(repository.findByNumeroCartao(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
		try {
			service.recoverySaldoByNumeroCartao(123L);
			assertTrue(false);
		} catch (Exception e) {
			assertNotNull(e);
		}
		Mockito.verify(repository).findByNumeroCartao(Mockito.anyLong());
	}

	@Test
	public void findByNumeroCartao_Succes() throws Exception {
		Cartao c = new Cartao();
		c.setSaldo(new BigDecimal("200"));
		Mockito.when(repository.findByNumeroCartao(Mockito.anyLong())).thenReturn(Optional.ofNullable(c));
		Cartao result = service.findByNumeroCartao(123L);
		assertNotNull(result);
		assertTrue(c.getSaldo().compareTo(result.getSaldo()) == 0);
		Mockito.verify(repository).findByNumeroCartao(Mockito.anyLong());
	}

	@Test
	public void findByNumeroCartao_Failed_Cartao_Existente() throws Exception {
		Mockito.when(repository.findByNumeroCartao(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
		try {
			service.findByNumeroCartao(123L);
			assertTrue(false);
		} catch (Exception e) {
			assertNotNull(e);
		}
		Mockito.verify(repository).findByNumeroCartao(Mockito.anyLong());

	}
}
