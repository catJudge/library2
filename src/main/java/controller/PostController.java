package controller;

import DAO.CategoryDAO;
import DAO.CommentDAO;
import DAO.PostDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import persistence.CategoryPO;
import persistence.CommentPO;
import persistence.PostPO;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        return homeModel();
    }

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView addHome(@ModelAttribute("inputCategory") String[] inputCategory,
                                @ModelAttribute("inputTitle") String inputTitle,
                                @ModelAttribute("inputText") String inputText,
                                @ModelAttribute("inputEmail") String inputEmail) {
        try {
            PostPO postPO = new PostPO();
            Timestamp timestamp = Timestamp.from(Instant.now());
            postPO.setTitle(inputTitle);
            postPO.setText(inputText);
            postPO.setEmail(inputEmail);
            List<CategoryPO> list = new ArrayList<>();
            for (String anInputCategory : inputCategory) {
                list.add(CategoryDAO.getCategoryById((Long.valueOf(anInputCategory))));
            }
            postPO.setCategories(list);
            postPO.setCreatedDate(timestamp);
            PostDAO.insertPost(postPO);
        } catch (Exception ignored) {

        }

        return homeModel();
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public ModelAndView post(@PathVariable("id") String id) {
        return postModel(Long.valueOf(id));
    }

    @ResponseBody
    @RequestMapping(value = "/post/{id}", method = RequestMethod.POST)
    public ModelAndView addPost(@PathVariable("id") String id, @ModelAttribute("inputText") String inputText) {
        try {
            CommentPO commentPO = new CommentPO();
            Timestamp timestamp = Timestamp.from(Instant.now());
            commentPO.setText(inputText);
            commentPO.setCreatedDate(timestamp);
            commentPO.setPost(PostDAO.getPostById(Long.valueOf(id)));
            CommentDAO.insertComment(commentPO);
        } catch (Exception ignored) {

        }
        return postModel(Long.valueOf(id));
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public ModelAndView category(@PathVariable("id") String id) {
        return categoryModel(Long.valueOf(id));
    }

    @ResponseBody
    @RequestMapping(value = "/category/{id}", method = RequestMethod.POST)
    public ModelAndView addPostCategory(@PathVariable("id") String id,
                                        @ModelAttribute("inputEmail") String inputEmail,
                                        @ModelAttribute("inputTitle") String inputTitle,
                                        @ModelAttribute("inputText") String inputText) {
        try {
            PostPO postPO = new PostPO();
            Timestamp timestamp = Timestamp.from(Instant.now());
            postPO.setTitle(inputTitle);
            postPO.setText(inputText);
            postPO.setEmail(inputEmail);
            List<CategoryPO> list = new ArrayList<>();
            list.add(CategoryDAO.getCategoryById(Long.valueOf(id)));
            postPO.setCategories(list);
            postPO.setCreatedDate(timestamp);
            PostDAO.insertPost(postPO);
        } catch (Exception ignored) {

        }
        return categoryModel(Long.valueOf(id));
    }

    private ModelAndView homeModel() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        List<CategoryPO> categoryPOs = CategoryDAO.getAllCategory();
        List<PostPO> allPosts = PostDAO.getAllPosts();
        Integer error = 0;
        modelAndView.addObject("categoryPOs", categoryPOs);
        modelAndView.addObject("allPosts", allPosts);
        modelAndView.addObject("error", error);
        return modelAndView;
    }

    private ModelAndView postModel(Long id) {
        ModelAndView modelAndView = new ModelAndView();
        List<CategoryPO> categoryPOs = CategoryDAO.getAllCategory();
        List<CommentPO> comments = CommentDAO.getCommentsByPostId(id);
        PostPO postPO = PostDAO.getPostById(id);
        modelAndView.setViewName("post");
        modelAndView.addObject("id", id);
        modelAndView.addObject("categoryPOs", categoryPOs);
        modelAndView.addObject("comments", comments);
        modelAndView.addObject("postPO", postPO);
        return modelAndView;
    }

    private ModelAndView categoryModel(Long id) {
        ModelAndView modelAndView = new ModelAndView();
        List<CategoryPO> categoryPOs = CategoryDAO.getAllCategory();
        List<PostPO> posts = CategoryDAO.getPostsByCategoryId(id);
        CategoryPO categoryPO = CategoryDAO.getCategoryById(id);
        modelAndView.setViewName("category");
        modelAndView.addObject("id", id);
        modelAndView.addObject("categoryPOs", categoryPOs);
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("categoryPO", categoryPO);
        return modelAndView;
    }

}
