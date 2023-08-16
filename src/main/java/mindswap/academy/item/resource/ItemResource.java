package mindswap.academy.item.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import mindswap.academy.item.dto.ItemCreateDto;
import mindswap.academy.item.dto.ItemDto;
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
    public ItemDto post(ItemCreateDto itemCreateDto){
        return itemService.create(itemCreateDto);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/item/bulk")
    public List<ItemDto> post(List<ItemCreateDto> itemCreateDto){
        return itemService.createBulk(itemCreateDto);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/item/{id}")
    public Item update(@PathParam("id") Long id, Item item){
        return itemService.updateById(id, item);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/item/{id}")
    public void delete(@PathParam("id") Long id){
        itemService.deleteById(id);
    }

}
