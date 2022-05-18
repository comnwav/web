 let current_page = 1;
 let tr_per_page = 5;

 let tr = document.getElementById('cmtTable');

 console.log(tr);
 console.log(tr.childNodes[0]);

 function toNumPages() {
     return Math.ceil(tr.rows.length / tr_per_page);
 }

 function prevPage() {
     console.log("prev");
     if (current_page > 1) {
         current_page--;
         change(current_page);
     }
 }

 function nextPage() {
     console.log("next");
     if (current_page < toNumPages()) {
         current_page++;
         change(current_page);
     }
 }

 function change(page) {
     let listing_table = document.getElementById("tableCmt");

     console.log(listing_table);

     let btn_prev = document.getElementById("btn_prev");
     btn_prev.addEventListener('click', prevPage);
     let btn_next = document.getElementById("btn_next");
     btn_next.addEventListener('click', nextPage);

     let page_span = document.getElementById("page");

     if (page < 1) page = 1;
     if (page > toNumPages()) page = toNumPages();

     for (let i = (page - 1) * tr_per_page; i < (page * tr_per_page); i++) {
         listing_table.appendChild(tr.childNodes[i]);
     }

     page_span.innerHTML = page;

     if (page == 1) {
         btn_prev.style.visibility = "hidden";
     } else {
         btn_prev.style.visibility = "visible";
     }
     if (page == toNumPages()) {
         btn_next.style.visibility = "hidden";
     } else {
         btn_next.style.visibility = "visible";
     }
 }

 window.addEventListener('onLoad', change(1));