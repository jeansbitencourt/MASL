/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jean
 */
public class DatabaseControl {

    Connection con;
    String path;

    public DatabaseControl() {
        
    }

    public String pathProgram() {
        String classpath = System.getProperty("java.class.path");
        String[] classpathEntries = classpath.split(File.pathSeparator);

        path = classpathEntries[0].replace("hsqldb.jar", "").replace("MASL.jar", "");
        return path;
    }

    public Connection dbConnect() {

        path = pathProgram();

        //JOptionPane.showMessageDialog(null, path);
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:file:" + path + "/db", "sa", "");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o driver JDBC. ");
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e);
        }
        return con;
    }

}
