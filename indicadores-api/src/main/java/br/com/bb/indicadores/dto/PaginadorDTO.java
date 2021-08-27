package br.com.bb.indicadores.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginadorDTO {

    @ApiModelProperty(value = "Número da Página")
    private int page;
    @ApiModelProperty(value = "Quantidade de páginas")
    private int pages;
    @ApiModelProperty(value = "Total de páginas")
    private int total;
    @ApiModelProperty(value = "Source id")
    private String sourceid;
    @ApiModelProperty(value = "Ultima atualização")
    private String lastupdated;

}
