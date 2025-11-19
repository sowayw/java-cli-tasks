# Java CLI Tasks 🗂️

🎯 **Overview**  
A simple, lightweight Task Tracker implemented in Java with a Command Line Interface (CLI).  
You can add, update, view, and delete tasks. Each task keeps track of its **status** and **timestamps** for creation and last update.

---

✨ **Features**  

- Add tasks  
- Delete tasks  
- Mark tasks as **Todo**, **In Progress**, or **Done**  
- View all tasks with formatted output: `[Task: ID | Description: ... | Status: ... | Created: ... | Updated: ...]`  
- Minimal dependencies: pure Java, no external libraries  

---

## 🚀 **How to Compile & Run**

**1. Clone the repository:** 
```html
git clone https://github.com/sowayw/java-cli-tasks.git
+cd java-cli-tasks
```

**2. Compile the source code:**
```html
javac -d out src/Main.java src/model/*.java src/exception/*.java
```

**3. Run the application:**
```html
java -cp out Main
```

## Usage

```html
Enter command: add
Enter text of task: Lol
Task has successfully added.
```

- help - Show all commands.
- add - Add task.
- del - Delete task.
- mark-todo - Mark task as todo.
- mark-done - Mark task as done.
- mark-in-progress - Mark task as in progress.
- list - Show all tasks.
- exit - Exit from program.
