package com.app.todoapp.services;

import com.app.todoapp.models.Task;
import com.app.todoapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepository.save(task);
    }

    public void deleteTasks(Long id) {
        taskRepository.deleteById(id);
    }

    public void toggleTasks(Long id) throws IllegalAccessException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessException("Invalid task id"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }
}
