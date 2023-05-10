'use strict';

const usernamePage = document.querySelector('#username-page');
const chatPage = document.querySelector('#chat-page');
const usernameForm = document.querySelector('#usernameForm');
const messageForm = document.querySelector('#messageForm');
const messageInput = document.querySelector('#message');
const messageArea = document.querySelector('#messageArea');
const connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;

const colors = [
    '#2196F3',
    '#32c787',
    '#00BCD4',
    '#ff5652',
    '#ffc107',
    '#ff85af',
    '#FF9800',
    '#39bbb0',
];

function connect(event) {
    username = document.querySelector('#name').value.trim();

    if(username) {
        // 연결이 됐으면 기본창 대신에 채팅창을 띄운다.
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        /*
            WebSocket Connection을 위한 EndPoint를 입력해준다.
         */
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        // STOMP 프로토콜에 함수를 적용시킨다.
        stompClient.connect({}, onConnected, onError);
        //~ (void) connect(headers, connectCallback, errorCallback)
        // onConnected - connect 콜백 함수
        // onError - error 콜백 함수
    }
    event.preventDefault();
}

// 성공적으로 연결되었다면 connectCallBack함수로 작성한 onConnected() 함수에서 동작합니다.
function onConnected() {
    // Subscribe to the Public Topic
    // 토픽을 구독 시킵니다.
    stompClient.subscribe('/topic/public', onMessageReceived);
    //(Object) subscribe(destination, callback, headers = {})
    //명명된 목적지"/topic/public"을 구독합니다.

    //(void) send(destination, headers = {}, body = '')
    //명명된 목적지 "/app/chat.adduser"로 메세지를 보냅니다.
    //
    stompClient.send(
        "/app/chat.addUser",
        {},
        JSON.stringify(
            {
                sender: username,
                type: 'JOIN',
            }
        )
    )

    // 기본적으로 쓰여있는 컴포넌트를 감춘다.
    connectingElement.classList.add('hidden');
}
// WebSocket 중 에러 발생시.
function onError(error) {
    connectingElement.textContent = '서버와 연결이 종료되었습니다.';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    const messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {

        const chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };

        stompClient.send(
            "/app/chat.sendMessage",
            {},
            JSON.stringify(chatMessage)
        );

        messageInput.value = '';
    }
    event.preventDefault();
}

// 사용자 접속관련 메세지 + 채팅 관련 메세지 태그작성
function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    const textElement = document.createElement('p');
    const messageText = document.createTextNode(message.content);

    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}

usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)