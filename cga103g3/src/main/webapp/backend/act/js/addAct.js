window.addEventListener("load", function(){
	
    let phfile = document.getElementById("phfile");
    phfile.addEventListener("change", function(e){

      let ul_el = document.getElementsByClassName("picture_list")[0];
      ul_el.innerHTML = ""; // 清空

      for (let i = 0; i < this.files.length; i++){
        let readPh = new FileReader(); // 用來讀取檔案的物件
        readPh.readAsDataURL(this.files[i]); // 讀取檔案
        // 檔案讀取完畢時觸發
        readPh.addEventListener("load", function () {
          console.log(this);
          let url = readPh.result;
          console.log(url);
          let li_str = `
            <li>
              <img src="${url}" class="preview">
            </li>
          `;
          // 可以透過 readPh.result 取得圖片讀取完成時的 Base64 編碼格式

          ul_el.insertAdjacentHTML("beforeend", li_str);

        });
      }
    });

  });