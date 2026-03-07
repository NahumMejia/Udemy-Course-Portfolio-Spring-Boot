package com.portfolio.demo.Exception.Handler;

import com.portfolio.demo.Exception.ValidationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException ex, Model model) {
        model.addAttribute("message", ex.getBindingResult().getAllErrors());
        model.addAttribute("message", "Errores de validación");
        return "error/validation";
    }
}
