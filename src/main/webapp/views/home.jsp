<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo List App with Chatbot</title>
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
            margin: 20px auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1, h3 {
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
        #chatbox {
            position: fixed;
            bottom: 20px;
            right: 20px;
            width: 300px;
            background: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            border-radius: 10px;
            overflow: hidden;
            display: none;
            flex-direction: column;
        }
        #chatboxHeader {
            background-color: #007bff;
            color: white;
            padding: 10px;
            cursor: pointer;
        }
        #messages {
            height: 200px;
            overflow-y: auto;
            border: 1px solid #ddd;
            margin: 0;
            padding: 10px;
        }
        #inputArea {
            display: flex;
            padding: 10px;
            border-top: 1px solid #ddd;
        }
        #userInput {
            flex: 1;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        #sendButton {
            margin-left: 5px;
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
        }
        #sendButton:hover {
            background-color: #0056b3;
        }
        #chatToggle {
            position: fixed;
            bottom: 20px;
            right: 20px;
            background: #007bff;
            color: white;
            border: none;
            border-radius: 50%;
            width: 60px;
            height: 60px;
            font-size: 24px;
            cursor: pointer;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        #chatToggle:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>

    <!-- Task Management Section -->
    <div class="container">
        <h1>Todo List App</h1>
        <form action="/search" method="get">
            <input type="text" name="title" placeholder="Search by title" required>
            <button type="submit">Search</button>
        </form>
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

    <!-- Random Quote Section -->
    <div class="container">
        <h1>Random Quote</h1>
        <p id="quoteDisplay">${quote}</p>
        <button onclick="fetchQuote()">Generate Quote</button>
    </div>

    <!-- Chatbot Section -->
    <div id="chatbox">
        <div id="chatboxHeader">
            Chatbot <button onclick="toggleChat()" style="float: right; background: none; border: none; color: white; font-size: 16px;">&times;</button>
        </div>
        <div id="messages"></div>
        <div id="inputArea">
            <input type="text" id="userInput" placeholder="Type your message here..." />
            <button id="sendButton">Send</button>
        </div>
    </div>
    <button id="chatToggle" onclick="toggleChat()">💬</button>

    <!-- Scripts -->
    <script>
        function fetchQuote() {
            fetch('/random-quote')
                .then(response => response.text())
                .then(quote => {
                    document.getElementById('quoteDisplay').innerText = quote;
                })
                .catch(error => {
                    console.error('Error fetching quote:', error);
                    document.getElementById('quoteDisplay').innerText = 'Failed to fetch a quote. Please try again later.';
                });
        }

        function toggleChat() {
            const chatbox = document.getElementById("chatbox");
            const chatToggle = document.getElementById("chatToggle");
            if (chatbox.style.display === "none" || chatbox.style.display === "") {
                chatbox.style.display = "flex";
                chatToggle.style.display = "none";
            } else {
                chatbox.style.display = "none";
                chatToggle.style.display = "flex";
            }
        }

        const sendButton = document.getElementById("sendButton");
        const userInput = document.getElementById("userInput");
        const messages = document.getElementById("messages");

        sendButton.addEventListener("click", () => {
            const input = userInput.value;
            if (input.trim() === "") return;

            const userMessage = document.createElement("p");
            userMessage.textContent = "You: " + input;
            messages.appendChild(userMessage);

            fetch('/ask', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(input)
            })
                .then(response => response.text())
                .then(data => {
                    const botMessage = document.createElement("p");
                    botMessage.textContent = "Bot: " + data;
                    messages.appendChild(botMessage);
                })
                .catch(error => {
                    const errorMessage = document.createElement("p");
                    errorMessage.textContent = "Error: Could not fetch response.";
                    messages.appendChild(errorMessage);
                });

            userInput.value = "";
        });
    </script>

</body>
</html>
