package org.cafe.manager;

import org.cafe.manager.entity.*;
import org.cafe.manager.entity.comp.OrderStatus;
import org.cafe.manager.entity.comp.ProductInOrderStatus;
import org.cafe.manager.entity.comp.UserType;
import org.cafe.manager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

//default username/password -> admin/admin
@SpringBootApplication
public class CafeManagerApplication {
    //region finals
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductInOrderRepository productInOrderRepository;
    private final TableRepository tableRepository;
    private final UserRepository userRepository;
    private final AssignRepository assignRepository;
    //endregion

    //region constructors
    @Autowired
    public CafeManagerApplication(OrderRepository orderRepository, ProductRepository productRepository, ProductInOrderRepository productInOrderRepository, TableRepository tableRepository, UserRepository userRepository, AssignRepository assignRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.productInOrderRepository = productInOrderRepository;
        this.tableRepository = tableRepository;
        this.userRepository = userRepository;
        this.assignRepository = assignRepository;
    }
    //endregion

    public static void main(String[] args) {
        SpringApplication.run(CafeManagerApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> {
            addAdmin();
            test();
        };
    }

    private void addAdmin() {
        userRepository.save(new User("admin", "admin", UserType.Manager));
    }

    //region test entity
    private void test() {
        addUsers();
        addTables();
        addProducts();
        //addOrders();
        //addProductInOrders();
        addAssign();
    }

    private void addUsers() {
        userRepository.save(new User("m1", "1", UserType.Manager));
        userRepository.save(new User("m2", "2", UserType.Manager));
        userRepository.save(new User("w1", "3", UserType.Waiter));
        userRepository.save(new User("w2", "4", UserType.Waiter));
        userRepository.save(new User("w3", "5", UserType.Waiter));
    }

    private void addTables() {
        tableRepository.save(new Table("Table 1"));
        tableRepository.save(new Table("Table 2"));
        tableRepository.save(new Table("Table 3"));
        tableRepository.save(new Table("Table 4"));
        tableRepository.save(new Table("Table 5"));
    }

    private void addProducts() {
        productRepository.save(new Product("Coca Cola 250 ml", 150.0));
        productRepository.save(new Product("Coca Cola 500 ml", 250.0));
        productRepository.save(new Product("Coca Cola 1.0 l", 450.0));
        productRepository.save(new Product("Coca Cola 1.5 l", 500.0));
        productRepository.save(new Product("Coca Cola 2.0 l", 550.0));

        productRepository.save(new Product("Free", 300.0));
        productRepository.save(new Product("Hamburger", 1100.0));
        productRepository.save(new Product("Xburger", 5000.0));
    }

    private void addOrders() {
        Table table1 = tableRepository.getOne(1L);
        Table table2 = tableRepository.getOne(2L);
        Table table3 = tableRepository.getOne(3L);

        orderRepository.save(new Order(0, OrderStatus.Open, table1));
        orderRepository.save(new Order(0, OrderStatus.Cancelled, table2));
        orderRepository.save(new Order(0, OrderStatus.Closed, table3));
    }

    private void addProductInOrders() {
        Product product1 = productRepository.getOne(1L);
        Product product2 = productRepository.getOne(2L);
        Product product3 = productRepository.getOne(3L);
        Product product4 = productRepository.getOne(4L);
        Product product5 = productRepository.getOne(5L);
        Product product6 = productRepository.getOne(6L);

        Order order1 = orderRepository.getOne(1L);
        Order order2 = orderRepository.getOne(2L);
        Order order3 = orderRepository.getOne(3L);

        // order 1
        double qtty = 5;
        double amount = qtty * 10;
        productInOrderRepository.save(new ProductInOrder(qtty, amount, order1, product1, ProductInOrderStatus.Active));

        qtty = 3;
        productInOrderRepository.save(new ProductInOrder(qtty, amount, order1, product2, ProductInOrderStatus.Active));

        qtty = 9;
        productInOrderRepository.save(new ProductInOrder(qtty, amount, order1, product6, ProductInOrderStatus.Active));

        //order 2
        qtty = 2;
        productInOrderRepository.save(new ProductInOrder(qtty, amount, order2, product3, ProductInOrderStatus.Active));

        //order 3
        qtty = 7;
        productInOrderRepository.save(new ProductInOrder(qtty, amount, order3, product4, ProductInOrderStatus.Active));

        qtty = 3;
        productInOrderRepository.save(new ProductInOrder(qtty, amount, order3, product5, ProductInOrderStatus.Active));
    }

    private void addAssign() {
        User waiter1 = userRepository.getOne(4L);
        User waiter2 = userRepository.getOne(5L);
        User waiter3 = userRepository.getOne(6L);

        Table table1 = tableRepository.getOne(1L);
        Table table2 = tableRepository.getOne(2L);
        Table table3 = tableRepository.getOne(3L);

        assignRepository.save(new Assign(waiter1, table3));
        assignRepository.save(new Assign(waiter2, table2));
        assignRepository.save(new Assign(waiter3, table1));
    }
    //endregion
}
