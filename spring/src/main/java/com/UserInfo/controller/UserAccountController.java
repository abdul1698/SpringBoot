package com.UserInfo.controller;

import com.UserInfo.entity.UserAccount;
import com.UserInfo.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/userAccount")

public class UserAccountController {
    @Autowired
    UserAccountRepository userAccountRepository;

    /*
     * Mapping url exmaple:
     * http://localhost:8080/userAccount/add?userName=Jerry&password=888888&email=
     * jerry@dev2qa.com
     * http://localhost:8080/userAccount/add?userName=Richard&password=888888&email=
     * richard@google.com
     */
    @GetMapping(path = "/add")
    @ResponseBody
    public String addUser(@RequestParam String userName, @RequestParam String password, @RequestParam String email) {

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(userName);
        userAccount.setPassword(password);
        userAccount.setEmail(email);

        userAccountRepository.save(userAccount);

        String ret = "User account has been added, user name = " + userName + ", password = " + password + ", email = "
                + email;

        return ret;

    }

    /*
     * Mapping url exmaple: http://localhost:8080/userAccount/findAll
     */
    @GetMapping(path = "/findAll")
    @ResponseBody
    public String findAllUser() {

        StringBuffer retBuf = new StringBuffer();

        List<UserAccount> userAccountList = (List<UserAccount>) userAccountRepository.findAll();

        if (userAccountList != null) {
            for (UserAccount userAccount : userAccountList) {
                retBuf.append("user name = ");
                retBuf.append(userAccount.getUsername());
                retBuf.append(", password = ");
                retBuf.append(userAccount.getPassword());
                retBuf.append(", email = ");
                retBuf.append(userAccount.getEmail());
                retBuf.append("\r\n");
            }
        }

        if (retBuf.length() == 0) {
            retBuf.append("No record find.");
        } else {
            retBuf.insert(0, "<pre>");
            retBuf.append("</pre>");
        }

        return retBuf.toString();
    }

    /*
     * Mapping url exmaple:
     * http://localhost:8080/userAccount/findByName?userName=Jerry
     */
    @GetMapping(path = "/findByName")
    @ResponseBody
    public String findByName(@RequestParam String userName) {

        StringBuffer retBuf = new StringBuffer();

        List<UserAccount> userAccountList = (List<UserAccount>) userAccountRepository.findByUsername(userName);

        if (userAccountList != null) {
            for (UserAccount userAccount : userAccountList) {
                retBuf.append("user name = ");
                retBuf.append(userAccount.getUsername());
                retBuf.append(", password = ");
                retBuf.append(userAccount.getPassword());
                retBuf.append(", email = ");
                retBuf.append(userAccount.getEmail());
                retBuf.append("\r\n");
            }
        }

        if (retBuf.length() == 0) {
            retBuf.append("No record find.");
        }

        return retBuf.toString();
    }

    /*
     * Mapping url exmaple:
     * http://localhost:8080/userAccount/findByNameAndPassword?userName=Jerry&
     * password=888888
     */
    @GetMapping(path = "/findByNameAndPassword")
    @ResponseBody
    public String findByNameAndPassword(@RequestParam String userName, @RequestParam String password) {

        StringBuffer retBuf = new StringBuffer();

        List<UserAccount> userAccountList = (List<UserAccount>) userAccountRepository
                .findByUsernameAndPassword(userName, password);

        if (userAccountList != null) {
            for (UserAccount userAccount : userAccountList) {
                retBuf.append("user name = ");
                retBuf.append(userAccount.getUsername());
                retBuf.append(", password = ");
                retBuf.append(userAccount.getPassword());
                retBuf.append(", email = ");
                retBuf.append(userAccount.getEmail());
                retBuf.append("<br/>");
            }
        }

        if (retBuf.length() == 0) {
            retBuf.append("No record find.");
        }

        return retBuf.toString();
    }

    /*
     * Mapping url exmaple:
     * http://localhost:8080/userAccount/updateUser?userName=Jerry&password=hello&
     * email=hello_jerry@gmail.com
     */
    @GetMapping(path = "/updateUser")
    @ResponseBody
    public String updateUser(@RequestParam String userName, @RequestParam String password, @RequestParam String email) {

        StringBuffer retBuf = new StringBuffer();

        List<UserAccount> userAccountList = userAccountRepository.findByUsername(userName);

        if (userAccountList != null) {
            for (UserAccount userAccount : userAccountList) {
                userAccount.setUsername(userName);
                userAccount.setPassword(password);
                userAccount.setEmail(email);
                userAccountRepository.save(userAccount);
            }
        }

        retBuf.append("User data update successfully.");

        return retBuf.toString();
    }

    /*
     * Mapping url exmaple:
     * http://localhost:8080/userAccount/deleteByUserName?userName=Richard
     */
    @GetMapping(path = "/deleteByUserName")
    @ResponseBody
    public String deleteByUserName(@RequestParam String userName) {

        StringBuffer retBuf = new StringBuffer();

        userAccountRepository.deleteByUsername(userName);

        retBuf.append("User data has been deleted successfully.");

        return retBuf.toString();
    }

    /*
     * Mapping url exmaple:
     * http://localhost:8080/userAccount/deleteByUserNameAndPassword?userName=
     * Richard&password=888888
     */
    @GetMapping(path = "/deleteByUserNameAndPassword")
    @ResponseBody
    public String deleteByUserNameAndPassword(@RequestParam String userName, @RequestParam String password) {

        StringBuffer retBuf = new StringBuffer();

        userAccountRepository.deleteByUsernameAndPassword(userName, password);

        retBuf.append("User data has been deleted successfully.");

        return retBuf.toString();
    }


}
