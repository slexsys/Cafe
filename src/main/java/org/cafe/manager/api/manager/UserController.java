package org.cafe.manager.api.manager;

import org.cafe.manager.entity.User;
import org.cafe.manager.entity.comp.UserType;
import org.cafe.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public void add(String name, String password, UserType type) {
        repository.save(new User(name, password, type));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public void edit(long id, String name, String password, UserType type) {
        User user = repository.getOne(id);
        user.setName(name);
        user.setPassword(password);
        user.setType(type);
        repository.save(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(long id) {
        repository.deleteById(id);
    }
}
