package ua.nure.andriimatiashov.kpp.example.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DAOFactory {
    public static TeacherIDAO getTeacherDAOInstance(TypeDAO typeDAO){
        TeacherIDAO dao;
        switch (typeDAO){
            case COLLECTION:
                dao = new CollectionTeacherDAO();
                break;
            case MySQL:
                dao = new MySQLTeacherDAO(new DAOConfig(
                        "jdbc:mysql://localhost:3306/kpp_labs?serverTimezone=UTC",
                        "bestuser",
                        "bestuser",
                        "MySQL"));
                break;
            default:
                throw new RuntimeException("Unknown DAO type");
        }
        return dao;
    }
    public static ScheduleIDAO getScheduleDAOInstance(TypeDAO typeDAO){
        ScheduleIDAO dao;
        switch (typeDAO){
            case COLLECTION:
                dao = new CollectionScheduleDAO();
                break;
            case MySQL:
                dao = new MySQLScheduleDAO(new DAOConfig(
                        "jdbc:mysql://localhost:3306/kpp_labs?serverTimezone=UTC",
                        "bestuser",
                        "bestuser",
                        "MySQL"));
                break;
            default:
                throw new RuntimeException("Unknown DAO type");
        }
        return dao;
    }

    public static DisciplineIDAO getDisciplineDAOInstance(TypeDAO typeDAO){
        DisciplineIDAO dao;
        switch (typeDAO){
            case COLLECTION:
                dao = new CollectionDisciplineDAO();
                break;
            case MySQL:
                dao = new MySQLDisciplineDAO(new DAOConfig(
                        "jdbc:mysql://localhost:3306/kpp_labs?serverTimezone=UTC",
                        "bestuser",
                        "bestuser",
                        "MySQL"));
                break;
            default:
                throw new RuntimeException("Unknown DAO type");
        }
        return dao;
    }

    @Bean
    public DisciplineIDAO getDisciplineDAOInstance(DAOConfig config){
        DisciplineIDAO dao;
        switch (TypeDAO.valueOf(config.getTypeDAO())){
            case MySQL:
                dao = new MySQLDisciplineDAO(config);
                break;
            case COLLECTION:
                dao = new CollectionDisciplineDAO();
                break;
            default:
                throw new RuntimeException("Unknown DAO type");
        }
        return dao;
    }

    @Bean
    public TeacherIDAO getTeacherDAOInstance(DAOConfig config){
        TeacherIDAO dao;
        switch (TypeDAO.valueOf(config.getTypeDAO())){
            case MySQL:
                dao = new MySQLTeacherDAO(config);
                break;
            case COLLECTION:
                dao = new CollectionTeacherDAO();
                break;
            default:
                throw new RuntimeException("Unknown DAO type");
        }
        return dao;
    }
    @Bean
    public ScheduleIDAO getScheduleDAOInstance(DAOConfig config){
        ScheduleIDAO dao;
        switch (TypeDAO.valueOf(config.getTypeDAO())){
            case MySQL:
                dao = new MySQLScheduleDAO(config);
                break;
            case COLLECTION:
                dao = new CollectionScheduleDAO();
                break;
            default:
                throw new RuntimeException("Unknown DAO type");
        }
        return dao;
    }
}
