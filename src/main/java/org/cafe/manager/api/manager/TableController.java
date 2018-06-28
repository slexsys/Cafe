package org.cafe.manager.api.manager;

import org.cafe.manager.entity.Table;
import org.cafe.manager.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/table")
public class TableController {

    private final TableRepository repository;

    @Autowired
    public TableController(TableRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public void add(String name) {
        repository.save(new Table(name));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public void edit(long id, String name) {
        Table table  = repository.getOne(id);
        table.setName(name);
        repository.save(table);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(long id) {
        repository.deleteById(id);
    }
}
