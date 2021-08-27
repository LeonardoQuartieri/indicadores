package br.com.bb.indicadores.client;

import br.com.bb.indicadores.dto.RetornoIndicadoresResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WorldBankClient {

    private static final String URL_SERVICO="http://api.worldbank.org/v2/country/{codPais}/indicator/SI.POV.DDAY?format=json";

    public RetornoIndicadoresResponse buscaIndicadoresPorCodPais(String codPais){
        return new RestTemplate().getForObject(URL_SERVICO, RetornoIndicadoresResponse.class, codPais);
    }

}