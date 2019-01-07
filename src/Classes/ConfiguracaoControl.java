/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jean
 */
public class ConfiguracaoControl {

    DatabaseControl nova;
    Connection con;
    Configuracao userName;
    String user;
    String dir;

    public Connection dbConnect() {
        nova = new DatabaseControl();
        con = nova.dbConnect();
        return con;
    }

    public void changeUserName(Configuracao userName) {
        try {
            con = dbConnect();
            PreparedStatement stm = con.prepareStatement("UPDATE configuracoes SET valor=? WHERE opcao=?");
            stm.setString(1, userName.getUserName());
            stm.setString(2, "username");
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RegistroControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void changeDir(Configuracao dir) {
        try {
            con = dbConnect();
            PreparedStatement stm = con.prepareStatement("UPDATE configuracoes SET valor=? WHERE opcao=?");
            stm.setString(1, dir.getDir());
            stm.setString(2, "dir");
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RegistroControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String setaUserName() {
        try {
            con = dbConnect();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM configuracoes WHERE opcao='username'");
            rs.next();
            user = rs.getString("valor");
            stm.execute("SHUTDOWN");
            return user;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RegistroControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public String setaDir() {
        try {
            con = dbConnect();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM configuracoes WHERE opcao='dir'");
            rs.next();
            dir = rs.getString("valor");
            stm.execute("SHUTDOWN");
            return dir;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RegistroControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void resetDatabase() {
        try {
            con = dbConnect();
            Statement stm = con.createStatement();
            stm.executeQuery("DELETE FROM registros");
            stm.executeQuery("ALTER TABLE registros ALTER COLUMN id RESTART WITH 0");
           // stm.executeQuery("INSERT INTO registros (nome, ano, generos, temporada, tipo, epitotal, epiatual, nota, descricao) VALUES ('Registro de teste', '0', '-', '-', '-', '0', '0', '0', 'Primeiro registro do programa, edite ou exclua...')");
            stm.execute("SHUTDOWN");

            DatabaseControl conf = new DatabaseControl();
            String path = conf.pathProgram();
            File dir;
            dir = new File(path + "/SaveFiles/");

            for (File file : dir.listFiles()) {
                if (!file.isDirectory()) {
                    file.delete();
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RegistroControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
