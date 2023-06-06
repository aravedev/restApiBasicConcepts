package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    // Get HTTP - student
    @GetMapping("")
    public ResponseEntity<Student> getStudent(){
        // creating Student Object
        Student student = new Student(1,"Danny","Rave Rua");

        // return result
        return ResponseEntity.ok(student);
    }

    // List of Students
    @GetMapping("/list")
    public ResponseEntity<List<Student>> getStudentList(){

        List <Student> students = new ArrayList<>();
        students.add(new Student(1,"Danny","Rave Rua"));
        students.add(new Student(2,"Johana","Rave Rua"));
        students.add(new Student(3,"Sergio","Rave Rua"));

        return ResponseEntity.ok(students);

    }

    // @PathVariable
    // /student/{URI} , our URI is "id" - URI template variable.
    //PathVariable: Binds the Uri {id} from GetMapping to the parameter given into the function.
    // if the param has the same name than URI isnt necessary specify with parenthesis @PathVariable("id")
    // spring will know that we are making reference to the same URI
    // localhost:8080/student/{id}/{first-name}/{last-name}
    @GetMapping("/{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> getStudentId(@PathVariable("id") int studentId,
                                @PathVariable("first-name") String firstName,
                                @PathVariable("last-name") String lastName
                                ){
        Student student = new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);
        //return new Student(studentId, "Danny", "Rave");
    }

    //@RequestParam : It used to perform a query search, extract the URI from URL.
    // localhost:8080/students/query?id=1 , where "?id" is our query parameter "?"
    // @RequestParam binds the query parameter from GetMapping to the parameter given into the function
    // using multiple @RequestParams would be:
    // localhost:8080/students/query?id=1&firstName=Danny&lastName=Rave Rua
    @GetMapping("/query")
    public ResponseEntity< Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName
                                          ){
        Student stu= new Student(id, firstName, lastName);
        return ResponseEntity.ok(stu);
    }

    /*
    * @PutMapping and @RequestBody
    * @RequestBody retrieves the data from HTTP and transform it into Java Object
    * @PutMapping performs the creation of a new object into server
    *
    * */

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getName());
        System.out.println(student.getLastName());

        return ResponseEntity.ok(student); // sending the object to the server
    }

    // PutRequest - Updating existing resources (objects) in the server
    // localhost8080:/students/{id}/update
    // PathVariable - we arent doing a query requirement, we are passing the value
    // directly to the server.

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){
        System.out.println(student.getName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    // DeleteMapping
    // localhost:8080/students/{id}/delete
    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String>  deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student has been deleted");
    }

    // ResponseEntity
    @GetMapping("responseEntity")
    public ResponseEntity<Student> getStudentResponseEntity(){
        Student stu = new Student(1,"Danny","Rave");

        //return ResponseEntity.ok(stu);
        return ResponseEntity.ok().header("custom-header","Danny").body(stu);
    }

}
