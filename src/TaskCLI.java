import Service.TaskService;
import Service.TaskServiceImpl;
import model.Task;

import java.util.List;

public class TaskCLI {
    public static void main(String[] args) {
        if (args.length == 0) {
            printHelp();
            return;
        }

        TaskService service = new TaskServiceImpl();
        String command = args[0].toLowerCase();

        try {
            switch (command) {
                case "add" -> {
                    if (args.length < 2) throw new IllegalArgumentException("Missing description");
                    service.add(args[1]);
                }
                case "update" -> {
                    if (args.length < 3) throw new IllegalArgumentException("Missing ID or description");
                    service.update(Integer.parseInt(args[1]), args[2]);
                    System.out.println("Task updated successfully.");
                }
                case "delete" -> {
                    if (args.length < 2) throw new IllegalArgumentException("Missing ID");
                    service.delete(Integer.parseInt(args[1]));
                    System.out.println("Task deleted successfully.");
                }
                case "mark-in-progress" -> {
                    service.markInProgress(Integer.parseInt(args[1]));
                    System.out.println("Status updated.");
                }
                case "mark-done" -> {
                    service.markDone(Integer.parseInt(args[1]));
                    System.out.println("Status updated.");
                }
                case "list" -> {
                    String filter = (args.length > 1) ? args[1] : null;
                    List<Task> tasks = service.list();

                    if (filter == null) {
                        tasks.forEach(System.out::println);
                    } else {
                        tasks.stream()
                                .filter(t -> t.getStatus().getApiLabel().equalsIgnoreCase(filter))
                                .forEach(System.out::println);
                    }
                }
                default -> {
                    System.out.println("Unknown command: " + command);
                    printHelp();
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void printHelp() {
        System.out.println("""
            Usage:
              TaskCLI add "description"
              TaskCLI delete <id>
              TaskCLI update <id> "new description"
              TaskCLI mark-in-progress <id>
              TaskCLI mark-done <id>
              TaskCLI list [status]
            """);
    }
}