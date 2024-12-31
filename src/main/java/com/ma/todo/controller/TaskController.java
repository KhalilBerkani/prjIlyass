package com.ma.todo.controller;


import com.ma.todo.model.Task;
import com.ma.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;




    @GetMapping
    public List<Task> createTask(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Task CreateTask(@RequestBody Task task){
        return taskService.createTask(task);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task newTask) {
        try {
            return ResponseEntity.ok(taskService.saveTask(id, newTask));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}/completed")
    public ResponseEntity<Task> toggleCompleted(@PathVariable Long id) {
        try {
            Task task = taskService.getTaskById(id)
                    .orElseThrow(() -> new RuntimeException("Task not found"));
            task.setCompleted(!task.isCompleted()); // Inverser l'état de completed
            Task updatedTask = taskService.saveTask(id, task);
            return ResponseEntity.ok(updatedTask);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteTask(@PathVariable Long id ){
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }







}
