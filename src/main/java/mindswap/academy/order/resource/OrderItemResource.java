package mindswap.academy.order.resource;


import com.oracle.svm.core.annotate.Delete;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import mindswap.academy.order.dto.OrderItemCreateDto;
import mindswap.academy.order.dto.OrderItemDto;
import mindswap.academy.order.dto.OrderItemUpdatedDto;
import mindswap.academy.order.service.OrderItemService;

import java.util.List;

@Path("/orders")
public class OrderItemResource {

    @Inject
    OrderItemService orderItemService;

    @GET
    @Path("/orderItem")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderItemDto> getAll(){
        return orderItemService.getAll();
    }

    @GET
    @Path("/orderItem/{orderItemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderItemDto getById(@PathParam("orderItemId") Long orderItemId){
        return orderItemService.getById(orderItemId);
    }

    @POST
    @Path("/orderItem")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderItemDto create(@Valid OrderItemCreateDto orderItemCreateDto){
        return orderItemService.create(orderItemCreateDto);
    }

    @Delete
    @Path("/orderItem/{orderItemId}")
    public void delete(@PathParam("orderItemId") Long orderItemId){
        orderItemService.delete(orderItemId);
    }

    @PUT
    @Path("/orderItem/{orderItemId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderItemDto update(@PathParam("orderItemId") Long orderItemId, OrderItemUpdatedDto orderItemUpdatedDto){
        return orderItemService.update(orderItemId, orderItemUpdatedDto);
    }

}
