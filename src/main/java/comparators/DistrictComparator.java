package comparators;

import model.City;

import java.util.Comparator;

public class DistrictComparator implements Comparator<City> {
    @Override
    public int compare(City o1, City o2) {
        if (!o1.getDistrict().equals(o2.getDistrict())) {
            return o1.getDistrict().compareToIgnoreCase(o2.getDistrict());
        }
        else {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }
}
