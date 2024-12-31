package com.ma.todo.service;

import com.ma.todo.model.Task;
import com.ma.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks (){
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }

    public Task saveTask(Long id, Task newTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(newTask.getTitle());
        task.setDescription(newTask.getDescription());
        task.setCompleted(newTask.isCompleted());
        return taskRepository.save(task);
    }

    public void deleteTaskById(Long id ){
        taskRepository.deleteById(id);
    }


}
