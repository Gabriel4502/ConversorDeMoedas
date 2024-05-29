import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;

public class Search {
    private Integer searchType;
    private SearchHistoryGenerator generator = new SearchHistoryGenerator();

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public Conversion currencyConvertion(int option){
        int localOption = option;
        String json = null;
        Conversion convertion = null;
        var gson = new Gson();

        try {

            switch (localOption){
                case 1:
                   json = requisition("USD", "ARS");
                    break;

                case 2:
                    json =requisition("ARS", "USD");
                    break;

                case 3:
                    json =requisition("USD", "BRL");
                    break;
                case 4:
                    json =requisition("BRL", "USD");
                    break;

                case 5:
                    json =requisition("USD", "COP");
                    break;

                case 6:
                    json =requisition("COP", "USD");
                    break;

                case 7:
                    json =requisition("BRL", "GBP");
                    break;

                case 8:
                    json =requisition("BRL", "EUR");
                    break;

                case 9:
                    System.out.println(this.generator.toString());
                    return null;

                default:
                    System.out.println("Opção inválida");
                    return null;
            }

            ConversionRec convertionRec = gson.fromJson(json, ConversionRec.class);
            convertion = new Conversion(convertionRec);
            this.generator.generateHistory(convertion);



            return convertion;




        } catch (NullPointerException e) {
            throw new RuntimeException("Null pointer" + e.getMessage(), e);
        } catch (IllegalStateException e) {
            throw new RuntimeException( "Illegal state!" + e.getMessage(), e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("Erro no Json"+e.getMessage(), e);
        }catch (InputMismatchException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    private String requisition(String baseCurrency, String targetCurrency){
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI
                    .create(
                            "https://v6.exchangerate-api.com/v6/ff3cb981599c2411babd8dd2/pair/"+ baseCurrency +
                                    "/" + targetCurrency)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (IOException e) {
            throw new RuntimeException("Erro de IO" + e.getMessage(), e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Excecução interrompida" + e.getMessage(), e);
        } catch (NullPointerException e) {
            throw new RuntimeException("Null pointer" + e.getMessage(), e);
        } catch (IllegalStateException e) {
            throw new RuntimeException( "Illegal state!" + e.getMessage(), e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("Erro no Json"+e.getMessage(), e);
        }
    }

}
