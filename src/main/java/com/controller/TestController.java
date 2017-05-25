package com.controller;

import com.db.UsersDb;
import com.google.gson.Gson;
import com.model.User;
import com.sun.org.apache.xerces.internal.util.URI;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ContentType;
import org.springframework.beans.factory.parsing.Location;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
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
    public ResponseEntity<String> getAll() {
        Set<User> users = UsersDb.getAll();
        if (users == null || users.size() == 0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json").body(new Gson().toJson(users));
    }

//    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
//    public
//    @ResponseBody
//    User getById(@PathVariable String id) {
//        int intId = Integer.parseInt(id);
//        return UsersDb.get(intId);
//    }


    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getById(@PathVariable String id) {
        int intId = Integer.parseInt(id);
        User user = UsersDb.get(intId);
        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json").body(new Gson().toJson(user));
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public
    @ResponseBody
    User editById(@RequestBody User user) {
        return UsersDb.save(user);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> delete(@PathVariable String id) {
        int intId = Integer.parseInt(id);
        User user = UsersDb.delete(intId);
        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json").body(new Gson().toJson(user));
    }
}
