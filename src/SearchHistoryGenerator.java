import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SearchHistoryGenerator {
    List <Conversion> conversions = new ArrayList<>();

   private final LocalDateTime now = LocalDateTime.now();

   private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");

   private final String currentMoment = now.format(formatter);
    @Override
    public String toString() {

        if (this.conversions.isEmpty()){
            return "Nenhuma conversão foi realizada ainda";
        }else{
            return "{" +
                    "conversions: \n" + conversions +
                    '}';
        }
    }

    public String getHistory(){
        if (this.conversions.isEmpty()){
            return "Nenhuma conversão foi feita ainda";

        }
        return this.conversions.toString();
    }

    public void setLista(Conversion conversion) {
        conversion.setCurrentMoment(currentMoment);
        this.conversions.add(conversion) ;
    }

    public void generateHistory(Conversion conversion){
        try {

            setLista(conversion);
            Gson gson = new Gson();
            FileWriter writer = new FileWriter("History.json");
            String registro = gson.toJson(getHistory() + "\n" + currentMoment);
            writer.write( registro );
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
