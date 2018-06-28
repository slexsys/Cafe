package org.cafe.manager.api.waiter;

import org.cafe.manager.entity.Order;
import org.cafe.manager.entity.Table;
import org.cafe.manager.entity.comp.OrderStatus;
import org.cafe.manager.repository.OrderRepository;
import org.cafe.manager.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, TableRepository tableRepository) {
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public void add(long tableid) {
        Table table = tableRepository.getOne(tableid);

        Order order = new Order(0, OrderStatus.Open, table);
        /*Order order = orderRepository.getByTable(table);
        if (order != null) {
            order.setStatus(OrderStatus.Open);
        } else {
            order = new Order(0, OrderStatus.Open, table);
        }*/
        orderRepository.save(order);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public void edit(long tableid, OrderStatus status) {
        Table table = tableRepository.getOne(tableid);
        Order order = orderRepository.getByTable(table);

        order.setStatus(status);

        orderRepository.save(order);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(long id) {
        orderRepository.deleteById(id);
    }
}
