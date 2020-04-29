package wolox.training.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@ApiModel(description = "Books from the Java training")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column()
    @ApiModelProperty(notes = "The book genre: can be anything")
    @NotNull(message = "genre is required")
    private String genre;

    @ApiModelProperty(notes = "Author from the book")
    @Column(nullable = false)
    @NotNull(message = "author is required")
    private String author;

    @Column(nullable = false)
    @NotBlank(message = "image can't be blank")
    private String image;

    @Column(nullable = false)
    @NotNull(message = "title is required")
    private String title;

    @Column(nullable = false)
    @NotNull(message = "subtitle is required")
    private String subtitle;

    @Column(nullable = false)
    @NotNull(message = "publisher is required")
    private String publisher;

    @Column(nullable = false)
    @NotNull(message = "year is required")
    @Min(value = 1888, message = "year must be equal or between 1888 and 2077")
    @Max(value = 2077, message = "year must be equal or between 1888 and 2077")
    private String year;

    @Column(nullable = false)
    @NotNull(message = "pages is required")
    @Min(value = 1, message = "pages must be greater than 0")
    private String pages;

    @Column(nullable = false)
    @NotNull(message = "isbn is required")
    private String isbn;

    @ManyToMany(mappedBy = "books")
    private List<User> users;

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", publisher='" + publisher + '\'' +
                ", year='" + year + '\'' +
                ", pages='" + pages + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
