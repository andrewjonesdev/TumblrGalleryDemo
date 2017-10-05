package byaj.controllers;

import byaj.configs.CloudinaryConfig;
import byaj.models.*;
import byaj.repositories.*;
import byaj.validators.UserValidator;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

/**
 * Created by student on 7/10/17.
 */
@Controller
public class HomeController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private byaj.services.UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SearchRepository searchRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private ProfileBuilderRepository profileBuilderRepository;

    @Autowired
    private PostBuilderRepository postBuilderRepository;

    @Autowired
    private CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String home(Model model) {
        List<Video> videos= videoRepository.findAllByOrderByVideoIDAsc();
        int range = ((videos.size()-1) - 0) + 1;
        Video randomVid = videos.get(((int)(Math.random() * range) + 0));
        String randomVidStr = randomVid.getVidUrl().substring(0,randomVid.getVidUrl().length()-3);
        model.addAttribute("randomVid", randomVidStr);
        model.addAttribute("videos", videos);
        String page = "home";
        String home = "home";
        String register = "register";
        String login = "login";
        model.addAttribute("page", page);
        model.addAttribute("home", home);
        model.addAttribute("register", register);
        model.addAttribute("login",login);
        return "index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model) {
        model.addAttribute("search", new Search());
        model.addAttribute("user", new User());
        List<Video> videos= videoRepository.findAllByOrderByVideoIDAsc();
        int range = ((videos.size()-1) - 0) + 1;
        Video randomVid = videos.get(((int)(Math.random() * range) + 0));
        String randomVidStr = randomVid.getVidUrl().substring(0,randomVid.getVidUrl().length()-3);
        model.addAttribute("randomVid", randomVidStr);
        String page = "register";
        String home = "home";
        String register = "register";
        String login = "login";
        model.addAttribute("page", page);
        model.addAttribute("home", home);
        model.addAttribute("register", register);
        model.addAttribute("login",login);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        model.addAttribute("search", new Search());

        model.addAttribute("user", user);
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            return "register";
        } else {
           /* if (user.getRoleSettings().toUpperCase().equals("ADMIN")) {
                //user.setRoles(Arrays.asList(adminRole));
                //userRepository.save(user);
                userService.saveAdmin(user);
            }
            if (user.getRoleSettings().toUpperCase().equals("EMPLOYER")) {
                //user.setRoles(Arrays.asList(employerRole));
                //userRepository.save(user);
                userService.saveEmployer(user);
            }
            if (user.getRoleSettings().toUpperCase().equals("USER")) {
                //user.setRoles(Arrays.asList(userRole));
                //userRepository.save(user);
                userService.saveUser(user);
                model.addAttribute("message", "User Account Successfully Created");
            }*/
            user.setPicUrl("http://res.cloudinary.com/andrewjonesdev/image/upload/c_scale,h_100/profilepic_kos4l4.jpg");
            user.setPicOriginUrl("http://res.cloudinary.com/andrewjonesdev/image/upload/profilepic_kos4l4.jpg");
            user.setPicDefaultUrl("http://res.cloudinary.com/andrewjonesdev/image/upload/profilepic_kos4l4.jpg");

            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("search", new Search());
        List<Video> videos= videoRepository.findAllByOrderByVideoIDAsc();
        int range = ((videos.size()-1) - 0) + 1;
        Video randomVid = videos.get(((int)(Math.random() * range) + 0));
        String randomVidStr = randomVid.getVidUrl().substring(0,randomVid.getVidUrl().length()-3);
        model.addAttribute("randomVid", randomVidStr);
        String page = "login";
        String home = "home";
        String register = "register";
        String login = "login";
        model.addAttribute("page", page);
        model.addAttribute("home", home);
        model.addAttribute("register", register);
        model.addAttribute("login",login);
        return "login";
    }

}