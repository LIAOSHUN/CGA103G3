window.addEventListener("load", function () {
    let bookingTitle = document.getElementById('bookingTitle');
    let text_muted = document.getElementById('text-muted');
    let memID = document.getElementById('memID');
    let store = document.getElementById('store');
    let bookingDate = document.getElementById('bookingDate');
    let boxSize = document.getElementById("boxSize");
    let bookingStart = document.getElementById('bookingStart');
    let bookingEnd = document.getElementById('bookingEnd');

    const java = '<b>Java</b>是一種廣泛使用的電腦程式設計語言，擁有跨平台、物件導向、泛型程式設計的特性，廣泛應用於企業級Web應用開發和行動應用開發。<br>'
        + '<b>Java</b>不同於一般的編譯語言或直譯語言。它首先將原始碼編譯成位元組碼，再依賴各種不同平台上的虛擬機器來解釋執行位元組碼，從而具有「一次編寫，到處執行」的跨平台特性。在早期JVM中，這在一定程度上降低了Java程式的執行效率。但在J2SE1.4.2釋出後，Java的執行速度有了大幅提升。';
    const servlet = '<b>Servlet（Server Applet）</b>，全稱<b>Java Servlet</b>。是用Java編寫的伺服器端程式。其主要功能在於互動式地瀏覽和修改資料，生成動態Web內容。狹義的Servlet是指Java語言實現的一個介面，廣義的Servlet是指任何實現了這個Servlet介面的類別，一般情況下，人們將Servlet理解為後者。'
        + '<br><b>Servlet</b>執行於支援Java的應用伺服器中。從實現上講，Servlet可以回應任何類別型的請求，但絕大多數情況下Servlet只用來擴充基於HTTP協定的Web伺服器。'
        + '最早支援Servlet標準的是JavaSoft的Java Web Server。此後，一些其它的基於Java的Web伺服器開始支援標準的Servlet。';
    const mySQL = '<b>MySQL</b>（官方發音為/maɪ ˌɛskjuːˈɛl/「My S-Q-L」[5]，但也經常被讀作/maɪ ˈsiːkwəl/「My Sequel」）原本是一個開放原始碼的關聯式資料庫管理系統，原開發者為瑞典的MySQL AB公司，該公司於2008年被昇陽微系統（Sun Microsystems）收購。2009年，甲骨文公司（Oracle）收購昇陽微系統公司，MySQL成為Oracle旗下產品。'
        + '<br>MySQL在過去由於效能高、成本低、可靠性好，已經成為最流行的開源資料庫，因此被廣泛地應用在Internet上的中小型網站中。隨著MySQL的不斷成熟，它也逐漸用於更多大規模網站和應用，比如維基百科、Google和Facebook等網站。非常流行的開源軟體組合LAMP中的「M」指的就是MySQL。'
    const peter = '<b style="color: red">全球銷售超過百萬冊！抗癌醫師吳永志自救救人飲食生活大公開～<br>'
        + '<br>【生活養生不一樣】'
        + '<br> ● 每天保持3次排便；每天曬太陽及運動30分鐘。'
        + '<br> ● 每天洗冷熱浴提升免疫力，改善血液循環。'
        + '<br> ● 喝對水，小口喝，讓細胞有充足時間吸收。'
        + '<br> ● 每天喝含植物生化素的蔬果汁清血毒、補充營養。'
        + '<br><p class="card-text"><small class="text-muted">資訊來源: 金融廣場</small></p>';


    // store設定 -----------------------------------------------------------------------------------		
    fetch("http://localhost:8081/CGA103G3_07/store/store.do")
        .then(res => res.json("storeList"))
        .then(storeList => {
            // console.log(storeList);
            let add = '';
            let storeClose = new Array(storeList.length);
            for (let i = 0; i <= storeList.length; i++) {
                add = '';
                add += '<option selected="selected" disabled>選擇門市</option>';
                for (let j = 0; j < i; j++) {
                    storeClose[j] = storeList[j].storeOff;
                    add += '<option value="' + storeList[j].storeID + '">' + storeList[j].storeName + '</option>';
                };
                store.innerHTML = add;
            };
            // 監聽store顯示包廂說明-------------------------------------------------------------------------------------------
            document.getElementById('store').addEventListener('change', () => {
                document.getElementById('bookingDate').value = '';
                boxSize.innerHTML = '';
                bookingStart.innerHTML = '';
                bookingEnd.innerHTML = '';

                if (store.value == 51001) {
                    bookingTitle.innerHTML = java;
                } else if (store.value == 51002) {
                    bookingTitle.innerHTML = servlet;
                } else if (store.value == 51003) {
                    bookingTitle.innerHTML = mySQL;
                } else if (store.value == 51004) {
                    bookingTitle.innerHTML = peter;
                }

            });
        });



    // 選擇訂位日期後顯示包廂及時間
    document.getElementById('bookingDate').addEventListener('click', () => {
        console.log('bookingDate 觸擊');
        // if (bookingDate.value == '') {
        //     alert('請選擇訂位日期');
        //     boxSize.innerHTML = '<option selected="selected">選擇包廂</option>';
        //     return;
        // }
        // 顯示包廂
        fetch("http://localhost:8081/CGA103G3_07/BoxInfo/BoxInfo.do")
            .then(res => res.json("allBoxAndStore"))
            .then(allBoxAndStore => {
                let add = '';
                add += '<option selected="selected" disabled>選擇包廂</option>';
                for (let i = 0; i < allBoxAndStore.length; i++) {
                    if (store.value == allBoxAndStore[i].storeID) {
                        storeOff = allBoxAndStore[i].storeOff;
                        add += '<option value="' + allBoxAndStore[i].boxID + '">' + '[ ' + allBoxAndStore[i].boxID + ' ] ' + allBoxAndStore[i].boxDescription + ' / 小時價格(NTD): ' + allBoxAndStore[i].boxPrice + '</option>';
                    }
                    boxSize.innerHTML = add;
                }
                // 選取包廂後顯示訂位時間
                document.getElementById('boxSize').addEventListener('change', () => {
                    fetch("http://localhost:8081/CGA103G3_07/Booking/GetDateAndShowTime.do", {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            boxID: boxSize.value,
                            bookingDate: bookingDate.value
                        })
                    })
                        .then(res => res.json("boxAndBooking"))
                        .then(boxAndBooking => {
                            console.log(boxAndBooking);
                            let startTime = boxAndBooking[0].boxBkStart;
                            let endTime = boxAndBooking[0].boxBkEnd;
                            let arr = new Array(endTime - startTime + 1); // 創建陣列管理訂位時間
                            let i = 0;
                            while (startTime <= endTime) {
                                arr[i] = '';
                                startTime++;
                                i++;
                            }
                            // 將有預訂的時間放入arr陣列中(放入disabled)
                            for (let i = 0; i < boxAndBooking.length; i++) {
                                if (boxAndBooking[i].bookingDate == bookingDate.value && boxAndBooking[i].bookingStatus == 1) {
                                    let count = boxAndBooking[i].bookingStart - boxAndBooking[0].boxBkStart;
                                    while (count <= (boxAndBooking[i].bookingEnd - boxAndBooking[0].boxBkStart)) {
                                        arr[count] = ' style="color:red" disabled';
                                        count++;
                                    }
                                }
                            }
                            // 創建訂位時間
                            let j = 0;
                            add = '';
                            add += '<option value="0" selected="selected" disabled>選擇時間</option>';
                            for (let time = boxAndBooking[0].boxBkStart; time < boxAndBooking[0].boxBkEnd; time++) {
                                add += '<option value="' + time + '"' + arr[j] + '>' + time + ':00' + '</option>';
                                j++;
                            }
                            bookingStart.innerHTML = add;

                            // 監聽創建結束時間
                            document.getElementById('bookingStart').addEventListener('change', () => {
                                let startVaule = bookingStart.value; // 取得選定的訂位起始時間
                                let j = (startVaule - boxAndBooking[0].boxBkStart + 1); // 設定arr陣列索引值取得不可選
                                let end = ++startVaule; // +1結束時間從起始時間下個開始選
                                add = '';
                                add += '<option value="0" selected="selected" disabled>選擇時間</option>';
                                for (end; end <= boxAndBooking[0].boxBkEnd; end++) {
                                    if (arr[j] == ' style="color:red" disabled') {
                                        add += '<option value="' + end + '" >' + end + ':00' + '</option>';
                                        break;
                                    }
                                    add += '<option value="' + end + '"' + arr[j] + '>' + end + ':00' + '</option>';
                                    j++;
                                }
                                bookingEnd.innerHTML = add;
                            });
                        })
                })
            })
    });
});

//submit 送出訂單
function bookingBtn() {
    if (memID.value == '') {
        alert('請輸入會員資料');
        return;
    }

    console.log(memID.value, store.value, boxSize.value, bookingDate.value, bookingStart.value, bookingEnd.value, bookingNote.value);
    document.getElementById('form').submit();

    fetch("http://localhost:8081/CGA103G3_07/bookingorderfetch/bookingorder.do", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            memID: memID.value,
            bookingDate: bookingDate.value,
            bookingStart: bookingStart.value,
            bookingEnd: bookingEnd.value,
            boxID: boxSize.value,
            bookingNote: bookingNote.value
        })
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                const { status, statusText } = response;
                throw Error(`${status}: ${statusText}`);
            }
        })
        .then(obj => console.log(obj))
        .catch(({ message }) => div.textContent = message);

    alert('訂位送出，再請至信箱查收是否成功!!');


}
