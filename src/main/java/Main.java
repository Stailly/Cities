import comparators.*;
import model.City;
import service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static service.DirectoryActions.findMaxPopulateCity;

public class Main {
    private static final String PATH_NAME = "src/main/resources/Cities.txt";
    private static final CityServiceCRUD SERVICE = new CityServiceCRUD();

    public static void main(String[] args) {
        printMenu();
        showMenu();
    }

    private static void showMenu() {
        List<City> cities = SERVICE.getAll();
        int response;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while ((response = Integer.parseInt(reader.readLine())) != 6) {
                chooseOption(cities, response);
            }
        } catch (NumberFormatException e) {
            System.out.println("Введены некорректные данные");
        } catch (IOException ignored) {
        }
    }

    private static void chooseOption(List<City> cities, int response) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        switch (response) {
            case 1:
                SERVICE.getAll().forEach(System.out::println);
                break;
            case 2:
                DirectoryActions.sortCities(cities, new CityNameComparator()).forEach(System.out::println);
                break;
            case 3:
                DirectoryActions.sortCities(cities, new DistrictComparator()).forEach(System.out::println);
                break;
            case 4:
                System.out.println(findMaxPopulateCity(cities));
                break;
            case 5:
                DirectoryActions.regionsStatistic(cities).forEach((a, b) -> System.out.println(a + " " + b));
                break;
            case 6:
                int idDel = Integer.parseInt(reader.readLine());
                SERVICE.delete(idDel);
                break;
            case 7:
                int idGet = Integer.parseInt(reader.readLine());
                System.out.println(SERVICE.get(idGet));
                break;
            case 8:
                Parser.parseCities(PATH_NAME);
                break;
            default:
                System.out.println("Введите корректный номер операции.");
        }
        if (response != 0) {
            printMenu();
        }
    }

    private static void printMenu() {
        System.out.print("\nВведите номер операции, которую хотите совершить:\n" +
                "1. Вывести содержимое справочника\n" +
                "2. Вывести справочник, отсортированный по названию города\n" +
                "3. Вывести справочник, отсортированный по федеральному округу\n" +
                "4. Вывести индекс города с наибольшим количеством жителей\n" +
                "5. Вывести статистику городов по регионам\n" +
                "6. Удалить город по id. Введите id: \n" +
                "7. Вывести город по id. Введите id: \n" +
                "8. Заполнить БД из файла \n" +
                "0. Выход из справочника\n");
    }


}
