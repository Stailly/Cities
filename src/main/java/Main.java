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
    private static CityServiceCRUD service = new CityServiceCRUD();

    public static void main(String[] args) {

        printMenu();
        List<City> cities = new ArrayList<>();
        //  Parser.parseCities(PATH_NAME);
        showMenu(cities);
    }

    private static void showMenu(List<City> cities) {
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
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        switch (response) {
            case 0:
                DirectoryActions.regionsStatistic(cities).forEach((a, b) -> System.out.println(a + " " + b));
                break;
            case 1:
                service.getAll().forEach(System.out::println);
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
                int idDel = Integer.parseInt(reader.readLine());
                service.delete(idDel);
                break;
            case 6:
                int idGet = Integer.parseInt(reader.readLine());
                System.out.println(service.get(idGet));
                break;
            default:
                System.out.println("Введите корректный номер операции.");
        }

        printMenu();
    }

    private static void printMenu() {
        System.out.print("\nВведите номер операции, которую хотите совершить:\n" +
                "1. Вывести содержимое справочника\n" +
                "2. Вывести справочник, отсортированный по названию города\n" +
                "3. Вывести справочник, отсортированный по федеральному округу\n" +
                "4. Вывести индекс города с наибольшим количеством жителей\n" +
                "5. Вывести статистику городов по регионам\n" +
                "5. Удалить город по id. Введите id: \n" +
                "6. Вывести город по id. Введите id: \n" +
                "0. Выход из справочника\n");
    }


}
