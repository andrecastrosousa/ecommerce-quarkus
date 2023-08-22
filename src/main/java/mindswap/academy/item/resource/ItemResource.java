package mindswap.academy.item.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import mindswap.academy.item.dto.ItemCreateDto;
import mindswap.academy.item.dto.ItemUpdateDto;
import mindswap.academy.item.model.Item;
import mindswap.academy.item.service.ItemService;

import java.util.List;

@Path("/item")
public class ItemResource {

    @Inject
    ItemService itemService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getAll(){
        return itemService.getAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public mindswap.academy.item.dto.ItemDto post(ItemCreateDto itemCreateDto){
        return itemService.create(itemCreateDto);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/item/{id}")
    public mindswap.academy.item.dto.ItemDto update(@PathParam("id") Long id, ItemUpdateDto itemUpdateDto){
        return itemService.updateById(id, itemUpdateDto);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/item/{id}")
    public void delete(@PathParam("id") Long id){
        itemService.deleteById(id);
    }

}
