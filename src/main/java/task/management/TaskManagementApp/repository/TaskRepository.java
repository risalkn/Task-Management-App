package task.management.TaskManagementApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.management.TaskManagementApp.model.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
