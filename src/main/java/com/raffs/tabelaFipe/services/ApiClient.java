package com.raffs.tabelaFipe.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.raffs.tabelaFipe.visual.Menu;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
public class ApiClient {

  private final HttpClient client = HttpClient.newHttpClient();
  private final ObjectMapper objectMapper = new ObjectMapper();

  public void processarMarcas(String url) {
    try {
      String responseBody = executarGet(url);

      List<MarcaDTO> marcas = objectMapper.readValue(
          responseBody,
          new TypeReference<List<MarcaDTO>>() {
          });

      Menu.exibirMarcas(marcas);

    } catch (Exception e) {
      System.err.println("Erro ao processar marcas: " + e.getMessage());
    }
  }

  public void processarModelos(String marcasUrl, String codigoMarca) {
    String modelosUrl = marcasUrl + "/" + codigoMarca + "/modelos";

    try {
      String responseBody = executarGet(modelosUrl);

      ModelosResponseDTO modelos = objectMapper.readValue(responseBody, ModelosResponseDTO.class);

      Menu.exibirModelos(modelos.modelos());

    } catch (Exception e) {
      System.err.println("Erro ao processar modelos: " + e.getMessage());
    }

  }

  public void processarAnos(String marcasUrl, String codigoMarca, String codigoVeiculo) {
    String anosUrl = marcasUrl + "/" + codigoMarca + "/modelos/" + codigoVeiculo + "/anos";

    try {
      String responseBody = executarGet(anosUrl);

      List<AnosDTO> anos = objectMapper.readValue(responseBody, new TypeReference<List<AnosDTO>>() {
      });

      Menu.exibirAnos(anos);

    } catch (Exception e) {
      System.err.println("Erro ao processar anos: " + e.getMessage());
    }

  }

  public void processarPrecos(String marcasUrl, String codigoMarca, String codigoVeiculo, String idAno) {
    String precoUrl = marcasUrl + "/" + codigoMarca + "/modelos/" + codigoVeiculo + "/anos/" + idAno;

    try {
      String responseBody = executarGet(precoUrl);

      PrecoFinalDTO modelo = objectMapper.readValue(responseBody, PrecoFinalDTO.class);

      Menu.exibirDetalhesCompletos(modelo);

    } catch (Exception e) {
      System.err.println("Erro ao processar precos: " + e.getMessage());
    }
  }

  private String executarGet(String endpoint) throws Exception {
    HttpRequest request = HttpRequest.newBuilder(URI.create(endpoint)).GET().build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    if (response.statusCode() != 200) {
      throw new IllegalStateException("API retornou status " + response.statusCode() + " para " + endpoint);
    }

    return response.body();
  }

}
