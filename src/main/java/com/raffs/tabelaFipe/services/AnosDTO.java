package com.raffs.tabelaFipe.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AnosDTO(String codigo, String nome) implements CodigoNomeDTO {
}
