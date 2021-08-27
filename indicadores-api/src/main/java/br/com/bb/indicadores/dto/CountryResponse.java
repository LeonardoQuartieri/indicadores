package br.com.bb.indicadores.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
/**
 * CountryDTO - Usado de duas formas,
 * id+value  = quando vem do ws-worldbank
 * name+code = quando vem do json
 * */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("CountryResponse")
public class CountryResponse {

    @JsonProperty("id")
    @ApiModelProperty(value = "Identificador do país no retorno do WorldBank")
    private String idWorldBank;
    @JsonProperty("value")
    @ApiModelProperty(value = "Valor do atributo no WorldBank")
    private String valueWorldBank;
    @JsonProperty("name")
    @ApiModelProperty(value = "Nome do atributo no JSON")
    private String nameJSON;
    @JsonProperty("code")
    @ApiModelProperty(value = "Codigo do atributo no JSON")
    private String codeJSON;

}
