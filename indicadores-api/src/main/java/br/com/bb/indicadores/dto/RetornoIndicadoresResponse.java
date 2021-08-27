package br.com.bb.indicadores.dto;

import br.com.bb.indicadores.dto.serializers.RetornoIndicadoresDTOJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = RetornoIndicadoresDTOJsonDeserializer.class)
public class RetornoIndicadoresResponse implements Serializable {
    @ApiModelProperty(value = "Paginador")
    private PaginadorDTO paginador;
    @ApiModelProperty(value = "Lista de indicadores")
    private List<IndicadorDTO> listaDeIndicadores;

}
