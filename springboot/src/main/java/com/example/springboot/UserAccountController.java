package com.example.springboot;

import com.example.springboot.UserAccount;
import com.example.springboot.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/hi")

public class UserAccountController {
    @Autowired
    UserAccountRepository userAccountRepository;

    @GetMapping("/userAccounts")
    public List<UserAccount> getAllUser() {
        return userAccountRepository.findAll();
    }

    @PostMapping("/userAccounts")
    public String createUser(@RequestBody UserAccount userAccount) {

        int count=0;
        String a = userAccount.getEmail();
        String b = userAccount.getUsername();
        List<UserAccount> userlist = userAccountRepository.findAll();
        for(UserAccount userAccount1 : userlist)
        {
            if(userAccount1.getEmail().equals(a) || userAccount1.getUsername().equals(b))
                count++;
        }
        if (count!=0)
            return "already exists";
        else {
            userAccountRepository.save(userAccount);
            return "created";
        }

    }

    @PutMapping("/userAccounts/{username}")
    public String updateNote(@PathVariable(value = "username") String userName,
                            @RequestBody UserAccount userDetails) {

        List<UserAccount> userlist = userAccountRepository.findAll();
        for(UserAccount userAccount : userlist)
        {
            if(userAccount.getUsername().equals(userName)) {
                userAccount.setEmail(userDetails.getEmail());
                userAccount.setPassword(userDetails.getPassword());
                userAccountRepository.save(userAccount);
                }
        }

        return "updatedNote";
    }

    @DeleteMapping("/userAccounts/{name}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "name") String userName) {
        List<UserAccount> userlist = userAccountRepository.findAll();
        for(UserAccount userAccount : userlist)
        {
            if(userAccount.getUsername().equals(userName)) {
                userAccountRepository.delete(userAccount);
            }
        }
        return ResponseEntity.ok().build();
    }
}

