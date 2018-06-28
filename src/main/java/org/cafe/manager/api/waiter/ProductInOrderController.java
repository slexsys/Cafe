package org.cafe.manager.api.waiter;

import org.cafe.manager.entity.Order;
import org.cafe.manager.entity.Product;
import org.cafe.manager.entity.ProductInOrder;
import org.cafe.manager.entity.Table;
import org.cafe.manager.entity.comp.ProductInOrderStatus;
import org.cafe.manager.repository.OrderRepository;
import org.cafe.manager.repository.ProductInOrderRepository;
import org.cafe.manager.repository.ProductRepository;
import org.cafe.manager.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productinorder")
public class ProductInOrderController {

    private final ProductInOrderRepository productInOrderRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final TableRepository tableRepository;

    @Autowired
    public ProductInOrderController(ProductInOrderRepository productInOrderRepository, OrderRepository orderRepository, ProductRepository productRepository, TableRepository tableRepository) {
        this.productInOrderRepository = productInOrderRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.tableRepository = tableRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public void add(long tableid, long productid, double qtty) {
        Table table = tableRepository.getOne(tableid);
        Order order = orderRepository.getByTable(table);
        Product product = productRepository.getOne(productid);

        productInOrderRepository.save(new ProductInOrder(
                qtty,
                qtty * product.getCost(),
                order,
                product,
                ProductInOrderStatus.Active
        ));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public void edit(long id, long productid, double qtty, ProductInOrderStatus status) {
        ProductInOrder productInOrder = productInOrderRepository.getOne(id);
        Product product = productRepository.getOne(productid);

        productInOrder.setQtty(qtty);
        productInOrder.setAmount(qtty * product.getCost());
        productInOrder.setProduct(product);
        productInOrder.setStatus(status);

        productInOrderRepository.save(productInOrder);
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    public void cancel(long tableid) {
        Table table = tableRepository.getOne(tableid);
        Order order = orderRepository.getByTable(table);
        orderRepository.cancelOrder(order);
    }

    @RequestMapping(value = "/close", method = RequestMethod.GET)
    public void close(long tableid) {
        Table table = tableRepository.getOne(tableid);
        Order order = orderRepository.getByTable(table);
        orderRepository.closeOrder(order);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(long id) {
        productInOrderRepository.deleteById(id);
    }
}
