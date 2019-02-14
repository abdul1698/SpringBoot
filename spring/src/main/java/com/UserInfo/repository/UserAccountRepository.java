package com.UserInfo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.UserInfo.entity.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

    /*
     * Get user list by user name. Please note the format should be
     * findBy<column_name>.
     */
    List<UserAccount> findByUsername(String username);

    /*
     * Get user list by user name and password. Please note the format should be
     * findBy<column_name_1>And<column_name_2>.
     */
    List<UserAccount> findByUsernameAndPassword(String username, String password);

    @Transactional
    void deleteByUsernameAndPassword(String username, String password);

    @Transactional
    void deleteByUsername(String username);

}
