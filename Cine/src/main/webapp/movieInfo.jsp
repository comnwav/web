<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>test</title>
</head>

<link rel="stylesheet" href="movieInfo.css">

<body>
	<div class="f1">
		<div class="poster"></div>
		<div class="reserve"></div>
	</div>

	<div class="f2">
		<h1 class="title"></h1>
		<h3 class="info"></h3>
	</div>

	<div class="f3">
		<p></p>
	</div>

	<div class="f4">
		<div class="youtube"></div>
	</div>

	<div class="f5">
		<table id="list_table">
			<thead>
				<th width="100"></th>
				<th width="300"></th>
				<th width="10"></th>
				<th width="100"></th>
				<th width="10"></th>
			</thead>
		</table>

		<a onclick="prevPage()" href='#' id="btn_prev">Prev</a> <a
			onclick="nextPage()" href='#' id="btn_next">Next</a> <span id="page"></span>
	</div>

	<div class="f6">
		<input type="text" name="" id="area" placeholder="이 영화 어떠셨나요?">
		<div class="star-rating">
			<input type="radio" id="5-stars" name="rating" value="5"> <label
				for="5-stars" class="star">&#9733;</label> <input type="radio"
				id="4-stars" name="rating" value="4"> <label for="4-stars"
				class="star">&#9733;</label> <input type="radio" id="3-stars"
				name="rating" value="3"> <label for="3-stars" class="star">&#9733;</label>
			<input type="radio" id="2-stars" name="rating" value="2"> <label
				for="2-stars" class="star">&#9733;</label> <input type="radio"
				id="1-star" name="rating" value="1"> <label for="1-star"
				class="star">&#9733;</label>
		</div>
		<input class="bt" type="button" value="입력">
	</div>


</body>

<script type="text/javascript">
    let key = "6446bae76aaffa9af6d4c00b2299f016";

    let srcPara = new URL(location.href).searchParams;
    let name = srcPara.get('title');
    let movieId = srcPara.get('movieId');

    let f1 = document.querySelector('.f1');
    let f2 = document.querySelector('.f2');
    let f3 = document.querySelector('.f3');
    let f4 = document.querySelector('.f4');
    let f5 = document.querySelector('.f5');
    let f6 = document.querySelector('.f6');

    function infoPage() {
        fetchMovie();
        showComment();
        fetchComment();
    }

    function fetchMovie() {
        let url = `https://api.themoviedb.org/3/search/movie?api_key=${key}&query=${name}&language=ko-KR`;
        let base_url = "https://image.tmdb.org/t/p/w500";

        fetch(url)
            .then(res => res.json())
            .then(res => {
                // query 결과확인
                let movie = res.results[0];

                // result 에서 가져올 항목
                let poster = base_url + movie.poster_path;
                let overview = movie.overview;
                let title = movie.original_title;
                let relDate = movie.release_date;

                // f1-1 poster
                let imgTag = document.createElement('img');
                imgTag.setAttribute('width', '300');
                imgTag.src = poster;

                console.log(imgTag);

                f1.firstElementChild.appendChild(imgTag);

                // f1-2 reserve button
                let rsTag = document.createElement('a');
                rsTag.innerHTML = '예매하기';
                rsTag.href = `${title}어디론가갑니다`
                f1.lastElementChild.appendChild(rsTag);

                // f2 info
                let titleTag = document.querySelector('.title');
                titleTag.innerHTML = title;
                f2.appendChild(titleTag);

                let infoTag = document.querySelector('.info');

                // f3 overview
                let ovTag = document.querySelector('.f3');
                ovTag.firstElementChild.innerHTML = overview;
            })
            .catch(error => console.log(error));
    };

    function fetchVideo() {
        let url = `https://api.themoviedb.org/3/movie/${movieId}/videos?api_key=${key}&language=en-EN`;
        let youtube = document.querySelector('.youtube');

        fetch(url)
            .then(res => res.json())
            .then(res => {
                let ary = res.results;
                console.log(ary);

                ary.forEach(element => {
                    let vid = document.createElement('iframe');
                    let key = element.key;
                    vid.src = `https://www.youtube.com/embed/${key}?autoplay=0`;
                    youtube.appendChild(vid);
                });
            })
    }

    function showComment() {

        let url = `commentList.do`;
        let table = document.querySelector('#list_table')

        fetch(url, {
                method: 'post',
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: `movieId=${movieId}`
            })
            .then(res => res.json())
            .then(res => {
                console.log(res);

                res.forEach(res => {

                    console.log(res);

                    let tr = document.createElement('tr');

                    for (let element in res) {
                        let td = document.createElement('td');
                        td.innerHTML = res[element];
                        td.setAttribute("align", "center");
                        tr.appendChild(td);
                    }
                    let td = document.createElement('td');
                    td.innerHTML = '&#10060;';
                    tr.appendChild(td);
                    table.appendChild(tr);

                })
            })
    }

    function fetchComment() {
        let url = `commentAdd.do`;

        let bt = document.querySelector('.bt');
        bt.addEventListener('click', e => {

            let tr = document.querySelectorAll('tr');
            tr.forEach(element => {
                element.remove();
            })

            let comment = document.getElementById('area').value;
            let stars = document.querySelectorAll('.star-rating>input');
            let val;

            stars.forEach(element => {
                if (element.checked == true) {
                    val = element.value;
                }
            })

            fetch(url, {
                    method: 'post',
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: `comment=${comment}&stars=${val}&movieId=${movieId}`
                })
                .then(res => {
                    document.getElementById('area').value = "";
                    stars.forEach(element => {
                        element.checked = false;
                    })
                    showComment();
                })
        })

        function delComment() {
            let url = `commentDel.do`;
            
        }

    }
    window.addEventListener('onLoad', infoPage());
    </script>
</html>