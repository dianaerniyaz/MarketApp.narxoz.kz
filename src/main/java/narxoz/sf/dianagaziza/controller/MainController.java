package narxoz.sf.dianagaziza.controller;

import narxoz.sf.dianagaziza.model.Apps;
import narxoz.sf.dianagaziza.model.AppsTypes;
import narxoz.sf.dianagaziza.model.Users;
import narxoz.sf.dianagaziza.repository.AppRepository;
import narxoz.sf.dianagaziza.repository.AppTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private AppTypeRepository typeRepository;


    @GetMapping(value = "/")
    public String indexPage(Model model) {
        List<Apps> appsList = appRepository.findAll();
        List<AppsTypes> typesList = typeRepository.findAll();
        model.addAttribute("appTypes", typesList);
        model.addAttribute("apps", appsList);
        model.addAttribute("user", getCurrentUser());
        return "index";
    }

    @GetMapping(value = "/addApp")
    @PreAuthorize("hasAnyRole('ROLE_DIANA')")

    public String AddPage(Model model) {
        List<AppsTypes> typesList = typeRepository.findAll();
        model.addAttribute("appTypes", typesList);
        return "addApp";
    }

    @PostMapping(value = "/addApp")
    public String AddItem(
            @RequestParam(name = "item_name") String name,
            @RequestParam(name = "item_estimation") float estimation,
            @RequestParam(name = "item_age") int age,
            @RequestParam(name = "memory_size") float size,
            @RequestParam(name = "item_types_id") Long typesId,
            @RequestParam(name = "item_description") String description
    ) {

        AppsTypes type = typeRepository.findById(typesId).orElse(null);

        if (type != null) {
            Apps app = new Apps();
            app.setName(name);
            app.setEstimation(estimation);
            app.setAge_limit(age);
            app.setMemory_size(size);
            app.setDescription(description);
            app.setTypes(type);

            appRepository.save(app);
        }

        return "redirect:/addApp?success";
    }

    @GetMapping(value = "/details/{idd}")
    public String Details(Model model,
                          @PathVariable(name = "idd") Long id
    ) {
        List<AppsTypes> typesList = typeRepository.findAll();
        model.addAttribute("appTypes", typesList);
        Apps currentApp = appRepository.findById(id).orElse(null);
        model.addAttribute("app", currentApp);
        return "details";
    }


    @PostMapping(value = "/update")
    public String Update(
            @RequestParam(name = "item_id") Long id,
            @RequestParam(name = "item_name") String name,
            @RequestParam(name = "item_estimation") float estimation,
            @RequestParam(name = "item_age") int age,
            @RequestParam(name = "memory_size") float size,
            @RequestParam(name = "item_types_id") Long typesId,
            @RequestParam(name = "description") String description
    ) {
        Apps app = appRepository.findById(id).orElse(null);
        AppsTypes type = typeRepository.findById(typesId).orElse(null);


        if (app != null && type != null) {
            app.setName(name);
            app.setEstimation(estimation);
            app.setAge_limit(age);
            app.setMemory_size(size);
            app.setDescription(description);
            app.setTypes(type);

            appRepository.save(app);

        }
        return "redirect:/details/" + id;
    }

    @PostMapping(value = "/delete")
    public String Delete(
            @RequestParam(name = "item_id") Long id
    ) {
        Apps app = appRepository.findById(id).orElse(null);

        String redirect = "/";
        if (app != null) {
            appRepository.delete(app);
            redirect = "/?delete";
        }
        return "redirect:" + redirect;
    }

    @GetMapping(value = "/login")
    public String Login(Model model) {
        model.addAttribute("user", getCurrentUser());
        return "login";
    }
    @GetMapping(value = "/registration")
    public String Regisration(Model model) {
        model.addAttribute("user", getCurrentUser());
        return "registration";
    }


    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String Profile(Model model) {
        model.addAttribute("user", getCurrentUser());
        return "profile";
    }

    @GetMapping(value = "/403")
    public String accessDeniedPage(Model model) {
        return "403";
    }


    private Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Users currentUser = (Users) authentication.getPrincipal();
            return currentUser;
        }
        return null;
    }


}

