package br.com.bb.indicadores.service;

import br.com.bb.indicadores.dto.CountryResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.annotation.ApplicationScope;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

/**
 * CountryService marcado como Application scoped, para cachear a lista de Paises
 * */
@Component
@ApplicationScope
public class CountryService {
    private static final String COUNTRY_JSON_PATCH = "countries.json";

    private List<CountryResponse> listaDePaises;

    public List<CountryResponse> getListaDePaises(){
        if(this.listaDePaises==null){
            preencheListaDeCountryDTO();
        }
        return listaDePaises;
    }

    private void preencheListaDeCountryDTO()  {
        ObjectMapper mapper = new ObjectMapper();
        CountryResponse[] arrCountries = null;
        try {
            File file = ResourceUtils.getFile("classpath:"+COUNTRY_JSON_PATCH);
            arrCountries = mapper.readValue(file, CountryResponse[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        listaDePaises = Arrays.asList(arrCountries);
    }

    public boolean codPaisEhValido(String codPais) {
        List<CountryResponse> validos = this.getListaDePaises();
        for (CountryResponse cou : validos){
            if(codPais.equals(cou.getCodeJSON())){ return true; }
        }
        return false;
    }

    public void testResourceFile() throws IOException {
        File resource = new ClassPathResource(COUNTRY_JSON_PATCH).getFile();
        String text = new String(Files.readAllBytes(resource.toPath()));
    }

}