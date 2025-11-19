import exception.NoSuchTaskException;
import model.Task;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        printHelp();

        while(true) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine();

            if(command.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                return;
            }

            switch(command.toLowerCase().trim()) {
                case "help" -> printHelp();

                case "add" -> {
                    System.out.print("Enter text of task: ");
                    String task = scanner.nextLine().trim();
                    try {
                        if (task.isEmpty()) {
                            throw new IllegalArgumentException();
                        }
                        tasks.add(new Task(task));
                        System.out.println("Task has successfully added.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Task can't be null or empty.");
                    }
                }

                case "del" -> {
                    if(tasks.isEmpty()) {
                        System.out.println("List is empty, perhaps add tasks.");
                        break;
                    }

                    System.out.print("Enter the task ID: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());

                        Task task = findTask(tasks,id);

                        if(tasks.remove(task)) {
                            System.out.println("Task deleted.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid ID.");
                    } catch (NoSuchTaskException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case "mark-todo" -> {
                    if(tasks.isEmpty()) {
                        System.out.println("List is empty, nothing to mark.");
                        break;
                    }

                    System.out.print("Enter the task ID: ");

                    try {
                        int id = Integer.parseInt(scanner.nextLine());

                        Task task = findTask(tasks, id);
                        task.markAsTodo();

                        System.out.println("Task marked as todo.");

                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid ID.");
                    } catch (NoSuchTaskException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "mark-done" -> {
                    if(tasks.isEmpty()) {
                        System.out.println("List is empty, nothing to mark.");
                        break;
                    }

                    System.out.print("Enter the task ID: ");

                    try {
                        int id = Integer.parseInt(scanner.nextLine());

                        Task task = findTask(tasks, id);
                        task.markAsDone();

                        System.out.println("Task marked as done.");

                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid ID.");
                    } catch (NoSuchTaskException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "mark-in-progress" -> {
                    if(tasks.isEmpty()) {
                        System.out.println("List is empty, nothing to mark.");
                        break;
                    }

                    System.out.print("Enter the task ID: ");

                    try {
                        int id = Integer.parseInt(scanner.nextLine());

                        Task task = findTask(tasks, id);
                        task.markAsInProgress();

                        System.out.println("Task marked as in progress.");

                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid ID.");
                    } catch (NoSuchTaskException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case "list" -> {
                    System.out.println();
                    for(Task s : tasks) {
                        System.out.println(s);
                    }
                    System.out.println();
                }

                default -> System.out.println("Unknown command: " + command + "\nUse 'help' for display commands.\n");
            }
        }
    }

    private static Task findTask(List<Task> tasks, int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchTaskException(String.format("Task with ID %d not found", id)));
    }


    public static void printHelp() {
        System.out.println("""
                - help - Show all commands.
                - add - Add task.
                - del - Delete task.
                - mark-todo - Mark task as todo.
                - mark-done - Mark task as done.
                - mark-in-progress - Mark task as in progress.
                - list - Show all tasks.
                - exit - Exit from program.
                """);
    }
}