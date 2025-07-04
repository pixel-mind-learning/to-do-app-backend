package com.coveragex.todo.repository;

import com.coveragex.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author maleeshasa
 * @Date 2025-07-04
 */
@Repository
public interface TaskRespository extends JpaRepository<Task, Integer> {

    List<Task> findByCompletedFalseOrderByIdAsc();
}
