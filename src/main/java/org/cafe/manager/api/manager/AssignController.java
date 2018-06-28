package org.cafe.manager.api.manager;

import org.cafe.manager.entity.Assign;
import org.cafe.manager.entity.Table;
import org.cafe.manager.entity.User;
import org.cafe.manager.repository.AssignRepository;
import org.cafe.manager.repository.TableRepository;
import org.cafe.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assign")
public class AssignController {

    private final AssignRepository assignRepository;
    private final UserRepository userRepository;
    private final TableRepository tableRepository;

    @Autowired
    public AssignController(AssignRepository assignRepository, UserRepository userRepository, TableRepository tableRepository) {
        this.assignRepository = assignRepository;
        this.userRepository = userRepository;
        this.tableRepository = tableRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public void add(long userId, long tableId) {
        User user = userRepository.getOne(userId);
        Table table = tableRepository.getOne(tableId);

        assignRepository.save(new Assign(user, table));
    }
}
