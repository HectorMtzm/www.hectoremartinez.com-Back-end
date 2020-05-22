package com.phoenixgb6.portfolio.universitycrm.controller;

import com.phoenixgb6.portfolio.universitycrm.entity.Course;
import com.phoenixgb6.portfolio.universitycrm.entity.Instructor;
import com.phoenixgb6.portfolio.universitycrm.service.ServiceS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

    ServiceS<Instructor> instructorService;
    ServiceS<Course> courseService;

    @Autowired
    public InstructorController(ServiceS<Instructor> instructorService, ServiceS<Course> courseService) {
        this.instructorService = instructorService;
        this.courseService = courseService;
    }

    @GetMapping("/list")
    public String instructorsList(Model model,
                                  @RequestParam(name = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "15") int pageSize){

        long count = instructorService.count();

        int totalPages = (int) Math.floor(count / pageSize);

        if ((count % pageSize) > 0) {
            totalPages++;
        }

        List<Instructor> instructorsList = instructorService.findAll(pageNumber, pageSize);

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalCount", count);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("instructors", instructorsList);

        return "universitycrm/instructor-table";
    }

    @GetMapping("/{id}")
    public String getInstructor(Model model, @PathVariable int id){

        model.addAttribute("individual", instructorService.findById(id));
        model.addAttribute("staff", true);

        return "/universitycrm/individual-profile";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("individual") Instructor instructor) {

        // save the employee
        instructorService.save(instructor);

        // use a redirect to prevent duplicate submissions
        return "redirect:/instructors/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("individualId") int id) {

        Instructor instructor = instructorService.findById(id);

        List<Course> courseList = instructor.getCourses();

        //Remove the instructor from his registered classes.
        for(Course course : courseList){
            course.setInstructor(null);
        }

        //save changes
        instructorService.save(instructor);

        // delete the instructor
        instructorService.deleteById(id);

        // redirect to /employees/list
        return "redirect:/instructors/list";

    }

    @GetMapping("/addForm")
    public String addForm(Model model) {

        // create model attribute to bind form data
        Instructor instructor = new Instructor();

        model.addAttribute("individual", instructor);
        model.addAttribute("staff", true);


        return "universitycrm/individual-form";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam("individualId") int id, Model model) {

        Instructor instructor = instructorService.findById(id);

        model.addAttribute("individual", instructor);
        model.addAttribute("staff", true);

        // send over to our form
        return "universitycrm/individual-form";
    }

    @GetMapping("/removeCourse")
    public String removeCourse(@RequestParam("courseId") int courseId,
                               @RequestParam("instructorId") int instructorId){

        Instructor instructor = instructorService.findById(instructorId);
        instructor.removeCourse(courseService.findById(courseId));

        instructorService.save(instructor);

        return "redirect:/instructors/" + instructorId;
    }
}