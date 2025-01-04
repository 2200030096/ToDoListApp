<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo List App</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            text-align: center;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
        }
        button {
            padding: 10px 20px;
            margin: 10px;
            border: none;
            background-color: #007bff;
            color: #fff;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
   
    <form action="/search" method="get">
        <input type="text" name="title" placeholder="Search by title" required>
        <button type="submit">Search</button>
    </form>
</div>

    <div class="container">
        <h1>Todo List App</h1>
        <form action="/add" method="get">
            <button type="submit">Add Task</button>
        </form>
        <form action="/tasks/update" method="get">
            <button type="submit">Update Task</button>
        </form>
        <form action="/deleteTaskPage" method="get">
            <button type="submit">Delete Task</button>
        </form>
        <form action="/viewall" method="get">
            <button type="submit">View Tasks</button>
        </form>
    </div>
    
        <h1>Random Quote</h1>
    <p id="quoteDisplay">${quote}</p> <!-- Display quote from the model -->
    
    <!-- Button to fetch a new quote -->
    <button onclick="fetchQuote()">Generate Quote</button>
    
     <script>
        function fetchQuote() {
            fetch('/random-quote')  // Send request to backend to get a new quote
                .then(response => response.text()) // Get the response as plain text
                .then(quote => {
                    document.getElementById('quoteDisplay').innerText = quote; // Update the quote on the page
                })
                .catch(error => {
                    console.error('Error fetching quote:', error);
                    document.getElementById('quoteDisplay').innerText = 'Failed to fetch a quote. Please try again later.';
                });
        }
    </script>
        
      
    
</body>
</html>
