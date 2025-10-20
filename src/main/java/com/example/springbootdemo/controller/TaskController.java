package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long nextId = 1L;
    public TaskController(){
tasks. add(new Task(1L,"Create wireframe", "12.09.2025", false));
    }

    private Task findTaskById(Long id) {
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private void updateTask(Long id, String name, String deadlineDate, boolean isCompleted) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setName(name);
            task.setDeadlineDate(deadlineDate);
            task.setCompleted(isCompleted);
        }
    }

    @GetMapping("/lab3-tasks")
    public String tasksPage(Model model) {
        model.addAttribute("tasksList", tasks);
        return "tasks";
    }

    @GetMapping("/task/{id}")
    public String taskDetails(@PathVariable Long id, Model model) {
        model.addAttribute("task", findTaskById(id));
        return "task-details";
    }

    @GetMapping("/add-task")
    public String showAddTaskForm() {
        return "add-task";
    }

    @PostMapping("/add-task")
    public String addTask(String name, String deadlineDate) {
        tasks.add(new Task(nextId++, name, deadlineDate, false));
        return "redirect:/lab3-tasks";
    }

    @GetMapping("/edit-task/{id}")
    public String showEditTaskForm(@PathVariable Long id, Model model) {
        model.addAttribute("task", findTaskById(id));
        return "edit-task";
    }

    @PostMapping("/edit-task/{id}")
    public String editTask(@PathVariable Long id, String name,
                           String deadlineDate, boolean isCompleted) {
        updateTask(id, name, deadlineDate, isCompleted);
        return "redirect:/lab3-tasks";
    }

    @GetMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
        return "redirect:/lab3-tasks";
    }
}
