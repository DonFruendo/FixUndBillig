package fixundbillig.sendungsverwaltung.core.db;

import fixundbillig.sendungsverwaltung.core.config.Configurator;
import fixundbillig.sendungsverwaltung.data.interfaces.ISQLConnector;
import fixundbillig.sendungsverwaltung.data.utils.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class H2Connector implements ISQLConnector {
    private static JdbcConnectionPool pool;

    private Configurator.DB config;

    public H2Connector() {
        config = Configurator.getInstance().database;
        connect();
    }

    @Override
    public void connect() {
        // Establish database connection
        try {
            if (pool == null) {
                pool = JdbcConnectionPool.create(config.url, config.user, config.password);
            }
            Logger.info("SQL connected");
        } catch (Exception e) {
            Logger.err(e.getMessage());
        }
    }

    @Override
    public ResultSet getQuery(String query) {
        try {
            Connection con = pool.getConnection();
            ResultSet resultSet = con.createStatement().executeQuery(query);
            //con.close();
            Logger.info("SQL Query: " + query);
            return resultSet;

        } catch (SQLException e) {
            Logger.err(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean executeStatement(String statement) {
        // Execute statement on database
        try {
            Connection con = pool.getConnection();
            boolean execute = con.createStatement().execute(statement);
            con.close();
            Logger.info("SQL Statement: " + statement);
            return execute;

        } catch (SQLException e) {
            Logger.err(e.getMessage());
        }
        return false;
    }

    @Override
    public void disconnect() {
        if (pool != null) {
            pool.dispose();
        }
    }

    /***
     * Creates a new table with the given {@code name} if it is not existing.
     * A PRIMARY KEY-constraint will be automatically assigned to the first column.<br>
     * Syntax: func("Test", "id int", "name varchar(50)", "city string");
     * @param name the name of the desired table
     * @param felder names and types of all columns
     */
    @Override
    public void createTableIfNotExisting(String name, String... felder) {
        /*CREATE TABLE Packstuek(
                id INT PRIMARY KEY,
                volumen INT,
                refnr INT,
                gewicht INT,
                sendungsnummer VARCHAR(50),
                lagerort VARCHAR(50),
                paketart VARCHAR(10));*/
        String statement = "CREATE TABLE IF NOT EXISTS " + name + "(";
        if (felder.length > 0) {
            for (int i = 0; i < felder.length; i++) {
                String[] strings = felder[i].split(" ");
                if (strings.length == 2) {
                    String colName = strings[0];
                    String type = strings[1].toUpperCase();

                    if ("STRING".equals(type)) {
                        type = "VARCHAR(50)";
                    }

                    statement += colName + " "
                            + type
                            + ((i == 0) ? " PRIMARY KEY" : "")
                            + ((i < felder.length - 1) ? ", " : "");
                }
            }
        }
        statement += ");";
        Logger.info("SQL: " + statement);
        executeStatement(statement);
    }
}
