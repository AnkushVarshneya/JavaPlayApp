package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Course extends Model {

    public static Finder<Long, Course> find = new Finder<Long,Course>(Course.class);

    @Id
    private Long id;

    @Constraints.Required
    private String name;

    @ManyToMany( targetEntity=Student.class )
    @JoinTable(
            name="Student_Enroll",
            joinColumns=@JoinColumn(name="cid", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="sid", referencedColumnName="id"))
    private List<Student> students;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}