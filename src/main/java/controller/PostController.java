package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * Created by ovchinnikov on 14.04.2016.
 */
@Controller
public class PostController {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    @ResponseBody
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public String addHome(){
//        return "home";
//    }

    @RequestMapping(value = "/post")
    public String post() {
        return "post";
    }

//    @ResponseBody
//    @RequestMapping(value = "/post", method = RequestMethod.POST)
//    public String addPost() {
//        return "post";
//    }

    @RequestMapping(value = "/category")
    public String category() {
        return "category";
    }
//
//    @ResponseBody
//    @RequestMapping(value = "/category", method = RequestMethod.POST)
//    public String addPostCategory() {
//        return "category";
//    }

}
