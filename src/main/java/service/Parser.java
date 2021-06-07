package service;

import model.City;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    public static void parseCities( String path) throws NumberFormatException {
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNext()) {
                String[] cityInfo = scanner.nextLine().split(";");
                String name = cityInfo[1];
                String region = cityInfo[2];
                String district = cityInfo[3];
                int population = Integer.parseInt(cityInfo[4]);
                int foundation = Integer.parseInt(cityInfo[5]);
                if (population < 0 || foundation < 0) {
                    throw new IllegalArgumentException("Значение не может быть отрицательным");
                }

                new CityServiceCRUD().save(new City(Integer.parseInt(cityInfo[0]),name, region, district, population, foundation));
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}
