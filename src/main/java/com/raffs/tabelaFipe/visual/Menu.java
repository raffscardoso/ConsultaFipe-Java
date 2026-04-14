package com.raffs.tabelaFipe.visual;

import java.util.List;

import com.raffs.tabelaFipe.services.AnosDTO;
import com.raffs.tabelaFipe.services.CodigoNomeDTO;
import com.raffs.tabelaFipe.services.MarcaDTO;

public class Menu {

  public static void exibirMenu() {

    String menu = """
              Qual tipo de veiculo voce quer consultar?
              (1) Carros
              (2) Motos
              (3) Caminhoes
        """;

    System.out.println(menu);
  }

  public static void exibirMarcas(List<MarcaDTO> marcas) {
    exibirListaCodigoNome(marcas);
  }

  public static void exibirModelos(List<MarcaDTO> modelos) {
    exibirListaCodigoNome(modelos);
  }

  public static void exibirAnos(List<AnosDTO> anos) {
    exibirListaCodigoNome(anos);
  }

  private static void exibirListaCodigoNome(List<? extends CodigoNomeDTO> itens) {
    itens.stream()
        .map(item -> String.format("[%s] - %s", item.codigo(), item.nome()))
        .forEach(System.out::println);
  }
}
