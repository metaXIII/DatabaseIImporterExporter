package com.metaxiii.fr;

import com.metaxiii.fr.lib.ConstantRequest;

import java.sql.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static com.metaxiii.fr.lib.ConstantDictionnary.HELLO;
import static com.metaxiii.fr.lib.ConstantDictionnary.WARNING_SUPPORT;
import static com.metaxiii.fr.lib.ConstantRequest.SELECTALL;
import static com.metaxiii.fr.lib.ConstantRequest.SHOWDATABASES;

public class Main {
    private static String      host;
    private static String      database;
    private static Connection  connection;
    private static Connection  connectionbis;
    private static ResultSet   resultats;
    private static Statement   stmt;
    private static String      tabOfResult[][];
    private static Set<String> databaseNameFromSource = new HashSet<>();
    private static Set<String> databaseNameFromDest   = new HashSet<>();
    private static String      url                    = "jdbc:mysql://" + getHost() + "?serverTimezone=UTC";

    public static void main(String[] args) {
//        System.out.println(HELLO.getSentence()
//                                   + WARNING_SUPPORT.getSentence() + "vous souhaitez " +
//                                   "importer" +
//                                   " ou exporter des " +
//                                   "données" +
//                                   " ? \n\t1 - Exporter depuis ce" +
//                                   " " +
//                                   "PC" +
//                                   "\n\t2 - Importer sur ce PC");
//        Scanner scanner = new Scanner(System.in);
//        String  resp    = scanner.nextLine();
//        System.out.println(resp.equals("1") ? "D'accord, vous voulez exporter" : resp.equals("2") ? "d'accord vous " +
//                "voulez importer" : "Merci de suivre les choix énoncés.");
//        if (resp.equals("1") || resp.equals("2")) {
//            if (resp.equals("1")) {
//                System.out.println("Quelle est l'adresse de l'ordinateur sur lequel vous souhaitez exporter ?");
//                //do import
//            } else {
//                System.out.println("Quelle est l'adresse de l'ordinateur depuis lequel vous souhaitez importer");
//                //do export
//            }
//        }
//        System.out.println("Fin du programme.");

                try {
//                    connection = DriverManager.getConnection(url, "root", "root");
//                    doConnect(databaseNameFromSource);
//                    connectionbis = DriverManager.getConnection("jdbc:mysql://localhost/jeux?serverTimezone=UTC",
//                    "root",
//                                                                "root");
//                    stmt          = connectionbis.createStatement();
//                    resultats     = stmt.executeQuery(SELECTALL.getRequest());
//                    while (resultats.next()) {
//                        int columnCount = resultats.getMetaData().getColumnCount();
//                        System.out.println("Nombre de colonnes : " + columnCount);
//                        for (int i = 1; i < columnCount; i++) {
//                            resultats.getMetaData().getColumnTypeName(2);// Obtenir le type de la variable
//                            System.out.print(resultats.getMetaData().getColumnName(i) + ": ");
//                            System.out.print(resultats.getString(i) + " | ");
//                        }
//                        System.out.println();
//                    }

                    extracted();
                } catch (Exception e) {
                    System.out.println("Impossible de se connecter à la base de données source");
                    e.printStackTrace();
                }

    }

    private static void doConnect(Set<String> databaseNameFromSource) throws SQLException {
        stmt      = connection.createStatement();
        resultats = stmt.executeQuery(SHOWDATABASES.getRequest());
        while (resultats.next()) {
            if (!resultats.getString(1).equals("information_schema")
                    && !resultats.getString(1).equals("mysql")
                    && !resultats.getString(1).equals("performance_schema")
                    && !resultats.getString(1).equals("sakila")
                    && !resultats.getString(1).equals("sys")
                    && !resultats.getString(1).equals("world")) {
                databaseNameFromSource.add(resultats.getString(1));
            }
        }
        databaseNameFromSource.forEach(System.out::println);
    }

    private static void extracted() {
        try {
            setDatabase("jeux");
            setHost("");//Adresse du Raspberry PIE
            connection = DriverManager.getConnection("jdbc:mariadb://", "root", "root");
            doConnect(databaseNameFromDest);
        } catch (Exception e) {
            System.out.println("Impossible de se connecter à la base de données destination");
            e.printStackTrace();
        }
    }

    public static String getHost() {
        return host != null ? host : "127.0.0.1";
    }

    public static void setHost(String host) {
        Main.host = host;
    }

    public static String getDatabase() {
        return database != null ?database : "";
    }

    public static void setDatabase(String database) {
        Main.database = database;
    }
}
