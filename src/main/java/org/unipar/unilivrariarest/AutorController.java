package org.unipar.unilivrariarest;

import jakarta.ws.rs.core.Response;
import org.unipar.unilivrariarest.domain.Autor;
import org.unipar.unilivrariarest.dto.CadastroAutorDTO;
import org.unipar.unilivrariarest.dto.ExceptionResponseDTO;
import org.unipar.unilivrariarest.exceptions.BusinessException;
import org.unipar.unilivrariarest.services.AutorService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/autores")
public class AutorController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(CadastroAutorDTO autorInsertRequestDTO) {
        try {
            AutorService autorService = new AutorService();
            Autor autor = new Autor(autorInsertRequestDTO);
            autor = autorService.inserir(autor);

            return Response.status(Response.Status.CREATED).entity(autor).build();

        } catch (BusinessException e) {
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponseDTO).build();

        } catch (Exception e) {
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO("Ocorreu um erro interno.");
            return Response.serverError().entity(exceptionResponseDTO).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Autor findById(@PathParam("id") Integer id) throws BusinessException {
        try{
            AutorService autorService = new AutorService();
            return new Autor();//autorService.buscarPorId(id);
        }catch (BusinessException e){
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.status(S)
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Autor> findAll(@QueryParam("nome") String nome) throws BusinessException {

        List<Autor> listaAutores = new ArrayList<Autor>();

        AutorService autorService = new AutorService();
        if (nome != null)
            listaAutores = autorService.buscarPorNome(nome);
        else
            listaAutores = autorService.buscarTodos();

        return listaAutores;

    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) throws BusinessException {
        AutorService autorService = new AutorService();
        autorService.deletar(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Autor update(@PathParam("id") Integer id, CadastroAutorDTO autorInsertRequestDTO) throws BusinessException {
        AutorService autorService = new AutorService();
        Autor autor = new Autor(id, autorInsertRequestDTO);

        return autorService.editar(autor);
    }

}