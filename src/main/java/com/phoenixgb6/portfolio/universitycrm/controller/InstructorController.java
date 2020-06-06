package com.phoenixgb6.portfolio.universitycrm.controller;

import com.phoenixgb6.portfolio.universitycrm.entity.Course;
import com.phoenixgb6.portfolio.universitycrm.entity.Instructor;
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
@RequestMapping("/portfolio/universitycrm/instructors")
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
                                  @RequestParam(name = "page", required = false, defaultValue = "1") int pageNumber,
                                  @RequestParam(name = "paSi", required = false, defaultValue = "15") int pageSize,
                                  @RequestParam(name = "order", required = false, defaultValue = "1") int order,
                                  @RequestParam(name = "search", required = false, defaultValue = "") String search){

        long count;

        if(search.equals("")){ count = instructorService.count(); }
        else{ count = instructorService.count(search); }

        int totalPages = (int) Math.floor(count / pageSize);
        if ((count % pageSize) > 0) {
            totalPages++;
        }

        List<Instructor> instructorsList;
        try{
            instructorsList = instructorService.findAll(pageNumber, pageSize, order, search);
        }
        catch (Exception ex){
            model.addAttribute("project", "universitycrm");
            model.addAttribute("type", 'i');
            throw new BadRequestException("Your browser sent a request that this server could not understand", model);
        }

        model.addAttribute("paSi", pageSize);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalCount", count);
        model.addAttribute("page", pageNumber); //current page
        model.addAttribute("instructors", instructorsList);
        model.addAttribute("order", order);
        model.addAttribute("search", search);

        return "universitycrm/instructor-table";
    }

    @GetMapping("/{id}")
    public String getInstructor(Model model, @PathVariable int id){

        Instructor instructor;

        try{
            instructor = instructorService.findById(id);
        }
        catch (Exception ex){
            model.addAttribute("project", "universitycrm");
            model.addAttribute("type", 'i');
            throw new NotFoundException("Instructor ID not found  -  " + id, model);
        }
        model.addAttribute("individual", instructor);
        model.addAttribute("staff", true);

        return "/universitycrm/individual-profile";
    }

    @PostMapping("/save")
    public String saveInstructor(@ModelAttribute("individual") @Valid Instructor instructor, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("staff", true);
            return "universitycrm/individual-form";
        }

        else{
            // save the instructor
            instructorService.save(instructor);

            // use a redirect to prevent duplicate submissions
            return "redirect:/portfolio/universitycrm/instructors/list";
        }
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

        // redirect to /instructors/list
        return "redirect:/portfolio/universitycrm/instructors/list";

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
    public String removeCourse(@RequestParam("cid") int courseId,
                               @RequestParam("iid") int instructorId, Model model){

        try{
        Instructor instructor = instructorService.findById(instructorId);
        instructor.removeCourse(courseService.findById(courseId));

        instructorService.save(instructor);
        }catch (Exception ex){
            model.addAttribute("project", "universitycrm");
            model.addAttribute("type", 'i');
            throw new NotFoundException("Instructor or course not found", model);
        }

        return "redirect:/portfolio/universitycrm/instructors/" + instructorId;
    }
}