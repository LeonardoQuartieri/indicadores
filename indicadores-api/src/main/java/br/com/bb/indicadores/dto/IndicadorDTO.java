package br.com.bb.indicadores.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class IndicadorDTO {

    @ApiModelProperty(value = "O indicador")
    private DadosIndicadorDTO indicador;
    @ApiModelProperty(value = "O país")
    private CountryResponse country;
    @ApiModelProperty(value = "Codigo ISO para ao país")
    private String countryIso3Code;

    @ApiModelProperty(value = "Data do registro do indicador")
    @EqualsAndHashCode.Include()
    private int date;
    @ApiModelProperty(value = "Valor do indicador")
    private Object value;
    @ApiModelProperty(value = "Unidade do indicador")
    private String unit;
    @ApiModelProperty(value = "Status do indicador")
    private String obs_status;
    @ApiModelProperty(value = "Decimal do indicador")
    private int decimal;

}
