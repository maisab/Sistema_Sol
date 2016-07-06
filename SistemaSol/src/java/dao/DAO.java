/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

/**
 *
 * @author maa
 */
public class DAO {

    /*------------remoto-----------------*/
//        private static final String url = "jdbc:mysql://mysql.sol.pro.br/";
//        private static final String dbName = "sol04";
//        private static final String driver = "com.mysql.jdbc.Driver";
//        private static final String userName = "sol04";
//        private static final String password = "546987";
    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String dbName = "loginTeste";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String userName = "root";
    private static final String password = "546987";

    public static boolean validate(String name, String pass) throws SQLException, ClassNotFoundException {
        boolean status = false;
        Connection conexao = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName(driver).newInstance();
            conexao = DriverManager.getConnection(url + dbName, userName, password);

//            pst = conexao.prepareStatement("select * from  login where nome=? and senha=?"); /*remoto*/
//            pst = conexao.prepareStatement("select * from  login where usuario=? and senha=?");
            pst = conexao.prepareStatement("select email_usuario, senha_usuario from  usuario where email_usuario=? and senha_usuario=?");
            pst.setString(1, name);
            pst.setString(2, pass);
            System.out.println(" usuario " + name);
            System.out.println(" senha " + pass);

            rs = pst.executeQuery();
            status = rs.next();

        } catch (Exception e) {
            System.out.println(e);

        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pst == null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return status;
    }

    public static boolean insert(Usuario u) throws ClassNotFoundException, InstantiationException {
        Connection conexao = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Usuario> listaUsuarios = listAll();

        try {
            Class.forName(driver).newInstance();
            conexao = DriverManager.getConnection(url + dbName, userName, password);

            if (listaUsuarios.contains(u.getEmail())) {
                System.out.println("Usuario já cadastrado");

            } else {
                Statement statement = conexao.createStatement();
                String sql = "INSERT INTO usuario (nome_usuario, email_usuario, senha_usuario) VALUES ('" + u.getNome() + "','" + u.getEmail() + "','" + u.getSenha() + "')";
                System.out.println("Executando consulta: " + sql);

                statement.execute(sql);

                System.out.println("Inserido com sucesso");

                statement.close();
            }
            return true;
        } catch (Exception ex) {

            System.out.println("Erro : " + ex.getMessage());
            return false;
        }

    }

    public static List<String> buscaSensor(String dataInicio, String horaInicio, String dataFim, String horaFim, int sensor) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection conexao = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<String> listaResultado = new ArrayList<>();
//
//        System.out.println("--------SQL ---------");
//
//        System.out.println(dataInicio);
//        System.out.println(horaInicio);
//        System.out.println(dataFim);
//        System.out.println(horaFim);
//        System.out.println(sensor);

        Class.forName(driver).newInstance();
        conexao = DriverManager.getConnection(url + dbName, userName, password);

        pst = conexao.prepareStatement("select * from coleta_dados where "
                + "                                             datahora_coleta_dados >= " + "'" + dataInicio + "'" + " " + "'" + horaInicio + "' "
                + "                                             AND datahora_coleta_dados <= " + "'" + dataFim + "'" + " " + "'" + horaFim + "' "
                + "                                             AND sensor_id_sensor = " + "'" + sensor + "'");

        rs = pst.executeQuery();
        
        String aux = "";
        
        while (rs.next()) {
//            System.out.println("--------- entrou aquiiiiiiiiiiiiiii -----------");  
//            
//            System.out.println(" SENSOR " +  rs.getInt("sensor_id_sensor"));
//            
            aux += rs.getTimestamp("datahora_coleta_dados");
            aux += ";" + rs.getInt("sensor_id_sensor");
            aux += ";" + rs.getString("dados_coleta_dados");

            listaResultado.add(aux);
            aux = "";
        }
//            System.out.println("----------- saiu aquiiiiiiiiiiiiiii ----------------");
        
        return listaResultado;
    }

    public static ArrayList<Usuario> listAll() {

        Connection conexao = null;
        PreparedStatement pst = null;

        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {

            Class.forName(driver).newInstance();
            conexao = DriverManager.getConnection(url + dbName, userName, password);

            PreparedStatement stmt = conexao.prepareStatement("select * from usuario");

// executa um select
            ResultSet rs = stmt.executeQuery();

// itera no ResultSet
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setNome(rs.getString("usuario"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));

                System.out.println(u.toString());
                usuarios.add(u);

            }

            stmt.close();
            conexao.close();

        } catch (Exception ex) {

            System.out.println("Erro : " + ex.getMessage());

        }
        return usuarios;
    }
}
