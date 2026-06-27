package org.infrastructure.database;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static final String CONN_STR = "jdbc:sqlite:/db/kafkaconsumer.db";
    public static final String INITIALIZATION_SCRIPT = "src/main/resources/init.sql";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONN_STR);
    }

    public static void initialize() {
        try (Connection conn = getConnection()) {
            ScriptRunner sr = new ScriptRunner(conn);

            sr.setLogWriter(null);
            sr.setErrorLogWriter(null);

            sr.runScript(new BufferedReader(new FileReader(INITIALIZATION_SCRIPT)));
        } catch (Exception e) {
            System.err.println("Initialization failed: " + e.getMessage());
        }
    }

}
