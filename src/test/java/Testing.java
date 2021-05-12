import comparators.CityNameComparator;
import comparators.DistrictComparator;
import model.City;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Testing {
    private List<City> list;

    @Before
    public void init() {
        list = new ArrayList<>();
        list.add(new City("Горно-Алтайск", "Алтай", "Сибирский", 56928, 1830));
        list.add(new City("Алтымаш", "Алтай", "Сибирский", 1238, 1180));
        list.add(new City("Майкоп", "Адыгея", "Южный", 595628, 1930));
        list.add(new City("Абакан", "Хакасия", "Сибирский", 1798, 1870));
        list.add(new City("Янтыш", "Хакасия", "Сибирский", 1238, 1950));
        list.add(new City("Домодедово", "Московский", "Центральный", 35487, 1798));

    }

    @Test(expected = NumberFormatException.class)
    public void parseTest2() {
        Parser.parseCities(new ArrayList<>(), "src/test/resources/wrongNumber.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseTest3() {
        Parser.parseCities(new ArrayList<>(), "src/test/resources/negativeNumber.txt");

    }

    @Test
    public void sortNameTest() {
        List<City> expect = new ArrayList<>();
        expect.add(new City("Абакан", "Хакасия", "Сибирский", 1798, 1870));
        expect.add(new City("Алтымаш", "Алтай", "Сибирский", 1238, 1180));
        expect.add(new City("Горно-Алтайск", "Алтай", "Сибирский", 56928, 1830));
        expect.add(new City("Домодедово", "Московский", "Центральный", 35487, 1798));
        expect.add(new City("Майкоп", "Адыгея", "Южный", 595628, 1930));
        expect.add(new City("Янтыш", "Хакасия", "Сибирский", 1238, 1950));
        Assert.assertEquals(Main.sortCities(list, new CityNameComparator()), expect);
    }

    @Test
    public void sortDistrictTest() {
        List<City> expect = new ArrayList<>();
        expect.add(new City("Абакан", "Хакасия", "Сибирский", 1798, 1870));
        expect.add(new City("Алтымаш", "Алтай", "Сибирский", 1238, 1180));
        expect.add(new City("Горно-Алтайск", "Алтай", "Сибирский", 56928, 1830));
        expect.add(new City("Янтыш", "Хакасия", "Сибирский", 1238, 1950));
        expect.add(new City("Домодедово", "Московский", "Центральный", 35487, 1798));
        expect.add(new City("Майкоп", "Адыгея", "Южный", 595628, 1930));
        Assert.assertEquals(Main.sortCities(list, new DistrictComparator()), expect);

    }

    @Test
    public void maxPopulateTest() {
        String expected = "[2] = 595628\n";
        Assert.assertEquals(expected,Main.findMaxPopulateCity(list));

    }
    @Test
    public void districtStatTest() {
        Map<String,Integer> expected = new HashMap<>();
        expected.put("Хакасия", 2);
        expected.put("Московский", 1);
        expected.put("Адыгея", 1);
        expected.put("Алтай", 2);
        Map<String,Integer> actual = Main.regionsStatistic(list);
        Assert.assertEquals( expected, actual);
    }
}
