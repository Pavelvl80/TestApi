package com.controller;

import com.db.UsersDb;
import com.google.gson.Gson;
import com.model.User;
import com.sun.org.apache.xerces.internal.util.URI;
import org.springframework.beans.factory.parsing.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Edvard Piri on 14.05.2017.
 */
@Controller
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public
    @ResponseBody
    User test() {
        User user = new User(1, 1, "name");
        return user;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public
    @ResponseBody
    Set<User> getAll() {
        return UsersDb.getAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    User getById(@PathVariable String id) {
        int intId = Integer.parseInt(id);
        return UsersDb.get(intId);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public
    @ResponseBody
    User editById(@RequestBody User user) {
        return UsersDb.save(user);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    User delete(@PathVariable String id) {
        int intId = Integer.parseInt(id);
        return UsersDb.delete(intId);
    }
}
