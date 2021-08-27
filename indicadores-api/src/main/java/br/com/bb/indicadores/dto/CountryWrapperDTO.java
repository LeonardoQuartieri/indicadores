package br.com.bb.indicadores.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryWrapperDTO {
    private List<CountryResponse> lista;

}
