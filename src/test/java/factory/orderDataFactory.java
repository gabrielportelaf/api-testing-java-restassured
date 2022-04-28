package factory;

import pojo.Order;

public class orderDataFactory {
    public static Order criaOrder(){
        Order orderCompra = new Order();

        orderCompra.setId(1);
        orderCompra.setPetId(2);
        orderCompra.setQuantity(3);
        orderCompra.setShipDate("2022-03-22T11:32:29.501Z");
        orderCompra.setStatus("placed");
        orderCompra.setComplete(true);

        return orderCompra;
    }
}