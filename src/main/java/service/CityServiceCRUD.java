package service;

import DataBase.JDBCUtils;
import model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CityServiceCRUD {
    private static final String SELECT_CITY = "SELECT * FROM cities WHERE id =?";
    private static final String COUNT = "SELECT COUNT(*) as count FROM cities";
    private static final String INSERT_CITY = "INSERT INTO cities (id, name, region, district, population, foundation) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ALL_SQL = "UPDATE cities SET name = ?, region = ?, district = ?, population = ?, foundation = ? WHERE id = ?;";
    public static final String DELETE_FROM_TABLE_SQL = "DELETE FROM cities WHERE id = ?";

    public City get(int id) {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_CITY)) {
            statement.setInt(1, id);
            return getFromBD(statement);
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return null;
    }

    public List<City> getAll() {
        List<City> cities = new ArrayList<>();
        int count = counter();
        for (int i = 0; i < count; i++) {
            City city = get(i);
            if (city != null) {
                cities.add(city);
            }
        }
        return cities;
    }

    public int counter() {
        try (Connection connection = JDBCUtils.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(COUNT);
            ResultSet rs = statement.getResultSet();
            rs.next();
            return rs.getInt("count");
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return -1;
    }


    public void save(City city) {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CITY)) {
            statement.setInt(1, city.getId());
            statement.setString(2, city.getName());
            statement.setString(3, city.getRegion());
            statement.setString(4, city.getDistrict());
            statement.setInt(5, city.getPopulation());
            statement.setInt(6, city.getFoundation());
            statement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    public void update(City city) {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ALL_SQL)) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getRegion());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.setInt(5, city.getFoundation());
            preparedStatement.setInt(6, city.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    public void delete(int id) {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_FROM_TABLE_SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    private City getFromBD(PreparedStatement preparedStatement) throws SQLException {
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String region = rs.getString("region");
        String district = rs.getString("district");
        int population = rs.getInt("population");
        int foundation = rs.getInt("foundation");
        return new City(id, name, region, district, population, foundation);
    }

}
