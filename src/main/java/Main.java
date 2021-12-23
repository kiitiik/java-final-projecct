import java.io.IOException;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = Db.getConnection("jdbc:sqlite:./CountriesStats.sqlite");
            var data = Csv.ParseCsv("./countriesStats.csv");
            Db.Update(connection, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
