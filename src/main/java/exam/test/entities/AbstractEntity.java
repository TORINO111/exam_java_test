package exam.test.entities;


import jakarta.persistence.GeneratedValue;
import lombok.Data;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Data
@MappedSuperclass
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
}
