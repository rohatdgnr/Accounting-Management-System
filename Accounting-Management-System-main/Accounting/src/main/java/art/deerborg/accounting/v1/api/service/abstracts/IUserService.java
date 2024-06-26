package art.deerborg.accounting.v1.api.service.abstracts;

import art.deerborg.accounting.v1.api.model.User;

public interface IUserService {
    User getByUsername(String username);
    User createUser(User user);
    User getByUserId(Long id);
}
