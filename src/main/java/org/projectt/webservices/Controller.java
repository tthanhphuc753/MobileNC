package org.projectt.webservices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@org.springframework.stereotype.Controller
@RequestMapping("api")
public class Controller {

    private final NumberService numberService;

    @Autowired
    public Controller(NumberService numberService) {
        this.numberService = numberService;
    }
    @RequestMapping("/number")
    public String showForm() {
        return "index";
    }

    @PostMapping("/convert")
    public String convertNumberToWords(String number, Model model) {
        try {
            int inputNumber = Integer.parseInt(number);
            String result = numberService.convertNumberToWords(inputNumber);
            model.addAttribute("result", result);
        } catch (NumberFormatException e) {
            model.addAttribute("result", "Vui lòng nhập số nguyên hợp lệ.");
        }
        return "index";
    }

}
