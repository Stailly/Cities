import comparators.CityNameComparator;
import comparators.DistrictComparator;
import model.City;
import service.Parser;

import java.util.*;

public class Main {
    private static final String PATH_NAME = "src/main/resources/Cities.txt";

    public static void main(String[] args) {
        // Модуль 1
        List<City> cities = new ArrayList<>();

        Parser.parseCities(cities, PATH_NAME);
        cities.forEach(System.out::println);

        // Модуль 2
        sortCities(cities, new CityNameComparator()).forEach(System.out::println);
        sortCities(cities, new DistrictComparator()).forEach(System.out::println);

        //Модуль 3

        System.out.println(findMaxPopulateCity(cities));
        //Модуль 4 Необходимо определить количество городов в разрезе регионов.
        regionsStatistic(cities).forEach((a, b) -> System.out.println(a + " " + b));
    }

    public static Map<String, Integer> regionsStatistic(List<City> cities) {
        Map<String, Integer> regionsMap = new HashMap<>();
        cities.forEach(city -> regionsMap.merge(city.getRegion(), 1, (a, val) -> val + 1));
        return regionsMap;
    }


    public static String findMaxPopulateCity(List<City> cities) {
        City[] citiesArray = new City[cities.size()];
        for (int i = 0; i < cities.size(); i++) {
            citiesArray[i] = cities.get(i);
        }
        long maxPopulation = 0;

        for (City city : citiesArray) {
            if (city.getPopulation() > maxPopulation) {
                maxPopulation = city.getPopulation();
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < citiesArray.length; i++) {
            if (citiesArray[i].getPopulation() == maxPopulation) {
                result.append(String.format("[%d] = %d%n", i, maxPopulation));
            }
        }
        return result.toString();
    }


    public static List<City> sortCities(List<City> cities, Comparator<City> comparator) {
        cities.sort(comparator);
        return cities;
    }

}
