package thuctap.task4.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.annotation.processing.Generated;
@Data
public class User {
    @Id
    private int id;
    private String name;
    private String address;
    private int age;

}
