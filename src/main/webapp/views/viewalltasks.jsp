<%@page import="com.klu.ToDoList.Task"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task List</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        header {
            background-color: #5f6368;
            color: white;
            padding: 15px;
            text-align: center;
        }
        .container {
            padding: 30px;
            max-width: 1200px;
            margin: 0 auto;
            background-color: #fff;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        .table-wrapper {
            margin-top: 20px;
            overflow-x: auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .button {
            display: inline-block;
            padding: 8px 20px;
            background-color: #007BFF;
            color: white;
            border-radius: 4px;
            text-decoration: none;
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<header>
    <h1>Task List</h1>
</header>

<div class="container">
    <h2>All Tasks</h2>
    <div class="table-wrapper">
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Due Date</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Task> tasks = (List<Task>) request.getAttribute("tasks"); // Retrieve tasks from request scope
                    if (tasks != null && !tasks.isEmpty()) {
                        for (Task task : tasks) { 
                %>
                    <tr>
                        <td><%= task.getId() %></td>
                        <td><%= task.getTitle() %></td>
                        <td><%= task.getDescription() %></td>
                        <td><%= task.getDueDate() %></td>
                    </tr>
                <% 
                        } 
                    } else { 
                %>
                    <tr>
                        <td colspan="4">No tasks found.</td>
                    </tr>
                <% 
                    } 
                %>
            </tbody>
        </table>
    </div>
    <a href="/" class="button">Back to Home</a>
</div>

</body>
</html>
