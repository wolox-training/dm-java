package wolox.training.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import wolox.training.exceptions.books.BookIdMissMatchException;
import wolox.training.exceptions.books.BookNotFoundException;
import wolox.training.models.Book;
import wolox.training.repositories.BookRepository;


@RestController
@RequestMapping("/api/books")
@Api
public class BookController extends SchemaValidatorExceptionHandler {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/title/{bookTitle}")
    @ApiOperation(value = "Giving a Book Title, return the book", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved book"),
            @ApiResponse(code = 404, message = "Book not found")
    })
    public Book findByTitle(
            @ApiParam(value = "bookTitle to find the book", required = true) @PathVariable String bookTitle) {
        Book book = bookRepository.findByTitle(bookTitle)
                .orElseThrow(() -> new BookNotFoundException(
                        "Book with title '" + bookTitle + "' not found"));
        return book;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Book patch(@Valid @RequestBody Book book, @PathVariable Long id) {
        if (book.getId() != id) {
            throw new BookIdMissMatchException();
        }
        bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
        bookRepository.deleteById(id);
    }
}
