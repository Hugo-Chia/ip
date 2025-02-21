# ArtemisPro User Guide

## Introduction
ArtemisPro is a task management bot that helps you remember tasks so we can get back to the moon again.
It's text-based, lightweight and very easy to learn and use.

It allows you to manage tasks by listing, adding, marking, unmarking, deleting, finding and sorting tasks!
It has persistent-storage so your data is preserved even after closing the app!

## Sample GUI Screenshots
![Ui.png](Ui.png)

## Adding Todo

You can add a new Todo task by typing a command with a specific format. The Todo task will be added to your task list.

**Command Syntax:**

`todo <todo name>`

**Example:**

`todo Finish Week 5 Task`

**Expected Outcome:**
The Todo task, "Finish Week 5 Task" will be added to your list of task. The expected output is as shown below:
```
Got it. I've added this task:
[T][ ] Finish Week 5 Task
Now you have 1 tasks in the list.
```

## Adding Deadline

You can add a new Deadline task by typing a command with a specific format. The Deadline task will be added to your task list.

**Command Syntax:**

`deadline <deadline name> /by <YYYY-MM-DD> <HH:MM>`

**Example:**

`deadline Push updated code to Artemis /by 2025-02-02 17:00`

**Expected Outcome:**
The Deadline task, "Push updated code to Artemis" will be added to your list of task. The expected output is as shown below:
```
Got it. I've added this task:
[D][ ] Push updated code to Artemis (by: 02 Feb 2025 05:00 pm)
Now you have 1 tasks in the list.
```

## Adding Events

You can add a new Event task by typing a command with a specific format. The Event task will be added to your task list.

**Command Syntax:**

`event <event name> /from <YYYY-MM-DD> <HH:MM> /to <HH:MM>`

**Example:**

`event Meeting to discuss future of Artemis Programme /from 2025-02-03 16:00 /to 18:00`

**Expected Outcome:**
The Event task, "Meeting to discuss future of Artemis Programme" will be added to your list of task. The expected output is as shown below:
```
Got it. I've added this task:
[E][ ] Meeting to discuss future of Artemis Programme (from: 03 Feb 2025 04:00 pm to: 06:00 pm)
Now you have 1 tasks in the list.
```

## List All Tasks

You can list all tasks by simply using the `list` command.

**Command Syntax:**

`list`

**Example:**

`list`

**Expected Outcome:**
All tasks will be listed. The expected output is as shown below:
```
Here are the tasks in your list:
1.[T][X] Finish Week 5 Task
2.[D][ ] Push updated code to Artemis (by: 02 Feb 2025 05:00 pm)
3.[E][ ] Meeting to discuss future of Artemis Programme (from: 02 Feb 2025 04:00 pm to: 06:00 pm)
```

## Mark task

You can mark a task as done by simply using the `mark` command.

**Command Syntax:**

`mark <task number>`

**Example:**

`mark 1`

**Expected Outcome:**
The selected task will be mark as done. The expected output is as shown below:
```
Nice! I've marked this task as done:
[T][X] Finish Week 5 Task
```

## Unmark task

You can unmark a task as not done by simply using the `unmark` command.

**Command Syntax:**

`unmark <task number>`

**Example:**

`unmark 1`

**Expected Outcome:**
The selected task will be mark as not done. The expected output is as shown below:
```
OK, I've marked this task as not done yet:
[T][] Finish Week 5 Task
```

## Deleting task

You can delete a task by simply using the `delete` command.

**Command Syntax:**

`delete <task number>`

**Example:**

`delete 1`

**Expected Outcome:**
The selected task will be deleted. The expected output is as shown below:
```
Noted, I've removed this task:
[T][] Finish Week 5 Task
Now you have 3 tasks in the list.
```

## Finding task

You can find a task by simply using the `find` command.

**Command Syntax:**

`find <keyword>`

**Example:**

`find artemis`

**Expected Outcome:**
The selected task will be deleted. The expected output is as shown below:
```
Here are the matching tasks in your list:
1.[D][X] Push updated code to Artemis (by: 02 Feb 2025 05:00 pm)
2.[E][ ] Meeting to discuss future of Artemis Programme (from: 03 Feb 2025 04:00 pm to: 06:00 pm)
```

## Sorting task

You can sort a task by simply using the `sort` command.

**Command Syntax:**

`sort <name/date>`

**Example:**

`sort date`

**Expected Outcome:**
The tasks will be sorted by name or date, depending on which is selected. The expected output is as shown below:
```
Here are the sorted tasks in your list:
1.[D][X] Push updated code to Artemis (by: 02 Feb 2025 05:00 pm)
2.[E][ ] Meeting to discuss future of Artemis Programme (from: 03 Feb 2025 04:00 pm to: 06:00 pm)
3.[T][X] Finish Week 5 Task
4.[T][ ] Finish Week 6 Task
```

## Other commands
* **Exit command**: Use `bye` to end the conversation.