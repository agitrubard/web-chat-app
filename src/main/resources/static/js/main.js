'use strict';

var loginPage = document.querySelector('#login-page');
var loginForm = document.querySelector('#login-form');

var chatPage = document.querySelector('#chat-page');
var chatArea = document.querySelector('#chat-area');
var sendMessageForm = document.querySelector('#send-message-form');
var messageInput = document.querySelector('#message-input');

var infoMessage = document.querySelector('.info-message');

var stompClient = null;
var username = null;

var colors = [
    '#2196F3', '#32C787', '#00BCD4', '#FF5652',
    '#FFF107', '#FF85AF', '#FF9800', '#39BBB0',
    '#00AA55', '#009FD4', '#B381B3', '#93BC93',
    '#E3BC00', '#D47500', '#DC2A2A', '#990000'
];

function connect(event) {
    username = document.querySelector('#name').value.trim();

    if (username) {
        goChatPage();

        connectStompClient();
    }
    event.preventDefault();
}


function goChatPage() {
    loginPage.classList.add('hidden');
    chatPage.classList.remove('hidden');
}

function connectStompClient() {
    var socket = new SockJS('/ws');

    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);
}

function onConnected() {
    stompClient.subscribe('/web-app/chat', onMessageReceived);

    stompClient.send("/chat/login", {}, getLoginRequest())

    infoMessage.classList.add('hidden');
}

function getLoginRequest() {
    var loginRequest = {
        username: username,
        userStatus: 'JOIN'
    };
    return JSON.stringify(loginRequest);
}

function onError(error) {
    infoMessage.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    infoMessage.style.color = 'red';
}

function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if (messageContent && stompClient) {
        var messageRequest = {
            sender: username,
            content: messageInput.value,
            userStatus: 'CHAT'
        };
        stompClient.send("/chat/sendMessage", {}, JSON.stringify(messageRequest));
        messageInput.value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload) {
    var request = JSON.parse(payload.body);

    var messageElement = document.createElement('li');
    var textElement = document.createElement('p');

    var messageText = null;

    switch (request.userStatus) {
        case 'JOIN':
            messageText = userJoined(request, messageElement);
            break;
        case 'CHAT':
            messageText = onChat(request, messageElement);
            break;
        case 'LEAVE':
            messageText = userLeft(request, messageElement);
            break;
    }

    textElement.appendChild(messageText);
    messageElement.appendChild(textElement);

    chatArea.appendChild(messageElement);
    chatArea.scrollTop = chatArea.scrollHeight;
}

function userJoined(request, messageElement, textElement) {
    messageElement.classList.add('event-message');
    return document.createTextNode(getJoinedInfoMessage(request.username));
}

function getJoinedInfoMessage(username) {
    return username + ' Joined!';
}

function userLeft(request, messageElement, textElement) {
    messageElement.classList.add('event-message');
    return document.createTextNode(getLeftInfoMessage(request.username));
}

function getLeftInfoMessage(username) {
    return username + ' Left!';
}

function onChat(request, messageElement) {
    messageElement.classList.add('chat-message');

    setElements(request, messageElement);

    return document.createTextNode(request.content);
}

function setElements(request, messageElement) {
    var sender = request.sender;

    var usernameElement = document.createElement('span');
    var timeElement = document.createElement('time');
    var avatarElement = document.createElement('i');

    var avatarText = document.createTextNode(sender[0]);
    var usernameText = document.createTextNode(sender);
    var timeText = document.createTextNode(getTime());

    avatarElement.appendChild(avatarText);
    setAvatarColor(sender, avatarElement);
    usernameElement.appendChild(usernameText);
    timeElement.appendChild(timeText);

    messageElement.appendChild(avatarElement);
    messageElement.appendChild(usernameElement);
    messageElement.appendChild(timeElement);
}

function setAvatarColor(sender, avatarElement) {
    var hash = 0;
    for (var i = 0; i < sender.length; i++) {
        hash = 31 * hash + sender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);

    avatarElement.style['background-color'] = colors[index];
}

function getTime() {
    var date = new Date();
    var hour = addZero(date.getHours());
    var minute = addZero(date.getMinutes());
    var seconds = addZero(date.getSeconds());
    return hour + ":" + minute + ":" + seconds;
}

function addZero(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

var usernameInput = document.getElementById('name');
var startChatButton = document.getElementById('start-chat-button');
var loginHeaderText = document.getElementById('login-header-text');

usernameInput.addEventListener('keyup', function(){
    if (usernameInput.value === null || usernameInput.value === "") {
        startChatButton.style.backgroundColor = '#5CB9F1';
        loginHeaderText.style.color = '#5CB9F1';
    }
    else {
        startChatButton.style.backgroundColor = "#A4E9C0";
        loginHeaderText.style.color = '#A4E9C0';
    }
});

var messageSubmitButton = document.getElementById('message-submit-button');

messageInput.addEventListener('keyup', function(){
    if (messageInput.value === null || messageInput.value === "") {
        messageSubmitButton.style.backgroundColor = '#5CB9F1';
    }
    else {
        messageSubmitButton.style.backgroundColor = "#A4E9C0";
    }
});

loginForm.addEventListener('submit', connect, true)
sendMessageForm.addEventListener('submit', sendMessage, true)