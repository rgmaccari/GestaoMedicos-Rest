package org.unipar.gestaomedicosrest.repository;

import org.unipar.gestaomedicosrest.domain.Consulta;
import org.unipar.gestaomedicosrest.dto.CadastroConsultaDTO;
import org.unipar.gestaomedicosrest.infrastructure.ConnectionFactory;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConsultaRepository {
    private static final String INSERT =
            "INSERT INTO consulta (medico_id, paciente_id, data_hora, ativo) VALUES (?, ?, ?, true)";

    private static final String DELETE_BY_ID = "UPDATE consulta SET ativo = false where id = ?";

    private static final String CONSULTA_NO_DIA_PACIENTE = "SELECT EXISTS (SELECT 1 FROM consulta " +
                    "WHERE paciente_id = ? AND DATE(data_hora) = ? AND ativo = true) AS exists_result";

    private static final String MEDICO_DISPONIVEL = "SELECT EXISTS (SELECT 1 FROM consulta " +
                    "WHERE medico_id = ? AND data_hora = ? AND ativo = true) AS exists_result";

    public Consulta insert(CadastroConsultaDTO cadastroDTO) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Consulta consulta = new Consulta(cadastroDTO);

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, cadastroDTO.getMedico_id());
            pstmt.setInt(2, cadastroDTO.getPaciente_id());
            pstmt.setObject(3, cadastroDTO.getData_hora());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                consulta.setId(rs.getInt(1));
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
        return consulta;
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

    //Validar se o paciente já possui uma consulta no dia
    public boolean consultaNoDiaPorPaciente(Integer pacienteId, LocalDate date) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(CONSULTA_NO_DIA_PACIENTE);
            pstmt.setInt(1, pacienteId);
            pstmt.setObject(2, date);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                exists = rs.getBoolean("exists_result");
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

        return exists;
    }

    public boolean medicoDisponivel(Integer medicoId, LocalDateTime dataHora) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(MEDICO_DISPONIVEL);
            pstmt.setInt(1, medicoId);
            pstmt.setObject(2, dataHora);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                exists = rs.getBoolean("exists_result");
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

        return exists;
    }
}
