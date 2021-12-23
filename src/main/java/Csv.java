import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Csv {
    public static List<Country> ParseCsv(String filePath) throws IOException {
        List<Country> countries = new ArrayList<>();
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        fileLines.remove(0);

        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(",");
            ArrayList<String> columnList = new ArrayList<>();

            for (String s : splitedText) {
                if (IsColumnPart(s)) {
                    String lastText = columnList.get(columnList.size() - 1);
                    columnList.set(columnList.size() - 1, lastText + "," + s);
                } else {
                    columnList.add(s);
                }
            }

            Country country = new Country();
            country.Name = columnList.get(0);
            country.Region = columnList.get(1);
            country.HappinessRank = Integer.parseInt(columnList.get(2));
            country.HappinessScore = Double.parseDouble(columnList.get(3));
            country.LowerConfidenceInterval = Double.parseDouble(columnList.get(4));
            country.UpperConfidenceInterval = Double.parseDouble(columnList.get(5));
            country.Economy = Double.parseDouble(columnList.get(6));
            country.Family = Double.parseDouble(columnList.get(7));
            country.Health = Double.parseDouble(columnList.get(8));
            country.Freedom = Double.parseDouble(columnList.get(9));
            country.Trust = Double.parseDouble(columnList.get(10));
            country.Generosity = Double.parseDouble(columnList.get(11));
            country.DystopiaResidual = Double.parseDouble(columnList.get(12));
            countries.add(country);
        }
        return countries;
    }

    private static boolean IsColumnPart(String text) {
        String trimText = text.trim();
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }
}
