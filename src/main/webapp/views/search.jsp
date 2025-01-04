<%@ page import="com.klu.ToDoList.Task" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Results</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 100%;
            text-align: center;
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
        }
        ul {
            list-style: none;
            padding: 0;
        }
        li {
            background: #f9f9f9;
            margin: 10px 0;
            padding: 10px;
            border-radius: 4px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
            text-align: left;
        }
        li:nth-child(even) {
            background: #f1f1f1;
        }
        a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background: #007bff;
            color: #fff;
            border-radius: 4px;
        }
        a:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Search Results</h1>
        <ul>
            <% 
                List<Task> tasks = (List<Task>) request.getAttribute("tasks"); // Retrieve tasks from the request
                if (tasks != null && !tasks.isEmpty()) { 
                    for (Task task : tasks) { 
            %>
                        <li><strong>Title:</strong> <%= task.getTitle() %><br>
                            <strong>Description:</strong> <%= task.getDescription() %><br>
                            <strong>Due Date:</strong> <%= task.getDueDate() %></li>
            <% 
                    }
                } else { 
            %>
                <li>No tasks found.</li>
            <% 
                } 
            %>
        </ul>
        <a href="/">Back to Home</a>
    </div>
</body>
</html>
