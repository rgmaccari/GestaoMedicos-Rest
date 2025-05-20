package org.unipar.gestaomedicosrest.repository;

import org.unipar.gestaomedicosrest.domain.Medico;
import org.unipar.gestaomedicosrest.dto.CadastroMedicoDTO;
import org.unipar.gestaomedicosrest.dto.ListagemMedicoDTO;
import org.unipar.gestaomedicosrest.infrastructure.ConnectionFactory;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MedicoRepository {

    private static final String INSERT =
            "INSERT INTO medico (nome, email, telefone, crm, especialidade, logradouro, numero, bairro, complemento, cidade, ativo) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, true)";

    private static final String UPDATE =
            "UPDATE medico SET nome = ?, telefone = ?, logradouro = ? WHERE id = ?";

    private static final String FIND_ALL =
            "SELECT id, nome, email, crm, especialidade FROM medico WHERE ativo = true ORDER BY nome asc";

    private static final String DELETE_BY_ID =
            "UPDATE medico SET ativo = false where id = ?";

    private static final String FIND_BY_ID = "SELECT * FROM medico WHERE id = ?";

    private static final String FIND_DISPONIBILIDADE = "SELECT m.id, m.nome, m.ativo FROM medico m " +
                    "WHERE m.ativo = true AND m.id NOT IN (SELECT c.medico_id FROM consulta c WHERE c.data_hora = ? AND c.ativo = true)";


    public Medico insert(CadastroMedicoDTO cadastroDTO) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Medico medico = new Medico(cadastroDTO);

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, cadastroDTO.getNome());
            pstmt.setString(2, cadastroDTO.getEmail());
            pstmt.setObject(3, cadastroDTO.getTelefone());
            pstmt.setString(4, cadastroDTO.getCrm());
            pstmt.setString(5, cadastroDTO.getEspecialidade());
            pstmt.setString(6, cadastroDTO.getLogradouro());
            pstmt.setObject(7, cadastroDTO.getNumero());
            pstmt.setString(8, cadastroDTO.getBairro());
            pstmt.setObject(9, cadastroDTO.getComplemento());
            pstmt.setString(10, cadastroDTO.getCidade());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                medico.setId(rs.getInt(1));
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
        return medico;
    }


    public void update(Medico medico) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();

            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setInt(4, medico.getId());
            pstmt.setString(1, medico.getNome());
            pstmt.setLong(2, medico.getTelefone());
            pstmt.setString(3, medico.getLogradouro());
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
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                medico = new Medico();

                medico.setId(resultSet.getInt("id"));
                medico.setNome(resultSet.getString("nome"));
                medico.setEmail(resultSet.getString("email"));
                medico.setCrm(resultSet.getString("crm"));
                medico.setEspecialidade(resultSet.getString("especialidade"));
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


    public List<ListagemMedicoDTO> findAll() throws SQLException, NamingException {
        List<ListagemMedicoDTO> medicos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ListagemMedicoDTO medico = new ListagemMedicoDTO();
                medico.setId(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medico.setEmail(rs.getString("email"));
                medico.setCrm(rs.getString("crm"));
                medico.setEspecialidade(rs.getString("especialidade"));
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

    public List<Medico> findDisponibilidadeMedico(LocalDateTime dataHora) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Medico> medicos = new ArrayList<>();

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_DISPONIBILIDADE);
            pstmt.setObject(1, dataHora);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medico.setAtivo(rs.getBoolean("ativo"));
                medicos.add(medico);
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            throw e;
        } catch (NamingException e) {
            System.err.println("NamingException: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return medicos;
    }



}
