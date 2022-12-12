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
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.controller.CartaoController;
import br.com.exception.ResourceNotFoundException;
import br.com.model.Cartao;
import br.com.service.CartaoService;
import br.com.vo.CartaoVO;

@ExtendWith(MockitoExtension.class)
public class CartaoControllerTests {

	@Spy
	@InjectMocks
	private CartaoController api;
	@Mock
	private CartaoService service;

	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void insert_Success() throws Exception {
		CartaoVO vo = new CartaoVO();
		Mockito.when(service.insert(Mockito.any(CartaoVO.class))).thenReturn(new Cartao());
		ResponseEntity<?> result = api.insert(vo);
		assertNotNull(result);
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		Mockito.verify(service).insert(vo);
	}

	@Test
	public void insert_Failed_Condicoes_Nao_Atendidads() throws Exception {
		CartaoVO vo = new CartaoVO();
		Mockito.when(service.insert(Mockito.any(CartaoVO.class))).thenReturn(null);
		ResponseEntity<?> result = api.insert(vo);
		assertNotNull(result);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, result.getStatusCode());
		Mockito.verify(service).insert(vo);
	}

	@Test
	public void recoverySaldoByNumeroCartao_Success() throws Exception {
		Mockito.when(service.recoverySaldoByNumeroCartao(Mockito.anyLong())).thenReturn(new BigDecimal("500"));
		ResponseEntity<?> result = api.recoverySaldoByNumeroCartao(123L);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(new BigDecimal("500"), result.getBody());
		Mockito.verify(service).recoverySaldoByNumeroCartao(123L);
	}

	@Test
	public void recoverySaldoByNumeroCartao_Failed_CARTAO_INEXISTENTE() throws Exception {
		Mockito.when(service.recoverySaldoByNumeroCartao(Mockito.anyLong()))
				.thenThrow(new ResourceNotFoundException("CARTAO_INEXISTENTE"));
		try {
			api.recoverySaldoByNumeroCartao(123L);
			assertTrue(false);
		} catch (Exception e) {
			assertNotNull(e);
			assertEquals(ResourceNotFoundException.class, e.getClass());
			assertEquals("CARTAO_INEXISTENTE", e.getMessage());
		}
	}
}
