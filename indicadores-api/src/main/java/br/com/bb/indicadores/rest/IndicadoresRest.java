package br.com.bb.indicadores.rest;

import br.com.bb.indicadores.dto.CountryResponse;
import br.com.bb.indicadores.dto.RetornoIndicadoresResponse;
import br.com.bb.indicadores.exception.InvalidCountryCodeException;
import br.com.bb.indicadores.exception.ResourceNotFoundException;
import br.com.bb.indicadores.service.CountryService;
import br.com.bb.indicadores.service.IndicadoresService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class IndicadoresRest {
    static final String BARRA_VERSAO="/v1";
    public static final String O_CODIGO_DO_PAIS_EH_INVALIDO = "O codigo do pais eh invalido";
    @Autowired
    private IndicadoresService indicadoresService;
    @Autowired
    private CountryService countryService;

    @ApiOperation(value = "Retorna uma lista de indicadores de pobreza para o pais informado. Ordenado por ano.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de indicadores do pais solicitado"),
            @ApiResponse(code = 404, message = "Registro nao encontrado ou nao é valido"),
            @ApiResponse(code = 500, message = "Exceção durante processamewnto (bem raro :))"),
    })
    @GetMapping(BARRA_VERSAO+"/indicadores/{codPais}")
    public ResponseEntity<RetornoIndicadoresResponse> buscarIndicadoresPorCodPais(@PathVariable(value = "codPais") String codPais)
            throws ResourceNotFoundException,InvalidCountryCodeException {
        if (countryService.codPaisEhValido(codPais)==false){
            throw new InvalidCountryCodeException(O_CODIGO_DO_PAIS_EH_INVALIDO);
        }
        RetornoIndicadoresResponse retorno =  indicadoresService.buscaIndicadoresPorCodPais(codPais);
        return ResponseEntity.ok().body(retorno);
    }

    @ApiOperation(value = "Retorna uma lista de países. Ordenado por alfabeticamente.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de indicadores do pais solicitado"),
            @ApiResponse(code = 404, message = "Registro nao encontrado ou nao é valido"),
            @ApiResponse(code = 500, message = "Exceção durante processamewnto (bem raro :))"),
    })
    @GetMapping(BARRA_VERSAO+"/countries")
    public ResponseEntity<List<CountryResponse>> getAllCountries()
            throws ResourceNotFoundException{
        List<CountryResponse> retorno =  countryService.getListaDePaises();
        return ResponseEntity.ok().body(retorno);
    }
}