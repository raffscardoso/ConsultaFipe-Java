package com.raffs.tabelaFipe.services;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModelosResponseDTO(List<MarcaDTO> modelos, List<AnosDTO> anos) {
}
