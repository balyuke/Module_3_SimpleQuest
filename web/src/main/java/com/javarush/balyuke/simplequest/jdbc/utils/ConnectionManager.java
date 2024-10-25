package com.javarush.balyuke.simplequest.jdbc.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionManager {

    private static final String URL_KEY="db.url";
    private static final String USERNAME_KEY="db.username";
    private static final String PASSWORD_KEY="db.password";
    private static final int DEFAULT_POOL_SIZE = 5;
    private static final String POOL_SIZE_KEY = "db.pool.size";

    private static BlockingQueue<Connection> pool;

    static {
        loadDriver();
        initConnectionPool();
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        }  catch(Exception ex) {
            ex.printStackTrace();
        }
    }


    private static void initConnectionPool() {
        String poolSize = PropertiesUtil.get("db.pool.size");
        int size = poolSize == null ? DEFAULT_POOL_SIZE : Integer.parseInt(poolSize);
        pool = new ArrayBlockingQueue(size);

        for (int i = 0; i < size; i++) {
            Connection connection = open();
            var proxyConnection = (Connection) Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(),
                    new Class[]{Connection.class},
                    (proxy, method, args) -> method.getName().equals("close") ?
                     pool.add((Connection) proxy) :
                        method.invoke(connection, args));
            pool.add(proxyConnection);
        }
    }

    public static Connection get()  {
        try {
            return (Connection) pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    private static Connection open() /*throws SQLException, ClassNotFoundException*/ {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY));
        } catch (SQLException /*| ClassNotFoundException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException*/ e) {
            throw new RuntimeException();
        }
    }



    private ConnectionManager() {

    }
}

