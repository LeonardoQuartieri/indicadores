package br.com.bb.indicadores.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DadosIndicadorDTO {

    @ApiModelProperty(value = "Id do Indicador")
    private String id;
    @ApiModelProperty(value = "Valor do Identificador")
    private String value;

}
