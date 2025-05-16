package org.unipar.gestaomedicosrest.repository;

import org.unipar.gestaomedicosrest.domain.Paciente;
import org.unipar.gestaomedicosrest.dto.CadastroPacienteDTO;
import org.unipar.gestaomedicosrest.dto.ListagemPacienteDTO;
import org.unipar.gestaomedicosrest.infrastructure.ConnectionFactory;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepository {

    private static final String INSERT =
            "INSERT INTO paciente (nome, email, telefone, cpf, logradouro, numero, complemento, bairro, cidade, uf, cep, ativo) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,true)";

    private static final String UPDATE =
            "UPDATE paciente SET nome = ?, telefone = ?, logradouro = ? WHERE id = ?";

    private static final String FIND_ALL =
            "SELECT id, nome, email, cpf FROM paciente WHERE ativo = true ORDER BY nome asc";

    private static final String DELETE_BY_ID =
            "UPDATE paciente SET ativo = false where id = ?";

    private static final String FIND_BY_ID = "SELECT * FROM paciente WHERE id = ?";


    public Paciente insert(CadastroPacienteDTO cadastroDTO) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Paciente paciente = new Paciente(cadastroDTO);

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, cadastroDTO.getNome());
            pstmt.setString(2, cadastroDTO.getEmail());
            pstmt.setLong(3, cadastroDTO.getTelefone());
            pstmt.setString(4, cadastroDTO.getCpf());
            pstmt.setString(5, cadastroDTO.getLogradouro());
            pstmt.setObject(6, cadastroDTO.getNumero());
            pstmt.setObject(7, cadastroDTO.getComplemento());
            pstmt.setString(8, cadastroDTO.getBairro());
            pstmt.setString(9, cadastroDTO.getCidade());
            pstmt.setString(10, cadastroDTO.getUf());
            pstmt.setLong(11, cadastroDTO.getCep());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                paciente.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            throw e;
        } catch (NamingException e) {
            System.err.println("NamingException: " + e.getMessage());
            throw e;
        } finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
            if (conn != null) conn.close();
        }
        return paciente;
    }


    public void update(Paciente paciente) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();

            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setInt(4, paciente.getId());
            pstmt.setString(1, paciente.getNome());
            pstmt.setLong(2, paciente.getTelefone());
            pstmt.setString(3, paciente.getLogradouro());
            pstmt.executeUpdate();

        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }


    public Paciente findById(Integer id) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Paciente paciente = null;

        try{
            connection = new ConnectionFactory().getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                paciente = new Paciente();

                paciente.setId(resultSet.getInt("id"));
                paciente.setNome(resultSet.getString("nome"));
                paciente.setEmail(resultSet.getString("email"));
                paciente.setCpf(resultSet.getString("cpf"));
            }


        }finally{
            if(preparedStatement != null)
                preparedStatement.close();

            if(resultSet != null)
                resultSet.close();

            if(connection != null)
                connection.close();
        }
        return paciente;
    }


    public List<ListagemPacienteDTO> findAll() throws SQLException, NamingException {
        List<ListagemPacienteDTO> pacientes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ListagemPacienteDTO paciente = new ListagemPacienteDTO();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setEmail(rs.getString("email"));
                paciente.setCpf(rs.getString("cpf"));
                pacientes.add(paciente);
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return pacientes;
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
