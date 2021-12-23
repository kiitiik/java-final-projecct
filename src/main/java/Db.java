import java.sql.*;
import java.util.List;

public class Db {
    public static Connection getConnection(String path){
        try {
            Class.forName("org.sqlite.JDBC");
            var conn = DriverManager.getConnection(path);
            System.out.println("Connected to database successfully");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void Update(Connection connection, List<Country> data) {
        Connection conn;
        Statement statement;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:./CountriesStats.sqlite");
            statement = conn.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS CountriesStats");
            String create_sql = "CREATE TABLE CountriesStats " +
                    "(NAME  CHAR(30)   PRIMARY KEY NOT NULL, " +
                    "REGION CHAR(50)   NOT NULL," +
                    "HAPPINESS_RANK INT        NOT NULL, " +
                    "HAPPINESS_SCORE    DOUBLE        NOT NULL, " +
                    "LOWER_CONFIDENCE_INTERVAL   DOUBLE        NOT NULL, " +
                    "UPPER_CONFIDENCE_INTERVAL    DOUBLE        NOT NULL, " +
                    "ECONOMY    DOUBLE        NOT NULL, " +
                    "FAMILY    DOUBLE        NOT NULL, " +
                    "HEALTH    DOUBLE        NOT NULL, " +
                    "FREEDOM    DOUBLE        NOT NULL, " +
                    "TRUST    DOUBLE        NOT NULL, " +
                    "GENEROSITY    DOUBLE        NOT NULL, " +
                    "DYSTOPIA_RESIDUAL  DOUBLE     NOT NULL )";
            statement.executeUpdate(create_sql);
            int i = 0;

            while (i < data.size()){
                var country = data.get(i);
                var name = country.Name;
                var region = country.Region;
                var happinessRank = country.HappinessRank;
                var happinessScore = country.HappinessScore;
                var lowerConfidenceInterval = country.LowerConfidenceInterval;
                var upperConfidenceInterval = country.UpperConfidenceInterval;
                var economy = country.Economy;
                var family = country.Family;
                var health = country.Health;
                var freedom = country.Freedom;
                var trust = country.Trust;
                var generosity = country.Generosity;
                var dystopiaResidual = country.DystopiaResidual;

                String query = "INSERT INTO CountriesStats VALUES (" +
                        "'" + name + "', " +
                        "'" + region + "', " +
                        "'" + happinessRank +  "', " +
                        "'" + happinessScore +  "', " +
                        "'" + lowerConfidenceInterval +  "', " +
                        "'" + upperConfidenceInterval +  "', " +
                        "'" + economy +  "', " +
                        "'" + family +  "', " +
                        "'" + health +  "', " +
                        "'" + freedom +  "', " +
                        "'" + trust +  "', " +
                        "'" + generosity +  "', " +
                        "'" + dystopiaResidual + "')";
                statement.addBatch(query);
                i++;
            }
            statement.executeBatch();
            statement.close();
            conn.close();
            System.out.printf("Successfully updated %s lines%n", i);
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
