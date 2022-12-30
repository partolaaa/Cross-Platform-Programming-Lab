package com.partola.PracticeSpringBootApp.dao;

/**
 * @author Ivan Partola
 * @date 07.12.2022
 */
public class DAOFactory {
    private static DAO dao;

    public static DAO getDaoInstance(DataSource dataSource) {
        switch (dataSource) {
            case MY_SQL -> dao = new MySQLDAO();
            case COLLECTION -> dao = new ListDAO();
            default -> dao = null;
        }

        return dao;
    }
}
