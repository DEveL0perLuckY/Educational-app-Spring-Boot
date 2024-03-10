package com.learningplatform.app.smart_learn.loginController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learningplatform.app.smart_learn.domain.Course;
import com.learningplatform.app.smart_learn.domain.User;
import com.learningplatform.app.smart_learn.repos.CourseRepository;
import com.learningplatform.app.smart_learn.repos.UserRepository;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/")
    public String getHome() {
        return "home/home";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "home/about";
    }

    @GetMapping("/contact")
    public String getContact() {
        return "home/contact";
    }

    @GetMapping("/courses")
    public String getCoCoursesntact(Model model) {
        List<Course> allCourses = courseRepository.findAll();

        Map<User, List<Course>> coursesByTutor = allCourses.stream()
                .collect(Collectors.groupingBy(Course::getTutor));

        model.addAttribute("coursesByTutor", coursesByTutor);

        return "home/Courses";
    }

    @Autowired
    UserRepository userRepository;

    @GetMapping("/search")
    public String getMethodName(@RequestParam String searchTerm, Model model) {
        Optional<User> user = userRepository.findByUsername(searchTerm);
        if (user.isPresent()) {
            List<Course> all = courseRepository.findByTutor(user.get());
            Map<User, List<Course>> coursesByTutor = all.stream()
                    .collect(Collectors.groupingBy(Course::getTutor));
            model.addAttribute("coursesByTutor", coursesByTutor);

            return "home/Courses";
        } else {
            model.addAttribute("coursesByTutor", Collections.emptyMap());

            return "home/Courses";

        }
    }

}
