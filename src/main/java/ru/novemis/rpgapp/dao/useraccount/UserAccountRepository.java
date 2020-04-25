package ru.novemis.rpgapp.dao.useraccount;

import org.springframework.data.repository.CrudRepository;
import ru.novemis.rpgapp.model.useraccount.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, String> {
}
