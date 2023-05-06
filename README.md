Android-AI-bot
This is an Android app for a chatbot using the ChatGPT API.

SplashScreenActivity is the first screen of the app which displays a splash screen for 3 seconds and then launches the MainActivity. MainActivity is the login screen of the app where the user can click on a button to proceed to the ChatgptActivity. ChatgptActivity is the main screen of the app where the user can input a question and get a response from the chatbot. It also has a button to copy the response to the clipboard. The ChatGPT API is used in an AsyncTask to handle the request in the background and display the response on the UI thread. The API key is hard-coded in the file.

Note: To use this app, you need to replace the API key in the code with your own API key. You can create a new API key by going to the ChatGPT website (https://chatgpt.com) and signing up for an account. Once you have created an account, navigate to the API section and generate a new API key. Replace the API key in the ChatgptActivity file with your own API key to use the app.
