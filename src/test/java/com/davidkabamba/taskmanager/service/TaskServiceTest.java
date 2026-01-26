package com.davidkabamba.taskmanager.service;

import com.davidkabamba.taskmanager.model.Task;
import com.davidkabamba.taskmanager.model.TaskStatus;
import com.davidkabamba.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    private TaskRepository repository;
    private TaskService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(TaskRepository.class);
        service = new TaskService(repository);
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(List.of(new Task()));

        List<Task> tasks = service.findAll();

        assertEquals(1, tasks.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Task task = Task.builder()
                .id(1L)
                .title("Test")
                .status(TaskStatus.TODO)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(task));

        Task result = service.findById(1L);

        assertEquals("Test", result.getTitle());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testCreate() {
        Task task = Task.builder().title("New Task").build();

        when(repository.save(task)).thenReturn(task);

        Task result = service.create(task);

        assertEquals("New Task", result.getTitle());
        verify(repository, times(1)).save(task);
    }

    @Test
    void testDelete() {
        doNothing().when(repository).deleteById(1L);

        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
