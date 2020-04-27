package wolox.training.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import wolox.training.exceptions.users.BookAlreadyOwnedException;

@Entity
@Table(name = "users")
@ApiModel(description = "Users from the Java training")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The username that represent user. Equivalent to nick")
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthday;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Book> books;

    public User() {
        this.books = Collections.emptyList();
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book newBook) {
        exceptionIfHasBook(newBook);
        this.books.add(newBook);
    }

    public void addBooks(List<Book> books) {
        books.forEach(this::exceptionIfHasBook);
        this.books.addAll(books);
    }

    private void exceptionIfHasBook(Book newBook) {
        this.books.stream()
                .filter(book -> book.getId() == newBook.getId())
                .findAny()
                .ifPresent(s -> {
                    throw new BookAlreadyOwnedException(
                            "Book with id " + newBook.getId() + " already exists on User");
                });
    }
}
