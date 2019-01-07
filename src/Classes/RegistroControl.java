/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jean
 */
public class RegistroControl {

    Registro object;
    Registro id;
    DatabaseControl nova;
    Connection con;
    int id_busca;

    public Connection dbConnect() {
        nova = new DatabaseControl();
        con = nova.dbConnect();
        return con;
    }

    public void insert(Registro object) {
        try {
            con = dbConnect();
            PreparedStatement stm = con.prepareStatement("INSERT INTO registros (nome, ano, generos, temporada, tipo, epitotal, epiatual, nota, descricao, modificacao, dropado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setString(1, object.getNome());
            stm.setInt(2, object.getAno());
            stm.setString(3, object.getGeneros());
            stm.setString(4, object.getTemporada());
            stm.setString(5, object.getTipo());
            stm.setInt(6, object.getEpitotal());
            stm.setInt(7, object.getEpiatual());
            stm.setInt(8, object.getNota());
            stm.setString(9, object.getDescricao());
            stm.setString(10, object.getUltimaModificacao());
            stm.setString(11, "Não");
            stm.execute();
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

    public void update(Registro object) {
        try {
            con = dbConnect();
            PreparedStatement stm = con.prepareStatement("UPDATE registros SET nome = ?, ano = ?, generos = ?, temporada = ?, tipo = ?, epitotal = ?, epiatual = ?, nota = ?, descricao = ?, modificacao = ?, dropado = ? WHERE id = ?");
            stm.setString(1, object.getNome());
            stm.setInt(2, object.getAno());
            stm.setString(3, object.getGeneros());
            stm.setString(4, object.getTemporada());
            stm.setString(5, object.getTipo());
            stm.setInt(6, object.getEpitotal());
            stm.setInt(7, object.getEpiatual());
            stm.setInt(8, object.getNota());
            stm.setString(9, object.getDescricao());
            stm.setString(10, object.getUltimaModificacao());
            stm.setString(11, object.getDrop());
            stm.setInt(12, object.getId());
            stm.execute();
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

    public void delete(Registro object) {
        try {
            con = dbConnect();
            PreparedStatement stm = con.prepareStatement("DELETE FROM registros WHERE id = ?");
            stm.setInt(1, object.getId());
            stm.execute();
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

    public ArrayList<Registro> selectAll(String orderby) {
        ArrayList<Registro> dados;
        dados = new ArrayList<>();
        try {
            con = dbConnect();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM registros WHERE dropado='Não' ORDER BY " + orderby + " DESC");
            while (rs.next()) {
                dados.add(preencheClasse(rs));
            }
            stm.execute("SHUTDOWN");
            con.close();
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RegistroControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dados;
    }

    public Registro retornaRegistro(int id_busca) {
        try {
            con = dbConnect();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM registros WHERE id=" + id_busca);
            rs.next();
            object = preencheClasse(rs);
            stm.execute("SHUTDOWN");
            con.close();
            return object;
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

    private Registro preencheClasse(ResultSet rs) {
        object = new Registro();
        try {
            object.setUltimaModificacao(rs.getString("modificacao"));
            object.setNome(rs.getString("nome"));
            object.setAno(Integer.parseInt(rs.getString("ano")));
            object.setGeneros(rs.getString("generos"));
            object.setTemporada(rs.getString("temporada"));
            object.setTipo(rs.getString("tipo"));
            object.setDescricao(rs.getString("descricao"));
            object.setEpitotal(Integer.parseInt(rs.getString("epitotal")));
            object.setEpiatual(Integer.parseInt(rs.getString("epiatual")));
            object.setNota(Integer.parseInt(rs.getString("nota")));
            object.setId(Integer.parseInt(rs.getString("id")));
            object.setDrop(rs.getString("dropado"));
        } catch (SQLException ex) {
            Logger.getLogger(RegistroControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return object;
    }

    public ArrayList<Registro> search(Registro object) {
        ArrayList<Registro> dados;
        dados = new ArrayList<>();

        String wheres = "";
        if (object.getNome() != null) {
            wheres += "nome like '%" + object.getNome() + "%' AND ";
        }
        if (object.getGeneros() != null) {
            wheres += "generos like '%" + object.getGeneros() + "%' AND ";
        }
        if (object.getAno() != 0) {
            wheres += "ano like '%" + object.getAno() + "%' AND ";
        }
        if (object.getTipo() != null) {
            wheres += "tipo like '%" + object.getTipo() + "%' AND ";
        }
        if (object.getEpitotal() != 0) {
            wheres += "epitotal like '%" + object.getEpitotal() + "%' AND ";
        }
        if (object.getNota() != 0) {
            wheres += "nota like '%" + object.getNota() + "%' AND ";
        }
        if (object.getTemporada() != null) {
            wheres += "temporada like '%" + object.getTemporada() + "%' AND ";
        }
        if (object.getDrop() != null) {
            wheres += "dropado like '%" + object.getDrop() + "%' AND ";
        }

        try {
            con = dbConnect();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM registros WHERE " + wheres.substring(0, wheres.length()-5));           
            while (rs.next()) {
                dados.add(preencheClasse(rs));
            }
            stm.execute("SHUTDOWN");
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RegistroControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dados;
    }
}
