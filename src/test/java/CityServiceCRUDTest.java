import model.City;
import org.junit.Assert;
import org.junit.Test;
import service.CityServiceCRUD;

public class CityServiceCRUDTest {
    private CityServiceCRUD service = new CityServiceCRUD();

    @Test
    public void getTest() {
        City expected = new City(1, "Адыгейск", "Адыгея", "Южный",12248, 1973);
        City actual = service.get(1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTest2() {
        City actual = service.get(-1);
        Assert.assertNull(actual);
    }

    @Test
    public void saveTest() {
        int countBefore = service.counter();
        service.save(new City(157,"Name", "region", "district", 12345, 1234));
        int countAfter = service.counter();
        Assert.assertEquals(1, countAfter-countBefore);
    }

    @Test
    public void deleteTest() {
        int countBefore = service.counter();
        service.delete(157);
        int countAfter = service.counter();
        Assert.assertEquals(1, countBefore-countAfter);
    }

}
