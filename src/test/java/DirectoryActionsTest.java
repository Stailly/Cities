import comparators.*;
import model.City;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.DirectoryActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectoryActionsTest {
    private List<City> list;

    @Before
    public void init() {
        list = new ArrayList<>();
        list.add(new City(1, "Горно-Алтайск", "Алтай", "Сибирский", 56928, 1830));
        list.add(new City(2, "Алтымаш", "Алтай", "Сибирский", 1238, 1180));
        list.add(new City(3, "Майкоп", "Адыгея", "Южный", 595628, 1930));
        list.add(new City(4, "Абакан", "Хакасия", "Сибирский", 1798, 1870));
        list.add(new City(5, "Янтыш", "Хакасия", "Сибирский", 1238, 1950));
        list.add(new City(6, "Домодедово", "Московский", "Центральный", 35487, 1798));

    }

    @Test
    public void sortNameTest() {
        List<City> expect = new ArrayList<>();
        expect.add(new City(4, "Абакан", "Хакасия", "Сибирский", 1798, 1870));
        expect.add(new City(2, "Алтымаш", "Алтай", "Сибирский", 1238, 1180));
        expect.add(new City(1, "Горно-Алтайск", "Алтай", "Сибирский", 56928, 1830));
        expect.add(new City(6, "Домодедово", "Московский", "Центральный", 35487, 1798));
        expect.add(new City(3, "Майкоп", "Адыгея", "Южный", 595628, 1930));
        expect.add(new City(5, "Янтыш", "Хакасия", "Сибирский", 1238, 1950));
        Assert.assertEquals(DirectoryActions.sortCities(list, new CityNameComparator()), expect);
    }

    @Test
    public void sortDistrictTest() {
        List<City> expect = new ArrayList<>();
        expect.add(new City(4, "Абакан", "Хакасия", "Сибирский", 1798, 1870));
        expect.add(new City(2, "Алтымаш", "Алтай", "Сибирский", 1238, 1180));
        expect.add(new City(1, "Горно-Алтайск", "Алтай", "Сибирский", 56928, 1830));
        expect.add(new City(5, "Янтыш", "Хакасия", "Сибирский", 1238, 1950));
        expect.add(new City(6, "Домодедово", "Московский", "Центральный", 35487, 1798));
        expect.add(new City(3, "Майкоп", "Адыгея", "Южный", 595628, 1930));
        Assert.assertEquals(DirectoryActions.sortCities(list, new DistrictComparator()), expect);

    }

    @Test
    public void maxPopulateTest() {
        String expected = "[2] = 595628\n";
        Assert.assertEquals(expected, DirectoryActions.findMaxPopulateCity(list));

    }

    @Test
    public void districtStatTest() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("Хакасия", 2);
        expected.put("Московский", 1);
        expected.put("Адыгея", 1);
        expected.put("Алтай", 2);
        Map<String, Integer> actual = DirectoryActions.regionsStatistic(list);
        Assert.assertEquals(expected, actual);
    }
}
