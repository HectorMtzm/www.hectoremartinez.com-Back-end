package com.phoenixgb6.portfolio.universitycrm.controller;

import com.phoenixgb6.portfolio.universitycrm.entity.Course;
import com.phoenixgb6.portfolio.universitycrm.entity.Instructor;
import com.phoenixgb6.portfolio.universitycrm.entity.Student;
import com.phoenixgb6.portfolio.universitycrm.service.ServiceS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    ServiceS<Course> courseService;
    ServiceS<Instructor> instructorService;
    ServiceS<Student> studentService;

    @Autowired
    public CourseController(ServiceS<Course> courseService, ServiceS<Instructor> instructorService, ServiceS<Student> studentService) {
        this.courseService = courseService;
        this.instructorService = instructorService;
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public String coursesList(Model model,
                              @RequestParam(name = "page", required = false, defaultValue = "1") int pageNumber,
                              @RequestParam(name = "paSi", required = false, defaultValue = "15") int pageSize,
                              @RequestParam(name = "order", required = false, defaultValue = "1") int order,
                              @RequestParam(name = "search", required = false, defaultValue = "") String search){

        long count;

        if(search.equals("")){ count = courseService.count(); }
        else{ count = courseService.count(search); }

        int totalPages = (int) Math.floor(count / pageSize);

        if ((count % pageSize) > 0) {
            totalPages++;
        }

        List<Course> coursesList = courseService.findAll(pageNumber, pageSize, order, search);

        model.addAttribute("paSi", pageSize);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalCount", count);
        model.addAttribute("page", pageNumber); //current page
        model.addAttribute("courses", coursesList);
        model.addAttribute("order", order);
        model.addAttribute("search", search);

        return "universitycrm/course-table";
    }

    @GetMapping("/{id}")
    public String getCourse(Model model, @PathVariable int id){

        model.addAttribute("course", courseService.findById(id));

        return "/universitycrm/course-profile";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("course") Course course, @RequestParam("instructorId") int instructorId) {

        if(instructorId == 0){
            course.setInstructor(null);
        }
        else {
            course.setInstructor(instructorService.findById(instructorId));
        }

        // save the employee
        courseService.save(course);

        // use a redirect to prevent duplicate submissions
        return "redirect:/courses/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("courseId") int id) {

        // delete the employee
        courseService.deleteById(id);

        // redirect to /employees/list
        return "redirect:/courses/list";

    }

    @GetMapping("/addForm")
    public String addForm(Model model) {

        // create model attribute to bind form data
        Course course = new Course();
        List<Instructor> instructorList = instructorService.findAll();

        model.addAttribute("course", course);
        model.addAttribute("instructorList", instructorList);
        model.addAttribute("instructorId", 0);

        return "universitycrm/course-form";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam("courseId") int id, Model model, Integer instructorId) {

        Course course = courseService.findById(id);
        List<Instructor> instructorList = instructorService.findAll();

        Instructor instructor = course.getInstructor();
        if(instructor != null){
            instructorId = course.getInstructor().getId();
        }

        model.addAttribute("course", course);
        model.addAttribute("instructorList", instructorList);
        model.addAttribute("instructorId", instructorId);

        // send over to our form
        return "universitycrm/course-form";
    }

}