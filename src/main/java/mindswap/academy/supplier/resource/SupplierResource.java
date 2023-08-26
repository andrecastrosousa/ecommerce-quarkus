package mindswap.academy.supplier.resource;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import mindswap.academy.supplier.dto.SupplierCreateDto;
import mindswap.academy.supplier.dto.SupplierDto;
import mindswap.academy.supplier.dto.SupplierUpdateDto;
import mindswap.academy.supplier.model.Supplier;
import mindswap.academy.supplier.service.SupplierService;

import java.util.List;

@Path("/suppliers")
public class SupplierResource {

    @Inject
    SupplierService supplierService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SupplierDto> get(){
        return supplierService.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SupplierDto getId(@PathParam("id") Long id){
        return supplierService.getById(id);
    }

    @PUT
    @Path("/{supplierId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public SupplierDto put(@PathParam("supplierId") Long supplierId, SupplierUpdateDto supplierUpdateDto){
        return supplierService.update(supplierId, supplierUpdateDto);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public  SupplierDto post(SupplierCreateDto supplierCreateDto){
        return supplierService.create(supplierCreateDto);
    }

    @DELETE
    @Path("/{supplierId}")
    @Transactional
    public void delete(@PathParam("supplierId") Long supplierId){
        supplierService.delete(supplierId);
    }
}
