package com.mtime.wordbank.rest;

import com.mtime.wordbank.domain.beans.Order;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mtime on 2016/7/21.
 */
@RestController
@RequestMapping(value = "/home")
public class HomeController {

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public Order create(@RequestBody Order order){

        return order;

    }
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Order update(@RequestBody Order order){

        return order;

    }
    @RequestMapping(value = "get", method = RequestMethod.GET )
    public Order get(@RequestParam(name = "id") int id){
        System.out.println("=================get====================");
        Order order = new Order();
        order.setId(id);
        return order;

    }


}
