import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import br.com.controller.TransacaoController;
import br.com.service.TransacaoService;
import br.com.vo.TransacaoVO;

@ExtendWith(MockitoExtension.class)
public class TransacaoControllerTests {

	@Spy
	@InjectMocks
	private TransacaoController api;
	@Mock
	private TransacaoService service;

	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void execute_Success() throws Exception {
		Mockito.doNothing().when(service).execute(Mockito.any(TransacaoVO.class));
		ResponseEntity<?> result = api.execute(new TransacaoVO());
		assertNotNull(result);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

}
