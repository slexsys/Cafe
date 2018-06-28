package org.cafe.manager.web;

import org.cafe.manager.entity.*;
import org.cafe.manager.entity.comp.OrderStatus;
import org.cafe.manager.entity.comp.ProductInOrderStatus;
import org.cafe.manager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/waiter")
public class WaiterController {
    private final AssignRepository assignRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;
    private final ProductInOrderRepository productInOrderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public WaiterController(AssignRepository assignRepository, UserRepository userRepository, OrderRepository orderRepository, TableRepository tableRepository, ProductInOrderRepository productInOrderRepository, ProductRepository productRepository) {
        this.assignRepository = assignRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
        this.productInOrderRepository = productInOrderRepository;
        this.productRepository = productRepository;
    }

    @RequestMapping("")
    public String menu() {
        return "waiter/menu";
    }

    @RequestMapping("/tables")
    public String tables(Model model, Principal principal) {
        User user = getUser(principal);
        List<Table> list = assignRepository.findAllTablesByUser(user);
        model.addAttribute("list", list);
        return "waiter/tables";
    }

    @RequestMapping("/create/orders")
    public String createOrder(Model model, Principal principal) {
        User user = getUser(principal);
        List<Table> list;
        List<Table> tables = orderRepository.findAllOpenedTables();
        if (tables.isEmpty()) {
            list = assignRepository.findNotActiveTablesByUser(user);
        } else {
            list = assignRepository.findNotActiveTablesByUser(user, tables);
        }

        model.addAttribute("list", list);
        return "waiter/create/orders";
    }

    @RequestMapping("/productInOrder")
    public String productInOrder(Model model, Principal principal) {
        User user = getUser(principal);
        List<Table> list = assignRepository.findActiveTablesByUser(user);
        model.addAttribute("list", list);
        return "waiter/tables4ProductInOrder";
    }

    @RequestMapping("/create/productInOrder")
    public String createProductInOrder(long tableid, Model model) {
        Table table = tableRepository.getOne(tableid);
        Order order = orderRepository.getByTable(table);

        double total = productInOrderRepository.getTotal(order);
        model.addAttribute("total", total);
        model.addAttribute("tableid", tableid);

        List<ProductInOrder> list = productInOrderRepository.findProductInOrderByOrder(order);
        model.addAttribute("list", list);

        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);

        model.addAttribute("statuses", ProductInOrderStatus.values());

        return "waiter/create/productInOrder";
    }

    @RequestMapping("/edit/orders")
    public String editOrders(Model model, Principal principal) {
        User user = getUser(principal);
        List<Table> tables = assignRepository.findAllTablesByUser(user);
        model.addAttribute("tables", tables);
        model.addAttribute("statuses", OrderStatus.values());
        return "waiter/edit/orders";
    }

    private User getUser(Principal principal) {
        String name = principal.getName();
        return userRepository.findByName(name);
    }
}
