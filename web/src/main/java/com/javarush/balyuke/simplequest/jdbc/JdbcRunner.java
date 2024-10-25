package com.javarush.balyuke.simplequest.jdbc;

import com.javarush.balyuke.simplequest.dao.FlightDao;
import com.javarush.balyuke.simplequest.dao.TicketDao;
import com.javarush.balyuke.simplequest.jdbc.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcRunner {

    public static void main(String[] args) throws SQLException {
        //checkMetaData();

        //var flightDao = FlightDao.getInstance();
        var ticketDao = TicketDao.getInstance();

        //System.out.println(flightDao.findAll());
        System.out.println(ticketDao.findAll());
    }

    private static void checkMetaData() throws SQLException {
        try (Connection connection = ConnectionManager.get()) {
            var metaData = connection.getMetaData();
            var catalogs = metaData.getCatalogs();
            while (catalogs.next()) {
                System.out.println(catalogs.getString(1));
            }
        }
    }
}
