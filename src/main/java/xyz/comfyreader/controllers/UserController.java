package xyz.comfyreader.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.comfyreader.models.RegistrationRequest;
import xyz.comfyreader.models.Url;
import xyz.comfyreader.models.User;
import xyz.comfyreader.services.UserService;

import java.net.URL;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody RegistrationRequest user){
        try{
            userService.createUser(user);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/addUrl")
    public ResponseEntity<?> addUrl(@RequestBody Url url){
        try{
            userService.addUrl(url.url());
            return new ResponseEntity<> ("Created", HttpStatus.CREATED);
        }catch(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/removeUrl")
    public ResponseEntity<?> removeUrl(@RequestBody Url url) {
        try{
            userService.removeUrl(url.url());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/id")
    public Optional<User> findById(@RequestParam String id) {
        return userService.findById(id);
    }
}
