package com.drumbeatbillings.drumbeat.datatypes;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database implements AutoCloseable {
    final Connection connection;
    public Database(File file) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + (file.isDirectory() ? file.getAbsolutePath() + File.separatorChar + "drumbeat.db" : file.getAbsolutePath()));
    }

    public Database(String address, int port, String database, String username, String password) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" + address + ':' + (port == 0 ? 3306 : port) + '/' + database, username, password);
    }

    public Database(String address, int port, String database) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" + address + ':' + (port == 0 ? 3306 : port) + '/' + database);
    }

    private void init() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE DrumBeat ('id' TINYINT UNSIGNED PRIMARY KEY AUTOINCREMENT, 'first' TEXT, 'last' TEXT, 'company' TEXT, 'email' TEXT, 'address' TEXT, 'phone' TEXT);");
        }
    }

    public void close() throws Exception {
        connection.close();
    }
}
