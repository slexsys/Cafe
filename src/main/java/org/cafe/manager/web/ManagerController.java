package org.cafe.manager.web;

import org.cafe.manager.entity.Product;
import org.cafe.manager.entity.Table;
import org.cafe.manager.entity.User;
import org.cafe.manager.repository.AssignRepository;
import org.cafe.manager.repository.ProductRepository;
import org.cafe.manager.repository.TableRepository;
import org.cafe.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final UserRepository userRepository;
    private final TableRepository tableRepository;
    private final ProductRepository productRepository;
    private final AssignRepository assignRepository;

    @Autowired
    public ManagerController(UserRepository userRepository, TableRepository tableRepository, ProductRepository productRepository, AssignRepository assignRepository) {
        this.userRepository = userRepository;
        this.tableRepository = tableRepository;
        this.productRepository = productRepository;
        this.assignRepository = assignRepository;
    }

    @RequestMapping("")
    public String menu() {
        return "manager/menu";
    }

    @RequestMapping("/users")
    public String users(Model model) {
        List<User> list = userRepository.findAll();
        model.addAttribute("list", list);
        return "/manager/users";
    }

    @RequestMapping("/tables")
    public String tables(Model model) {
        List<Table> list = tableRepository.findAll();
        model.addAttribute("list", list);
        return "/manager/tables";
    }

    @RequestMapping("/products")
    public String products(Model model) {
        List<Product> list = productRepository.findAll();
        model.addAttribute("list", list);
        return "/manager/products";
    }

    @RequestMapping("/assign")
    public String assign(Model model) {
        List<User> users = userRepository.findAllWaiters();
        model.addAttribute("users", users);

        List<Table> list = assignRepository.getAllTables();
        List<Table> tables = tableRepository.findAllNotContain(list);
        model.addAttribute("tables", tables);

        return "/manager/assign";
    }
}
