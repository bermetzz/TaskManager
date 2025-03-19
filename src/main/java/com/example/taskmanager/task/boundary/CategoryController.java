package com.example.taskmanager.task.boundary;

import com.example.taskmanager.task.domain.Category;
import com.example.taskmanager.task.domain.CategoryDTO;
import com.example.taskmanager.task.domain.CategoryRepository;
import com.example.taskmanager.task.domain.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryDTO> categoryDTOs = categories.stream().map(category ->
                new CategoryDTO(
                        category.getId(),
                        category.getName(),
                        category.getTasks().stream()
                                .map(task -> new TaskDTO(task.getId(), task.getTitle()))
                                .collect(Collectors.toList())
                )
        ).collect(Collectors.toList());

        return ResponseEntity.ok(categoryDTOs);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        category.setId(null);
        category.setTasks(null);
        return ResponseEntity.ok(categoryRepository.save(category));
    }

}


