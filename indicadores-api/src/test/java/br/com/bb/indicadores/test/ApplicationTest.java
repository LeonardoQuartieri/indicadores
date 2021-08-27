package br.com.bb.indicadores.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTest {
	public static final String LISTA_DE_INDICADORES = "listaDeIndicadores";
	public static final String O_CODIGO_DO_PAIS_EH_INVALIDO = "O codigo do pais eh invalido";
	public static final String API_V_1_COUNTRIES = "/api/v1/countries";
	public static final String API_V_1_INDICADORES_XX_INEXISTENTE = "/api/v1/indicadores/XX";
	public static final String API_V_1_INDICADORES_BR = "/api/v1/indicadores/BR";

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testBuscarPaises() {
		Object listaDePaises = buscarPaises();
		assertNotNull(listaDePaises.toString());
	}

	@Test
	public void testBuscarIndicadoresPorPaisExistente() {
		Object objRetornado = buscarIndicadoresDoBrasil();
		assertThat(objRetornado.toString().contains(LISTA_DE_INDICADORES));
	}

	@Test
	public void testBuscarIndicadoresPorPaisInexistente() {
		Object objRetornado = buscarPaisInexistente();
		assertThat(objRetornado.toString().contains(O_CODIGO_DO_PAIS_EH_INVALIDO));

	}

	private String buscarPaisInexistente() {
		return buscarNoServico(API_V_1_INDICADORES_XX_INEXISTENTE);
	}

	private String buscarPaises() {
		return buscarNoServico(API_V_1_COUNTRIES);
	}

	private String buscarIndicadoresDoBrasil() {
		return buscarNoServico(API_V_1_INDICADORES_BR);
	}

	private String buscarNoServico(String recurso){
		return this.restTemplate.getForObject(getServico()+ recurso, String.class);
	}

	private String getServico() {
		return "http://localhost:" + port;
	}

}
