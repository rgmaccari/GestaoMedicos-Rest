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
    public Response findById(@PathParam("id") Integer id) throws BusinessException {
        try{
            AutorService autorService = new AutorService();
            //return Response.status(Response.Status.FOUND).entity(autorService.buscarPorId(id)).build();
            return Response.status(Response.Status.OK).entity(autorService.buscarPorId(id)).build();
        }catch (BusinessException e){
            //400 pq é um problema com a validação (deixou campo em branco)
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponseDTO).build();

        } catch(NotFoundException e){
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            //404 pq é um erro de resultado
            return Response.status(Response.Status.NOT_FOUND).entity(exceptionResponseDTO).build();
        } catch(Exception e){
            //500 = erro interno
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO("Ocorreu um erro interno.");
            return Response.serverError().entity(exceptionResponseDTO).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws BusinessException {
        try{
            List<Autor> listaAutores = new ArrayList<Autor>();
            AutorService autorService = new AutorService();
            listaAutores = autorService.findAll();

            return Response.status(Response.Status.OK).entity(listaAutores).build();

        }catch (NotFoundException e){
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(exceptionResponseDTO).build();

        } catch (Exception e) {
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.serverError().entity(exceptionResponseDTO).build();
        }

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") Integer id, CadastroAutorDTO autorInsertRequestDTO) throws BusinessException {
        try {
            AutorService autorService = new AutorService();
            Autor autor = new Autor(id, autorInsertRequestDTO);
            return Response.status(Response.Status.OK).entity(autorService.update(autor)).build();
        }catch (BusinessException e){
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponseDTO).build();
        }catch (NotFoundException e){
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(exceptionResponseDTO).build();
        }catch(Exception e){
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(e.getMessage());
            return Response.serverError().entity(exceptionResponseDTO).build();
        }
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) throws BusinessException {
        AutorService autorService = new AutorService();
        autorService.deletar(id);
    }


}
