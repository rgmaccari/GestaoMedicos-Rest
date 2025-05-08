package org.unipar.unilivrariarest.repository;

import org.unipar.unilivrariarest.domain.Autor;
import org.unipar.unilivrariarest.infrastructure.ConnectionFactory;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorRepository {

    private static final String INSERT =
            "insert into autor (nome) values (?)";

    private static final String UPDATE =
            "update autor set nome = ? where id = ?";

    private static final String FIND_ALL =
            "select id, nome from autor";

    private static final String DELETE_BY_ID =
            "delete from autor where id = ?";

    private static final String FIND_BY_ID = "SELECT * FROM medico WHERE id = ?";

    public Autor inserir(Autor autor) throws SQLException, NamingException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, autor.getNome());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            rs.next();
            autor.setId(rs.getInt(1));

        } finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
            if (conn != null) conn.close();
        }

        return autor;
    }

    public Autor buscarPorId(Integer id) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Autor autor = null;

        try{
            connection = new ConnectionFactory().getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                autor = new Autor();

                autor.setId(resultSet.getInt("id"));
                autor.setNome(resultSet.getString("nome"));
            }


        }finally{
            if(preparedStatement != null)
                preparedStatement.close();

            if(resultSet != null)
                resultSet.close();

            if(connection != null)
                connection.close();
        }
        return autor;
    }

    public void update(Autor autor) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();

            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setString(1, autor.getNome());
            pstmt.setInt(2, autor.getId());
            pstmt.executeUpdate();

        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    public List<Autor> findAll() throws SQLException, NamingException {
        List<Autor> autores = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Autor autor = new Autor();
                autor.setId(rs.getInt("id"));
                autor.setNome(rs.getString("nome"));
                autores.add(autor);
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return autores;
    }

    public void deletar(Integer id) throws SQLException, NamingException {
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
