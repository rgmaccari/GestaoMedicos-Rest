package org.unipar.unilivrariarest.repository;

import org.unipar.unilivrariarest.domain.Medico;
import org.unipar.unilivrariarest.infrastructure.ConnectionFactory;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoRepository {

    private static final String INSERT =
            "insert into medico (nome) values (?)";

    private static final String UPDATE =
            "update medico set nome = ? where id = ?";

    private static final String FIND_ALL =
            "select id, nome from medico";

    private static final String DELETE_BY_ID =
            "delete from medico where id = ?";

    private static final String FIND_BY_ID = "SELECT * FROM medico WHERE id = ?";

    public Medico insert(Medico medico) throws SQLException, NamingException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, medico.getNome());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            rs.next();
            medico.setId(rs.getInt(1));

        } finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
            if (conn != null) conn.close();
        }

        return medico;
    }

    public void update(Medico medico) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();

            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setInt(1, medico.getId());
            pstmt.setString(2, medico.getNome());
            pstmt.executeUpdate();

        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    public Medico findById(Integer id) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Medico medico = null;

        try{
            connection = new ConnectionFactory().getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                medico = new Medico();

                medico.setId(resultSet.getInt("id"));
                medico.setNome(resultSet.getString("nome"));
            }


        }finally{
            if(preparedStatement != null)
                preparedStatement.close();

            if(resultSet != null)
                resultSet.close();

            if(connection != null)
                connection.close();
        }
        return medico;
    }

    public List<Medico> findAll() throws SQLException, NamingException {
        List<Medico> medicos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medicos.add(medico);
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return medicos;
    }

    public void delete(Integer id) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {

            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(DELETE_BY_ID);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

    }



}
