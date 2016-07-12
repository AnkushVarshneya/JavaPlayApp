package controllers;

import models.*;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

import views.html.*;

import java.util.ArrayList;
import java.util.List;
import com.avaje.ebean.Model;

import javax.inject.Inject;

import static play.libs.Json.toJson;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class Application extends Controller {

    @Inject
    private FormFactory formFactory;

    public Result index() {
        return ok(index.render());
    }

    public Result students() {
        return ok(students.render());
    }

    public Result getStudent(Long Id) {
        Student student = Student.find.byId(Id);
        return ok(toJson(student));
    }

    public Result getStudents() {
        List<Student> studentList = Student.find.all();
        return ok(toJson(studentList));
    }

    public Result createStudent() {
        Student student = formFactory.form(Student.class).bindFromRequest().get();
        student.save();
        return redirect(routes.Application.students());
    }

    public Result updateStudent() {
        Student student = formFactory.form(Student.class).bindFromRequest().get();
        student.update();
        return redirect(routes.Application.students());
    }

    public Result deleteStudent() {
        Student student = formFactory.form(Student.class).bindFromRequest().get();
        student.delete();
        return redirect(routes.Application.students());
    }

    public Result courses() {
        return ok(courses.render());
    }

    public Result getCourse(Long Id) {
        Course course = Course.find.byId(Id);
        return ok(toJson(course));
    }

    public Result getCourses() {
        List<Course> courseList = Course.find.all();
        return ok(toJson(courseList));
    }

    public Result createCourse() {
        Course course = formFactory.form(Course.class).bindFromRequest().get();

        // manually fill the many-to-many relationship
        java.util.Map<String, String[]> urlFormEncoded = play.mvc.Controller.request().body().asFormUrlEncoded();
        if (urlFormEncoded != null) {
            for(String val: urlFormEncoded.get("course.students[]")) {

                Long id = new Long(val);
                course.getStudents().clear();
                course.getStudents().add(Student.find.byId(id));

                play.Logger.debug(val);
            }
        }

        course.save();
        return redirect(routes.Application.courses());
    }

    public Result updateCourse() {
        Course course = formFactory.form(Course.class).bindFromRequest().get();
        course.update();
        return redirect(routes.Application.courses());
    }

    public Result deleteCourse() {
        Course course = formFactory.form(Course.class).bindFromRequest().get();
        course.delete();
        return redirect(routes.Application.courses());
    }
}
