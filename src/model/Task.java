package model;

import java.time.LocalDateTime;

public class Task {
    public static int lastId = 0;
    private final int id;
    private String description;
    private Status status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    /**
     * Public constructor for creating a new task by a user.
     * Automatically assigns the next available ID and sets the initial status to TODO.
     *
     * @param description Brief text describing the task.
     * @throws IllegalArgumentException if the description is null, empty, or exceeds 255 characters.
     */
    public Task(String description) {
        checkDescription(description);

        this.id = ++lastId;
        this.description = description;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Private constructor used for reconstructing a task from persistent storage (e.g., JSON).
     * This allows manual setting of all fields, including ID and timestamps.
     */
    private Task(int id, String description, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void markAsInProgress() {
        this.status = Status.IN_PROGRESS;
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsDone() {
        this.status = Status.DONE;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public String toJson() {
        return String.format(
                "{\"id\":%d, \"description\":\"%s\", \"status\":\"%s\", \"createdAt\":\"%s\", \"updatedAt\":\"%s\"}",
                id, description, status.getApiLabel(), createdAt, updatedAt
        );
    }

    public static Task fromJson(String json) {
        int id = Integer.parseInt(extractValue(json, "id"));
        String desc = extractValue(json, "description");

        String statusStr = extractValue(json, "status");
        Status status = Status.valueOf(statusStr.toUpperCase().replace("-", "_"));

        LocalDateTime created = LocalDateTime.parse(extractValue(json, "createdAt"));
        LocalDateTime updated = LocalDateTime.parse(extractValue(json, "updatedAt"));

        if (id > lastId) {
            lastId = id;
        }

        return new Task(id, desc, status, created, updated);
    }

    private static String extractValue(String json, String key) {
        String keyPattern = "\"" + key + "\":";
        int startIndex = json.indexOf(keyPattern);

        if (startIndex == -1) return "";

        startIndex += keyPattern.length();

        if (json.charAt(startIndex) == '"') {
            startIndex++;
            int endIndex = json.indexOf("\"", startIndex);
            return json.substring(startIndex, endIndex);
        } else {
            int endIndex = json.indexOf(",", startIndex);
            if (endIndex == -1) endIndex = json.indexOf("}", startIndex);
            return json.substring(startIndex, endIndex).trim();
        }
    }

    @Override
    public String toString() {
        return String.format(
                "id: %d, description: %s, status: %s, createdAt: %s, updatedAt: %s ",
                id, description, status.getDisplay(), createdAt, updatedAt
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