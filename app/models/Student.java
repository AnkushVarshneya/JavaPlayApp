package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Student extends Model {

    public static Finder<Long, Student> find = new Finder<Long,Student>(Student.class);

    @Id
    private Long id;

    @Constraints.Required
    private String name;

    @ManyToMany( targetEntity=Course.class )
    private List<Course> Courses;

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

    public List<Course> getCourses() {
        return Courses;
    }

    public void setCourses(List<Course> courses) {
        Courses = courses;
    }
}