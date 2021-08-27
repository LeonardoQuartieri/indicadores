package br.com.bb.indicadores.service;

import br.com.bb.indicadores.client.WorldBankClient;
import br.com.bb.indicadores.dto.IndicadorDTO;
import br.com.bb.indicadores.dto.RetornoIndicadoresResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class IndicadoresService {

    @Autowired
    private WorldBankClient worldBankClient;

    public RetornoIndicadoresResponse buscaIndicadoresPorCodPais(String codPais){
        RetornoIndicadoresResponse retorno = worldBankClient.buscaIndicadoresPorCodPais(codPais);
        // deixa elementos unicos
        Set<IndicadorDTO> setIndicadorDTO = new HashSet<IndicadorDTO>(retorno.getListaDeIndicadores());
        //Aplica ordenação
        List<IndicadorDTO> sortedList = new ArrayList<>(setIndicadorDTO).stream()
                .sorted(Comparator.comparingInt(IndicadorDTO::getDate).reversed())
                .collect(Collectors.toList());
        retorno.setListaDeIndicadores(sortedList);
        return retorno;
    }
}