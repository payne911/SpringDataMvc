package springTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springTest.model.Person;
import springTest.service.PersonService;


@Controller
public class MyController {

    @Autowired
    PersonService personService;

    @RequestMapping("/add1")
    public ModelAndView add1(@RequestParam(value="email") String email,
            @RequestParam(value="firstName") String fn,
            @RequestParam(value="lastName") String ln) {

        System.out.println("adding @RequestParam: " + email + fn + ln);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("generic");
        mv.getModel().put("title", "Subscribed!");
        mv.getModel().put("msg", "Welcome! (via @RequestParam)");
        mv.getModel().put("person", new Person(email, fn, ln));

        return mv;
    }

    @RequestMapping("/add2")
    public ModelAndView add2(@ModelAttribute Person person) {

        System.out.println("adding @ModelAttribute: " + person.getEmail() + person.getFirstName() + person.getLastName());

        ModelAndView mv = new ModelAndView("generic");
        mv.getModel().put("title", "Subscribed!");
        mv.getModel().put("msg", "Welcome! (via @ModelAttribute)");
        mv.getModel().put("person", person);

        return mv;
    }

    @RequestMapping("/add3")
    public String add3(Person person, RedirectAttributes redirAttr) {

        System.out.println(person);
        redirAttr.addFlashAttribute("person", person);
        return "redirect:/rest/add";
    }



    @RequestMapping("/add4")
    @ResponseBody
    public Person add4(@ModelAttribute Person person) {
        return personService.createPerson(person);
    }


    @PostMapping("/remove")
    public ModelAndView remove(@RequestParam(value="email") String email) {

        System.out.println("removing: " + email);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("generic");
        mv.getModel().put("title", "Ciao!");
        mv.getModel().put("msg", "The following user has been removed: " + email);

        return mv;
    }

    @RequestMapping("/")
    public String home() {

        System.out.println("home");



        return "index";
    }
}
