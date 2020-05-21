package com.phoenixgb6.portfolio.universitycrm.controller;

import com.phoenixgb6.portfolio.universitycrm.entity.Course;
import com.phoenixgb6.portfolio.universitycrm.entity.Student;
import com.phoenixgb6.portfolio.universitycrm.service.ServiceS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    ServiceS<Student> studentService;
    ServiceS<Course> courseService;

    @Autowired
    public StudentController(ServiceS<Student> studentService, ServiceS<Course> courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/list")
    public String studentsList(Model model){

        List<Student> studentsList = studentService.findAll();

        model.addAttribute("students", studentsList);

        return "universitycrm/student-table";
    }

    @GetMapping("/{id}")
    public String getStudent(Model model, @PathVariable int id){

        model.addAttribute("individual", studentService.findById(id));
        model.addAttribute("staff", false);

        return "/universitycrm/individual-profile";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("individual") Student student) {

        // save the employee
        studentService.save(student);

        // use a redirect to prevent duplicate submissions
        return "redirect:/students/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("individualId") int id) {

        // delete the employee
        studentService.deleteById(id);

        // redirect to /employees/list
        return "redirect:/students/list";

    }

    @GetMapping("/addForm")
    public String addForm(Model model) {

        // create model attribute to bind form data
        Student student = new Student();

        model.addAttribute("individual", student);
        model.addAttribute("staff", false);


        return "universitycrm/individual-form";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam("individualId") int id, Model model) {

        Student student = studentService.findById(id);

        model.addAttribute("individual", student);
        model.addAttribute("staff", false);

        // send over to our form
        return "universitycrm/individual-form";
    }

    @GetMapping("/dropCourse")
    public String dropCourse(@RequestParam("studentId") int studentId,
                            @RequestParam("courseId") int courseId){

        Student student = studentService.findById(studentId);

        //Drop course without helper class
//        List<Course> courseList = student.getCourses();
//
//        for(int i = 0; i < courseList.size(); i++){
//            if(courseList.get(i).getId() == courseId){
//                courseList.remove(i);
//                break;
//            }
//        }
//
//        student.setCourses(courseList);

        student.dropCourse(courseService.findById(courseId));

        studentService.save(student);

        return "redirect:/students/" + studentId;
    }
}