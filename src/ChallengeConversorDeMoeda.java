import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ChallengeConversorDeMoeda {
    public static void main(String[] args) throws IOException, InterruptedException, InstantiationException, IllegalAccessException {
        System.out.println("\nOlá!\nSejam todos bem vindo!\nEste é o seu programa Conversor de Moedas");
        Scanner moeda = new Scanner(System.in);
        System.out.println("\n▼▼▼ Por favor digite uma moeda de origem ▼▼▼\nUSD - Dólar americano\nBRL - Real brasileiro\nARS - Peso argentino\nCOP - Peso colombiano\nCLP - Peso chileno\nBOB - Boliviano boliviano");
        var busca = moeda.nextLine();
        String apiKey = "https://v6.exchangerate-api.com/v6/" + "5628336a85b916ac9d361512" + "/latest/" +busca;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiKey))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        Gson gson = new Gson();
        JsonObject buscaMoeda = gson.fromJson(json, JsonObject.class);
        JsonObject resultadoBusca = buscaMoeda.getAsJsonObject("conversion_rates");
        Scanner conversao = new Scanner(System.in);

        int sair = 0;
        while (sair != 7) {
            String menu = """
                    \n▼▼▼ Escolha a sua moeda de destino: ▼▼▼
                    1 - Dólar americano
                    2 - Real brasileiro
                    3 - Peso argentino
                    4 - Peso colombiano
                    5 - Peso chileno
                    6 - Boliviano boliviano
                    7 - Sair
                    """;
            System.out.println(menu);
            int escolha = moeda.nextInt();
            switch (escolha) {
                case 1:
                    System.out.println("→ 1 " + busca + " esta cotado a " + resultadoBusca.get("USD") + " em Dólar americano.");
                    break;
                case 2:
                    System.out.println("→ 1 " + busca + " esta cotado a " + resultadoBusca.get("BRL") + " em Real brasileiro.");
                    break;
                case 3:
                    System.out.println("→ 1 " + busca + " esta cotado a " + resultadoBusca.get("ARS") + " em Peso argentino.");
                    break;
                case 4:
                    System.out.println("→ 1 " + busca + " esta cotado a " + resultadoBusca.get("COP") + " em Peso colombiano.");
                    break;
                case 5:
                    System.out.println("→ 1 " + busca + " esta cotado a " + resultadoBusca.get("CLP") + " em Peso chileno.");
                    break;
                case 6:
                    System.out.println("→ 1 " + busca + " esta cotado a " + resultadoBusca.get("BOB") + " em Boliano boliviano.");
                    break;
                case 7:
                    sair = 7;
                    System.out.println("Muito obrigado por usar o nosso programa.\nAté a proxima!");
                    break;
                default:
                    System.out.println("▬▬▬ Opção inválida! ▬▬▬");
                    System.out.println("Selecione uma opção válida!");
                    break;
            }
        }
    }
}