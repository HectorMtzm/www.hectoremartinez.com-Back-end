package com.phoenixgb6.portfolio.universitycrm.controller;

import com.phoenixgb6.portfolio.universitycrm.entity.Course;
import com.phoenixgb6.portfolio.universitycrm.entity.Student;
import com.phoenixgb6.portfolio.universitycrm.exception.BadRequestException;
import com.phoenixgb6.portfolio.universitycrm.exception.NotFoundException;
import com.phoenixgb6.portfolio.universitycrm.service.ServiceS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/portfolio/universitycrm/students")
public class StudentController {

    ServiceS<Student> studentService;
    ServiceS<Course> courseService;

    @Autowired
    public StudentController(ServiceS<Student> studentService, ServiceS<Course> courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/list")
    public String studentsList(Model model,
                               @RequestParam(name = "page", required = false, defaultValue = "1") int pageNumber,
                               @RequestParam(name = "paSi", required = false, defaultValue = "15") int pageSize,
                               @RequestParam(name = "order", required = false, defaultValue = "1") int order,
                               @RequestParam(name = "search", required = false, defaultValue = "") String search){

        long count;

        if(search.equals("")){ count = studentService.count(); }
        else{ count = studentService.count(search); }

        int totalPages = (int) Math.floor(count / pageSize);
        if ((count % pageSize) > 0) {
            totalPages++;
        }

        List<Student> studentsList;

        try{
            studentsList = studentService.findAll(pageNumber, pageSize, order, search);
        }
        catch (Exception ex){
            model.addAttribute("project", "universitycrm");
            model.addAttribute("type", 's');
            throw new BadRequestException("Your browser sent a request that this server could not understand", model);
        }

        model.addAttribute("paSi", pageSize);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalCount", count);
        model.addAttribute("page", pageNumber); //current page
        model.addAttribute("students", studentsList);
        model.addAttribute("order", order);
        model.addAttribute("search", search);

        return "universitycrm/student-table";
    }

    @GetMapping("/{id}")
    public String getStudent(Model model, @PathVariable("id") int id){

        Student student;

        try{
            student = studentService.findById(id);
        }
        catch (Exception ex){
            model.addAttribute("project", "universitycrm");
            model.addAttribute("type", 's');
            throw new NotFoundException("Student ID not found  -  " + id, model);
        }

        model.addAttribute("individual", student);
        model.addAttribute("staff", false);
        model.addAttribute("cid", null);

        return "/universitycrm/individual-profile";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("individual") @Valid Student student, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("staff", false);
            return "universitycrm/individual-form";
        }

        else{
            // save the student
            studentService.save(student);

            // use a redirect to prevent duplicate submissions
            return "redirect:/portfolio/universitycrm/students/list";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("individualId") int id, Model model) {

        try {
            // delete the student
            studentService.deleteById(id);
        } catch (Exception ex) {
            model.addAttribute("project", "universitycrm");
            model.addAttribute("type", 's');
            throw new NotFoundException("Student not found - ID: " + id, model);
        }

        // redirect to /students/list
        return "redirect:/portfolio/universitycrm/students/list";

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
    public String dropCourse(@RequestParam("sid") int studentId,
                            @RequestParam("cid") int courseId, Model model){

        try{
            Student student = studentService.findById(studentId);
            student.dropCourse(courseService.findById(courseId));
            studentService.save(student);
        }
        catch (Exception ex){
            model.addAttribute("project", "universitycrm");
            model.addAttribute("type", 's');
            throw new NotFoundException("Student or course not found", model);
        }

        return "redirect:/portfolio/universitycrm/students/" + studentId;
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@RequestParam("sid") int studentId, @RequestParam("cid") int courseId, Model model){

        Student student = studentService.findById(studentId);

        try{
            student.addCourse(courseService.findById(courseId));
            studentService.save(student);
        } catch (Exception ex){
            model.addAttribute("project", "universitycrm");
            model.addAttribute("type", 's');
            throw new NotFoundException("Course ID not found - " + courseId, model);
        }

        return "redirect:/portfolio/universitycrm/students/" + studentId;
    }

}






















