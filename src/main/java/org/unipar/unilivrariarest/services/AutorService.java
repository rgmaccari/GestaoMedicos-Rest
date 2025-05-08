package org.unipar.unilivrariarest.services;

import jakarta.ws.rs.NotFoundException;
import org.unipar.unilivrariarest.domain.Autor;
import org.unipar.unilivrariarest.exceptions.BusinessException;
import org.unipar.unilivrariarest.repository.AutorRepository;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorService {

    private AutorRepository autorRepository;

    public AutorService() {
        autorRepository = new AutorRepository();
    }


    public Autor inserir(Autor autor) throws BusinessException, SQLException, NamingException {
        if (autor.getNome() == null || autor.getNome().isEmpty()) {
            throw new BusinessException("Nome do autor é obrigatório");
        }

        if (autor.getNome().length() > 100) {
            throw new BusinessException("Nome do autor deve ter no máximo 100 caracteres");
        }

        autorRepository.inserir(autor);
        return autor;
    }

    public Autor buscarPorId(Integer id) throws BusinessException, SQLException, NamingException {

        if (id == null || id == 0) {
            throw new BusinessException("Nome do autor é obrigatório para busca");
        }

        Autor autor = autorRepository.buscarPorId(id);

        if(autor == null) {
            throw new NotFoundException("Autor não encontrado");
        }
        return autor;
    }

    public List<Autor> findAll() throws NotFoundException, SQLException, NamingException {
        List<Autor> listaAutores = new ArrayList<>();
        AutorRepository autorRepository = new AutorRepository();
        listaAutores = autorRepository.findAll();

        if(listaAutores == null) {
            throw new NotFoundException("Lista vazia. Nenhum autor encontrado");
        }

        return listaAutores;
    }

    public Autor update(Autor autor) throws BusinessException, SQLException, NamingException {
        if (autor.getId() == null) {
            throw new BusinessException("Id do autor é obrigatório para edição");
        }

        if (autor.getNome() == null || autor.getNome().isEmpty()) {
            throw new BusinessException("Nome do autor é obrigatório");
        }

        if (autor.getNome().length() > 100) {
            throw new BusinessException("Nome do autor deve ter no máximo 100 caracteres");
        }

        autorRepository.update(autor);
        return autor;
    }



    public void deletar(Integer id) throws BusinessException {

        if (id == null) {
            throw new BusinessException("Id do autor é obrigatório para exclusão");
        }

        try {
            autorRepository.deletar(id);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            throw new BusinessException("Erro ao deletar autor. Entre o contato com o suporte do WebService");
        }
    }

}