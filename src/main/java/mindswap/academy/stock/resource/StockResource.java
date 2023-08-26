package mindswap.academy.stock.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import mindswap.academy.item.dto.ItemDto;
import mindswap.academy.stock.dto.StockDto;
import mindswap.academy.stock.dto.StockItemDto;
import mindswap.academy.stock.dto.StockSupplierDto;
import mindswap.academy.stock.service.StockServiceImp;
import mindswap.academy.supplier.dto.SupplierDto;

@Path("/")
public class StockResource {

    @Inject
    StockServiceImp stockServiceImp;

    @GET
    @Path("items/{itemId}/stock")
    @Produces(MediaType.APPLICATION_JSON)
    public StockItemDto getStockFromItemId(@PathParam("itemId") Long itemId){
        return stockServiceImp.getByItemId(itemId);
    }

    @GET
    @Path("supplier/{supplierId}/stock")
    @Produces(MediaType.APPLICATION_JSON)
    public StockSupplierDto getStockFromSupplierId(@PathParam("supplierId") Long supplierId) {
        return stockServiceImp.getBySupplierId(supplierId);
    }

    @GET
    @Path("supplier/{supplierId}/items/{itemId}/stock")
    @Produces(MediaType.APPLICATION_JSON)
    public StockDto getStockFromSupplier(@PathParam("supplierId") Long supplierId, @PathParam("itemId") Long itemId){
        return stockServiceImp.getItemStockFromSupplier(supplierId, itemId);
    }

}
