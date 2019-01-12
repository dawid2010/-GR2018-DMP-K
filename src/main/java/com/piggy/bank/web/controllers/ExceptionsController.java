package com.piggy.bank.web.controllers;

import com.piggy.bank.web.exceptions.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(AlreadyOpenedTrainingException.class)
    public String alreadyOpenedTrainingExceptionHandler(Model model,
                                                        HttpServletRequest request,
                                                        NoTrainingFoundException exception){
        model.addAttribute("title", "Error: Training is already opened");
        model.addAttribute("errorCode", 500);
        model.addAttribute("message", "Cannot process your request!\nTraining is already opened!");

        return "exception/exceptionTemplate";
    }

    @ExceptionHandler(NoTrainingFoundException.class)
    public String noTrainingFoundExceptionHandler(Model model,
                                                  HttpServletRequest request,
                                                  NoTrainingFoundException exception){
        model.addAttribute("title", "Error: No budget found");
        model.addAttribute("errorCode", 404);
        model.addAttribute("message", "No budget found at this page!");

        return "exception/exceptionTemplate";
    }

    @ExceptionHandler(InternalServerException.class)
    public String internalServerExceptionHandler(Model model,
                                                  HttpServletRequest request,
                                                  InternalServerException exception){
        model.addAttribute("title", "Error: Internal server error");
        model.addAttribute("errorCode", 500);
        model.addAttribute("message", "Server cannot process your request!");

        return "exception/exceptionTemplate";
    }

    @ExceptionHandler(NoAttachmentFoundException.class)
    public String noAttachmentFoundExceptionHandler(Model model,
                                                  HttpServletRequest request,
                                                 NoAttachmentFoundException exception) {
        model.addAttribute("title", "Error: No attachment found");
        model.addAttribute("errorCode", 404);
        model.addAttribute("message", "No attachment found at this page!");

        return "exception/exceptionTemplate";
    }

    @ExceptionHandler(NoExpectanceList.class)
    public String noExpectanceListExceptionHandler(Model model,
                                                  HttpServletRequest request,
                                                 NoExpectanceList exception){
        model.addAttribute("title", "Error: Internal server error");
        model.addAttribute("errorCode", 500);
        model.addAttribute("message", "Cannot prepare expectance list!");

        return "exception/exceptionTemplate";
    }

    @ExceptionHandler(NoPeopleFoundException.class)
    public String noPeopleFoundExceptionHandler(Model model,
                                                  HttpServletRequest request,
                                                 NoPeopleFoundException exception){
        model.addAttribute("title", "Error: No people found");
        model.addAttribute("errorCode", 404);
        model.addAttribute("message", "No people found at this page!");

        return "exception/exceptionTemplate";
    }

    @ExceptionHandler(NoPersonFoundException.class)
    public String noPersonFoundExceptionHandler(Model model,
                                                  HttpServletRequest request,
                                                 NoPersonFoundException exception){
        model.addAttribute("title", "Error: No person found");
        model.addAttribute("errorCode", 404);
        model.addAttribute("message", "No person found at this page!");

        return "exception/exceptionTemplate";
    }

    @ExceptionHandler(WrongArgumentException.class)
    public String wrongArgumentExceptionHandler(Model model,
                                                HttpServletRequest request,
                                                WrongArgumentException exception){
        model.addAttribute("title", "Error: No required argument");
        model.addAttribute("errorCode", 400);
        model.addAttribute("message", "Cannot find required argument!");

        return "exception/exceptionTemplate";
    }
}
