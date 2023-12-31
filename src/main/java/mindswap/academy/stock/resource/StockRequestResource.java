package mindswap.academy.stock.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import mindswap.academy.stock.dto.StockRequestCreateDto;
import mindswap.academy.stock.dto.StockRequestDto;
import mindswap.academy.stock.dto.StockRequestUpdateDto;
import mindswap.academy.stock.service.StockRequestServiceImpl;

import java.util.List;

@Path("/stockRequest")
public class StockRequestResource {
    @Inject
    StockRequestServiceImpl stockRequestServiceImpl;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<StockRequestDto> get(){
        return stockRequestServiceImpl.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StockRequestDto getId(@PathParam("id") Long id){
        return stockRequestServiceImpl.getById(id);
    }

    @PUT
    @Path("/{stockSupplierId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public StockRequestDto put(@PathParam("stockSupplierId") Long stockSupplierId, StockRequestUpdateDto stockRequestUpdateDto){
        return stockRequestServiceImpl.update(stockSupplierId, stockRequestUpdateDto);
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public StockRequestDto post(StockRequestCreateDto stockRequestDto){
        return stockRequestServiceImpl.create(stockRequestDto);
    }

    @DELETE
    @Path("{stockRequestId}")
    @Transactional
    public void delete(@PathParam("stockRequestId") Long stockRequestId){
        stockRequestServiceImpl.delete(stockRequestId);
    }
    }



