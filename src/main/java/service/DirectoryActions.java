package service;

import model.City;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectoryActions {

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
