const httpRequest = new XMLHttpRequest();;

function callRestApi(e, type, url, data) {
    const jsonParsedData = data ? JSON.stringify(data) : null;

    httpRequest.onreadystatechange = e;
    httpRequest.open(type, url);
    httpRequest.setRequestHeader("Content-Type", "application/json")
    httpRequest.send(jsonParsedData);
}

const displayButton = (button) => {
    // block을 할 경우 상단의 text-align: center; 가 깨진다.
    button.style.display = ""
}

const disappearButton = (button) => {
    button.style.display = "none";
}

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

const toBack = () => {
    // window.history.replaceState({page: 1}, "", "http://localhost:9090");
    window.history.back()
}

const toMain = () => {
    window.location.href = "/";
}

const isLogin = (session) => {
    if(!session) {
        alert("로그인이 필요한 서비스입니다.")
        window.location.href = "/login"
    }
}