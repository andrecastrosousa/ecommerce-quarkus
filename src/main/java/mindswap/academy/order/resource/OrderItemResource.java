package mindswap.academy.order.resource;


import com.oracle.svm.core.annotate.Delete;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import mindswap.academy.order.dto.OrderItemCreateDto;
import mindswap.academy.order.dto.OrderItemDto;
import mindswap.academy.order.dto.OrderItemUpdatedDto;
import mindswap.academy.order.service.OrderItemService;
import mindswap.academy.order.service.OrderItemServiceImp;

import java.util.List;

@Path("/orders")
public class OrderItemResource {

    @Inject
    OrderItemServiceImp orderItemServiceImp;

    @GET
    @Path("/orderItem")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderItemDto> getAll(){
        return orderItemServiceImp.getAll();
    }

    @GET
    @Path("/orderItem/{orderItemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderItemDto getById(@PathParam("orderItemId") Long orderItemId){
        return orderItemServiceImp.getById(orderItemId);
    }

    @POST
    @Path("/orderItem")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public OrderItemDto create(@Valid OrderItemCreateDto orderItemCreateDto){
        return orderItemServiceImp.create(orderItemCreateDto);
    }

    @Delete
    @Path("/orderItem/{orderItemId}")
    @Transactional
    public void delete(@PathParam("orderItemId") Long orderItemId){
        orderItemServiceImp.delete(orderItemId);
    }

    @PUT
    @Path("/orderItem/{orderItemId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public OrderItemDto update(@PathParam("orderItemId") Long orderItemId, OrderItemUpdatedDto orderItemUpdatedDto){
        return orderItemServiceImp.update(orderItemId, orderItemUpdatedDto);
    }

}
