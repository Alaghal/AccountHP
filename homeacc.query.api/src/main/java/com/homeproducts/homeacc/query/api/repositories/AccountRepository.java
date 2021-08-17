package com.homeproducts.homeacc.query.api.repositories;

import com.homeproducts.homeacc.core.models.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<UserAccount,String> {
}
