var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            location.reload();
        }).fail(function (error) {
            alert(error);
            alert("에러 발생")
        });
    }

};

main.init();


///**
//* JS 첫 문장에 var main = {...}를 선언했습니다. 
//* 굳이 main이라는 변수의 속성으로 function을 추가한 이유는 뭘까요?
//* 브라우저의 scope는 공용으로 쓰이기 때문에 나중에 불려진 js의 init, save가 먼저 불려진 js의 function을 덮어쓰게 됩니다.
//* 이런 문제를 피하려고 main.js만의 변수,function 영역으로 var main이란 객체 안에서 function을 선언합니다. 
//* 이렇게 되면 main 객체 안에서만 이름이 유효하기 때문에 다른 JS와 겹칠 위험이 사라집니다.
//* Tip)
//* 프론트엔드의 이런 의존성관리, scope관리 등등의 문제들 때문에 최근에는 JS 개발환경이 급변했습니다.
//* Angular, React등은 이미 이런 기능을 프레임워크 레벨에서 지원하고 있습니다.
//* IE 대응으로 Angular, React를 사용 못하는 상황이라면, 이전에 포스팅한 IE 7/8에서 모던하게 JS 개발하기 시리즈를 참고하시면 도움 되실것 같습니다.  
//*/
