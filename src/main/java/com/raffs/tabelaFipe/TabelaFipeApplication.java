package com.raffs.tabelaFipe;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.raffs.tabelaFipe.services.ApiClient;
import com.raffs.tabelaFipe.visual.Menu;

@SpringBootApplication
public class TabelaFipeApplication implements CommandLineRunner {

  private final ApiClient apiClient;

  public TabelaFipeApplication(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public static void main(String[] args) {
    SpringApplication.run(TabelaFipeApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    int opcao = -1;

    do {
      Menu.exibirMenu();
      while (!scanner.hasNextInt()) {
        System.out.println("Digite somente numeros.");
        scanner.nextLine();
      }

      opcao = scanner.nextInt();
      scanner.nextLine();

      if (opcao <= 0 || opcao > 3)
        System.out.println("Entre uma opcao valida!");
    } while (opcao <= 0 || opcao > 3);

    String marcasUrl;
    if (opcao == 1)
      marcasUrl = "https://parallelum.com.br/fipe/api/v1/carros/marcas";
    else if (opcao == 2)
      marcasUrl = "https://parallelum.com.br/fipe/api/v1/motos/marcas";
    else
      marcasUrl = "https://parallelum.com.br/fipe/api/v1/caminhoes/marcas";

    apiClient.processarMarcas(marcasUrl);

    System.out.println("Digite o codigo da marca que voce quer ver os modelos: ");
    String codigoMarca = scanner.nextLine();
    apiClient.processarModelos(marcasUrl, codigoMarca);

    System.out.println("Digite o codigo do veiculo para consultar anos disponiveis");
    String codigoVeiculo = scanner.nextLine();
    apiClient.processarAnos(marcasUrl, codigoMarca, codigoVeiculo);

    scanner.close();
  }

}
