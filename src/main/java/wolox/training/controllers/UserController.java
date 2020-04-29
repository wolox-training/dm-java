package wolox.training.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import wolox.training.exceptions.SchemaValidatorExceptionHandler;
import wolox.training.exceptions.users.UserNotFoundException;
import wolox.training.models.Book;
import wolox.training.models.User;
import wolox.training.repositories.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController extends SchemaValidatorExceptionHandler {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/username/{username}")
    public User findByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with username " + username + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable long id) {
        return userRepository.save(user);
    }

    @PostMapping("/{id}/book")
    public User addBook(@RequestBody List<Book> books, @PathVariable long id) {
        return userRepository.findById(id).map(user -> {
            user.addBooks(books);
            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }
}
