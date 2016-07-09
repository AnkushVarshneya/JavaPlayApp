package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Course extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String name;



    public static Finder<Long, Student> find = new Finder<Long,Student>(Student.class);
}