package com.example.springbootdemo.controller;
import com.example.springbootdemo.model.ApplicationRequest;
import com.example.springbootdemo.service.ApplicationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationRequestController {
    private final ApplicationRequestService applicationRequestService;

    @GetMapping
    public String allApplications(Model model) {
        model.addAttribute("applications", applicationRequestService.getAllApplications());
        return "applications";
    }

    @GetMapping("/pending")
    public String pendingApplications(Model model) {
        model.addAttribute("applications", applicationRequestService.getPendingApplications());
        return "pending-applications";
    }

    @GetMapping("/processed")
    public String processedApplications(Model model) {
        model.addAttribute("applications", applicationRequestService.getProcessedApplications());
        return "processed-applications";
    }

    @GetMapping("/add")
    public String showAddApplicationForm(Model model) {
        model.addAttribute("courses", applicationRequestService.getAllCourses());
        return "add-application";
    }

    @PostMapping("/add")
    public String addApplication(
            @RequestParam("userName") String userName,
            @RequestParam("courseId") Long courseId,
            @RequestParam("commentary") String commentary,
            @RequestParam("phone") String phone
    ) {
        ApplicationRequest application = new ApplicationRequest();
        application.setUserName(userName);
        application.setCommentary(commentary);
        application.setPhone(phone);

        applicationRequestService.addApplication(application, courseId);
        return "redirect:/applications";
    }

    @GetMapping("/{id}")
    public String applicationDetails(@PathVariable Long id, Model model) {
        ApplicationRequest application = applicationRequestService.getApplicationById(id);
        if (application == null) {
            return "redirect:/applications";
        }
        model.addAttribute("app", application);
        return "application-detail";
    }

    @GetMapping("/{id}/assign-operators")
    public String showAssignOperatorsForm(@PathVariable Long id, Model model) {
        ApplicationRequest application = applicationRequestService.getApplicationById(id);
        if (application == null || application.isHandled()) {
            return "redirect:/applications/" + id;
        }

        model.addAttribute("app", application);
        model.addAttribute("operators", applicationRequestService.getAllOperators());
        return "assign-operators";
    }

    @PostMapping("/{id}/assign-operators")
    public String assignOperators(
            @PathVariable Long id,
            @RequestParam("operatorIds") List<Long> operatorIds
    ) {
        applicationRequestService.assignOperators(id, operatorIds);
        return "redirect:/applications/" + id;
    }

    @PostMapping("/{requestId}/remove-operator/{operatorId}")
    public String removeOperator(
            @PathVariable Long requestId,
            @PathVariable Long operatorId
    ) {
        applicationRequestService.removeOperator(requestId, operatorId);
        return "redirect:/applications/" + requestId;
    }

    @PostMapping("/{id}/delete")
    public String deleteApplication(@PathVariable Long id) {
        applicationRequestService.deleteApplication(id);
        return "redirect:/applications";
    }
}