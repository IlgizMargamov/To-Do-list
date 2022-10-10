
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    // standard constructors

    // standard getters and setters
}