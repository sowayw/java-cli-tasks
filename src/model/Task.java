package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    public static int lastId = 0;
    private final int id;
    private String description;
    private TaskStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(String description) {
        checkDescription(description);

        this.id = ++lastId;
        this.description = description;
        this.status = TaskStatus.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void markAsTodo() {
        this.status = TaskStatus.TODO;
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsInProgress() {
        this.status = TaskStatus.IN_PROGRESS;
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsDone() {
        this.status = TaskStatus.DONE;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return String.format("[Task: %d | Description: %s | Status: %s | Created: %s | Updated: %s]",
                this.id,
                this.description,
                status.getTitleCaseStatus(),
                this.createdAt.format(fmt),
                this.updatedAt.format(fmt)
        );
        }


    private void checkDescription(String description) {
        if(description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description can't be null or empty.");
        }
        if(description.length() > 255) {
            throw new IllegalArgumentException("Description is too long.");
        }
    }
}
