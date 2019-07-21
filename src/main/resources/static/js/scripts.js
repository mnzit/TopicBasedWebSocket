var socket = new SockJS('/cp');
let stompClient = Stomp.over(socket);


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    var messageComponent = document.createElement('div');
    messageComponent.className = "alert alert-info mt-3 mb-3"
    messageComponent.innerHTML = "<div class='row'><div class='col-md-3 text-center'><b>" + message.name + "</b></div><div class='col-md-8'>" + message.content + "</div><div class='col-md-1 badge badge-info text-right'>" + message.time + "</div></div>"
    var messagesContainer = $('#messages');
    messagesContainer.append(messageComponent);
}

function onError() {
    alert("We got a problem !!");
}

function sendPublicMessage() {
    let response = {};
    response['name'] = $('#name').val();
    response['content'] = $('#content').val();
    stompClient.send("/app/message", {}, JSON.stringify(response));
}

$(document).ready(function () {
    $('#send').on('click', function () {
        sendPublicMessage();
    });
    $('input:radio[name="type"]').change(function () {
        var view = "";
        var radioValue = $('input:radio[name="type"]:checked').val();
        if (radioValue === "private") {
           view = "block";
        } else {
           view = "none";
        }
        $('#groupBlock').css('display',view);
    });
});

