import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        var search = new Search();
        var scan = new Scanner(System.in);
        int option = -1;
        while (option != 0){
            Conversion conversion = null;
            System.out.println("""
                *****************************************************************************
                Menu de pesquisa
                0) Sair
                1) Dólar =>> Peso Argentino
                2) Peso argentino =>> Dólar
                3) Dólar =>> Real Brasileiro
                4) Real Brasileiro =>> Dólar
                5) Dólar ==> Peso Colombiano
                6) Peso Colombiano =>> Dólar
                7) Real Brasileiro =>> Libra Esterlina
                8) Real Brasileiro =>> Euro
                9) Pesquisar histórico
                *****************************************************************************
                """);
            option = scan.nextInt();
            if (option == 0){break;}

                conversion = search.currencyConvertion(option);
                if (conversion == null && option !=0) {

                    continue;
                }
                System.out.println("""
                    Informe o valor a ser convertido: 
                    """);
                conversion.convertion(scan.nextDouble());
            System.out.println(conversion);

        }


    }
}
