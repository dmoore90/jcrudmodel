package com.jcrud.Controllers;

import com.jcrud.Entity.Post;
import com.jcrud.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String getPost(Model model) {
        List<Post> listPosts = postService.getAll();
        model.addAttribute("listPosts", listPosts);
        model.addAttribute("post", new Post());
        return "index";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String savePost(@ModelAttribute("post") Post post) {
        postService.savePost(post);
        return "redirect:/";
    }

    @RequestMapping(value="/update_post/{id}")
    public String getPost(@PathVariable long id, Model model){
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        return "update_post";
    }

    @RequestMapping(value="/deletePost/{id}")
    public String deletePost(@PathVariable long id) {
        postService.deletePost(id);
        return "redirect:/";
    }
}
