package com.app.todoapp.controller;

import com.app.todoapp.models.Task;
import com.app.todoapp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")  // You can also add "/tasks" here if needed
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks"; // This means src/main/resources/templates/tasks.html should exist
    }

    @PostMapping  // You can also add "/tasks" here if needed
    public String createTask(@RequestParam String title) {
        taskService.createTask(title);
        return "redirect:/"; // This means src/main/resources/templates/tasks.html should exist
    }
    @GetMapping("/{id}/delete")  // You can also add "/tasks" here if needed
    public String deleteTasks(@PathVariable Long id) {
        taskService.deleteTasks(id);
        return "redirect:/"; // This means src/main/resources/templates/tasks.html should exist
    }
    @GetMapping("/{id}/toggle")
    public String toggleTasks(@PathVariable Long id) {
        try {
            taskService.toggleTasks(id);
        } catch (IllegalAccessException e) {
            // Optional: log error or handle accordingly
            e.printStackTrace();
        }
        return "redirect:/"; // Make sure tasks.html exists at templates directory
    }

}
