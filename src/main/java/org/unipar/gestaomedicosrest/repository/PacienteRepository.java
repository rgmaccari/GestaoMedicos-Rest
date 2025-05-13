package org.unipar.gestaomedicosrest.repository;

import org.unipar.gestaomedicosrest.domain.Paciente;
import org.unipar.gestaomedicosrest.infrastructure.ConnectionFactory;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepository {

    private static final String INSERT = "insert into paciente (nome) values (?)";

    private static final String UPDATE = "update paciente set nome = ? where id = ?";

    private static final String FIND_ALL = "select id, nome from paciente";

    private static final String DELETE_BY_ID = "delete from paciente where id = ?";

    private static final String FIND_BY_ID = "SELECT * FROM paciente WHERE id = ?";

    public Paciente insert(Paciente paciente) throws SQLException, NamingException{
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try{
            conn = new ConnectionFactory().getConnection();

            pstm = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1, paciente.getNome());
            pstm.executeUpdate();

            rs = pstm.getGeneratedKeys();

            rs.next();
            paciente.setId(rs.getInt(1));
        }finally {
            {
                if(pstm != null){
                    pstm.close();
                }

                if(rs != null){
                    rs.close();
                }

                if(conn != null){
                    conn.close();
                }
            }
        }
        return paciente;
    }

    public void update(Paciente paciente) throws SQLException, NamingException{
        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = new ConnectionFactory().getConnection();
            pstm = conn.prepareStatement(UPDATE);

            pstm.setInt(1, paciente.getId());
            pstm.setString(2, paciente.getNome());
            pstm.executeUpdate();

        }finally {
            if(pstm != null){
                pstm.close();
            }

            if(conn != null){
                conn.close();
            }
        }
    }

    public Paciente findById(Integer id) throws SQLException, NamingException{
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Paciente paciente = null;

        try{
            conn = new ConnectionFactory().getConnection();
            pstm = conn.prepareStatement(FIND_BY_ID);
            rs = pstm.executeQuery();

            if(rs.next()){
                paciente = new Paciente();

                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
            }
        }finally {
            if(conn != null){
                conn.close();
            }

            if(pstm != null){
                pstm.close();
            }

            if(rs != null){
                rs.close();
            }
        }

        return paciente;
    }

    public List<Paciente> findAll() throws SQLException, NamingException{
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<Paciente> listaPacientes = new ArrayList<>();

        try{
            conn = new ConnectionFactory().getConnection();
            pstm = conn.prepareStatement(FIND_ALL);
            rs = pstm.executeQuery();

            while(rs.next()){
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                listaPacientes.add(paciente);
            }

        }finally {
            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        }

        return listaPacientes;
    }


    public void delete(Integer id) throws SQLException, NamingException{
        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = new ConnectionFactory().getConnection();
            pstm = conn.prepareStatement(DELETE_BY_ID);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        }finally {
            if(conn != null){
                conn.close();
            }

            if(pstm != null){
                pstm.close();
            }
        }
    }

}
