package ua.nure.andriimatiashov.kpp.example.dao;

import ua.nure.andriimatiashov.kpp.example.entity.Discipline;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDisciplineDAO implements DisciplineIDAO {

    private String url;
    private String user;
    private String password;

    private final String SQL_GET_ALL_DISCIPLINES = "SELECT * FROM discipline";
    private final String SQL_GET_DISCIPLINE_BY_ID = "SELECT * FROM discipline WHERE id = ?";
    private final String SQL_ADD_DISCIPLINE = "INSERT INTO discipline (name,hour_per_semester) VALUES (?,?)";
    private final String SQL_UPDATE_DISCIPLINE = "UPDATE discipline SET name = ?, hour_per_semester = ? WHERE id = ?";
    private final String SQL_DELETE_DISCIPLINE_BY_ID = "DELETE FROM discipline WHERE id = ?";

    @Override
    public List<Discipline> getAllDisciplines() {
        List<Discipline> disciplines = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_DISCIPLINES);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(set.getLong("id"));
                discipline.setName(set.getString("name"));
                discipline.setHourPerSemester(set.getInt("hour_per_semester"));
                disciplines.add(discipline);
            }

            connection.commit();
            connection.setAutoCommit(true);

            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplines;
    }

    @Override
    public void saveDiscipline(Discipline discipline) {
        PreparedStatement statement = null;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            if (discipline == null) {
                throw new IllegalArgumentException("Object to save is null.");
            }

            if (discipline.getId() == 0) {
                statement = connection.prepareStatement(SQL_ADD_DISCIPLINE);
            } else {
                statement = connection.prepareStatement(SQL_UPDATE_DISCIPLINE);
                statement.setLong(3, discipline.getId());
            }

            statement.setString(1, discipline.getName());
            statement.setInt(2, discipline.getHourPerSemester());
            statement.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Discipline getDiscipline(long id) {
        Discipline discipline = null;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            PreparedStatement statement = connection.prepareStatement(SQL_GET_DISCIPLINE_BY_ID);

            statement.setLong(1, id);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                discipline = new Discipline();
                discipline.setId(set.getLong("id"));
                discipline.setName(set.getString("name"));
                discipline.setHourPerSemester(set.getInt("hour_per_semester"));
            }

            connection.commit();
            connection.setAutoCommit(true);

            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discipline;
    }

    @Override
    public void deleteDiscipline(long id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_DISCIPLINE_BY_ID);
            statement.setLong(1,id);
            statement.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MySQLDisciplineDAO(DAOConfig config) {
        url = config.getUrl();
        user = config.getUser();
        password = config.getPassword();
    }
}
