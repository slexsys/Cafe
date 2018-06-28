package org.cafe.manager.api.manager;

import org.cafe.manager.entity.Product;
import org.cafe.manager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductRepository repository;

    @Autowired
    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public void add(String name, double cost) {
        repository.save(new Product(name, cost));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public void edit(long id, String name, double cost) {
        Product product = repository.getOne(id);
        product.setName(name);
        product.setCost(cost);
        repository.save(product);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(long id) {
        repository.deleteById(id);
    }
}
