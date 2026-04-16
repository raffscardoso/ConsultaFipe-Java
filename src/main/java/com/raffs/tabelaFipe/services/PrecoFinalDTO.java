package com.raffs.tabelaFipe.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PrecoFinalDTO(String Valor, String Modelo, String AnoModelo) {
}
