$(document).ready(function () {

});

function deleteOne(id) {
    $.ajax({
        url: `/api/board/${id}`,
        type: "DELETE",
        success: function (response) {
            console.log(response);
            window.location.href = "/";
        }
    })
}

function writeReply(id) {
    let text = $("#reply").val();
    console.log(text);
    let data = {'text': text};
    $.ajax({
        url: `/api/board/${id}/comment`,
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (response) {
            console.log(response);
            alert("댓글이 등록되었습니다.");
            window.location.reload();
        },
        error: function (err) {
            console.log(err);
        }
    })
}

function getReply(id) {
    $.ajax({
        url: `/api/board/${id}/comment`,
        type: "GET",
        success: function (response) {
            console.log(response);
            /*
            for (let i = 0; i < response.length; i++) {
                let board = response[i];
                let id = board.id;
                let title = board.title;
                let content = board.content;
                let username = board.user.username;
                let modifiedAt = board.modifiedAt;
                addHTML(id, title, username, content, modifiedAt);
            }

             */
        }
    })
}