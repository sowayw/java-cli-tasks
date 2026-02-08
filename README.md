# Java CLI Tasks 

## **Overview**  
A simple, lightweight Task Tracker implemented in Java with a Command Line Interface (CLI).  
You can add, update, view, and delete tasks. Each task keeps track of its **status** and **timestamps** for creation and last update.

---

✨ **Features**  

- Add tasks  
- Delete tasks  
- Mark tasks as **In Progress**, or **Done**  
- View all tasks with formatted output: `"id: .., description: .., status: .., createdAt: .., updatedAt: .. "`  
- Minimal dependencies: pure Java, no external libraries  

---

## **How to Compile & Run**

**1. Clone the repository:** 
``` bash
git clone https://github.com/sowayw/java-cli-tasks.git
cd "java-cli-tasks/src"
```

**2. Compile the source code:**
```html
javac TaskCLI.java
```

**3. Run the application:**
```html
java TaskCLI [command]
```

## Usage

```html
java TaskCLI add "go to gym at 12:00"
```

- add - Add task.
- del - Delete task.
- mark-done - Mark task as done.
- mark-in-progress - Mark task as in progress.
- list - Show all tasks.
