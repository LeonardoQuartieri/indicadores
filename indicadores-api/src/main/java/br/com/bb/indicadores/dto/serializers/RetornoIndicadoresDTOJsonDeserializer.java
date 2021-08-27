package br.com.bb.indicadores.dto.serializers;

import br.com.bb.indicadores.dto.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.var;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class RetornoIndicadoresDTOJsonDeserializer extends JsonDeserializer<RetornoIndicadoresResponse> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public RetornoIndicadoresResponse deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final var response =  RetornoIndicadoresResponse.builder().build();
        final var treeNode = (JsonNode) jsonParser.getCodec().readTree(jsonParser);
        List json = mapper.readerFor(new TypeReference<List<Object>>() {
        }).readValue(treeNode);
        final var pagination = (LinkedHashMap<String, Object>) json.get(0);
        final var paginationDTO = PaginadorDTO.builder().build();
        paginationDTO.setTotal((Integer) pagination.get("total"));
        paginationDTO.setLastupdated((String) pagination.get("lastupdated"));
        paginationDTO.setPage((Integer) pagination.get("page"));
        paginationDTO.setPages((Integer) pagination.get("pages"));
        response.setPaginador(paginationDTO);
        List<IndicadorDTO> listaFinalDeIndicadores = new ArrayList<>();

        for(LinkedHashMap obj : (List<LinkedHashMap<String, Object>>) json.get(1)){
            for(Object s : obj.values()){
                final var indicator = IndicadorDTO.builder().build();
                indicator.setCountryIso3Code((String) obj.get("countryiso3code"));
                indicator.setDate(Integer.valueOf(""+obj.get("date")));
                indicator.setUnit((String) obj.get("unit"));
                indicator.setDecimal((Integer) obj.get("decimal"));
                indicator.setIndicador(preencheObjDadosIndicadorDTO((LinkedHashMap<String, Object>) obj.get("indicator")));
                indicator.setCountry(preencheObjCountryDTO((LinkedHashMap<String, Object>) obj.get("country")));

                listaFinalDeIndicadores.add(indicator);
            }
        }
        response.setListaDeIndicadores(listaFinalDeIndicadores);
        return response;
    }

    private DadosIndicadorDTO preencheObjDadosIndicadorDTO(LinkedHashMap<String, Object> indicatorInterno) {
        DadosIndicadorDTO retorno = DadosIndicadorDTO.builder().build();
        retorno.setId((String) indicatorInterno.get("id"));
        retorno.setValue((String) indicatorInterno.get("value"));
        return retorno;
    }

    private CountryResponse preencheObjCountryDTO(LinkedHashMap<String, Object> country) {
        CountryResponse retorno = CountryResponse.builder().build();
        retorno.setIdWorldBank((String) country.get("id"));
        retorno.setValueWorldBank((String) country.get("value"));
        return retorno;
    }
}