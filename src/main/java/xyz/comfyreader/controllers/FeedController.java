package xyz.comfyreader.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import xyz.comfyreader.models.Feed;
import xyz.comfyreader.models.FeedItem;
import xyz.comfyreader.services.FeedService;

import java.util.List;



@RestController
@RequestMapping("/feeds")
@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
public class FeedController {

    private final FeedService service;

    public FeedController(FeedService service) {
        this.service = service;
    }

    @GetMapping
    public List<Feed> fetchFeeds() {
        return service.fetchFeed();
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void create(@RequestParam String url){
//        service.addFeed(url);
//    }

    @GetMapping("/test")
    public String test(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/posts")
    public List<FeedItem> fetchPosts() {
        return service.fetchPosts();
    }
}
