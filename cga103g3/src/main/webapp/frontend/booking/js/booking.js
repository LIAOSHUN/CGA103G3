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

	let column = [10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21];

	// store設定 -----------------------------------------------------------------------------------		
	fetch("http://localhost:8081/CGA103G3_07/store/store.do")
		.then(res => res.json("storeList"))
		.then(storeList => {
			arr = storeList;
			// console.log(storeList);
			let add = '';
			for (let i = 0; i <= storeList.length; i++) {
				add = '';
				add += '<option selected="selected" disabled>選擇門市</option>';
				for (let j = 0; j < i; j++) {
					add += '<option value="' + storeList[j].storeID + '">' + storeList[j].storeName + '</option>';
				};
				store.innerHTML = add;
			};
		});



	// 包廂-------------------------------------------------------------------------------------------
	document.getElementById('store').addEventListener('change', box => {
		fetch("http://localhost:8081/CGA103G3_07/box/box.do")
			.then(res => res.json("allBox"))
			.then(allBox => {
				// console.log(allBox);
				let add = '';
				add += '<option selected="selected" disabled>選擇包廂</option>';
				for (let i = 0; i < allBox.length; i++) {
					if (store.value == allBox[i].storeID) {
						add += '<option value="' + allBox[i].boxID + '">' + allBox[i].boxDescription + '</option>';
					}
					boxSize.innerHTML = add;
				}
			})
		if (store.value == 51001) {
			bookingTitle.innerHTML = java;
		} else if (store.value == 51002) {
			bookingTitle.innerHTML = servlet;
		} else if (store.value == 51003) {
			bookingTitle.innerHTML = mySQL;
		} else if (store.value == 51004) {
			bookingTitle.innerHTML = peter;
		}
	})

	//指定每周固定日
	//	$("#bookingDate").datepicker({
	//		minDate: 1,
	//		maxDate: "15D",
	//		dateFormat: "yy-mm-dd"
	//		// beforeShowDay: nationalDays
	//		// beforeShowDay: function (date1) { return [date1.getDay() != 2, '']; }
	//	});

	//修改中=========================================================================================================================

	// 如有訂單轉跳至此方法
	function bookingMethod() {
		fetch("http://localhost:8081/CGA103G3_07/BookingSet/BookingSet.do")
			.then(res => res.json("allBooking"))
			.then(allBooking => {
				console.log(allBooking);
				console.log('bookingMethod()');
				let checkStart, boxEnd;
				let add = '';
				column = ['', '', '', '', '', '', '', '', '', '', '', ''];
				for (let i = 0; i < allBooking.length; i++) {
					add = '';
					add += '<option selected="selected" disabled>選擇時間</option>';
					if (allBooking[i].boxID == boxSize.value && bookingDate.value == allBooking[i].bookingDate) {
						checkStart = allBooking[i].bookingStart;
						checkEnd = allBooking[i].checkEnd;
						console.log('checkStart= ' + checkStart);
						console.log('checkEnd= ' + checkEnd);
						for (let i = 0; i <= column.length; i++) {
							if (column[i] == checkStart) {
								for (let inn = checkStart; inn <= checkEnd; inn++) {
									column[i] = ' disabled';
									i++;
								}
							}
						}
					};
				};
				let i = 0;
				for (let start = 10; start < 21; start++) {
					add += '<option value="' + start + '"' + column[i] + '>' + start + ':00' + '</option>';
					i++;
				};

				// for (let start = checkStart; start < checkStart; start++) {
				// 	add += '<option value="' + start + '">' + start + ':00' + '</option>';
				// };
				// for (let start = checkEnd; start < boxEnd; start++) {
				// 	add += '<option value="' + start + '">' + start + ':00' + '</option>';
				// };
				bookingStart.innerHTML = add;
			});
	}

	//修改中=========================================================================================================================
	// $('#bookingDate').on('change', function () )}


	document.getElementById('boxSize').addEventListener('change', () => {
		console.log('來自包廂資訊= ' + boxSize.value);
		fetch("http://localhost:8081/CGA103G3_07/BookingSet/BookingSet.do")
			.then(res => res.json("allBooking"))
			.then(allBooking => {
				let getBox = '';
				let getBoxBkStart = '';
				let getBoxBkEnd = '';
				let add = '';
				for (let i = 0; i < allBooking.length; i++) {
					add = '';
					add += '<option value="0" selected="selected">選擇時間</option>';
					if (allBooking[i].boxID == boxSize.value) {
						console.log('取得getBox =' + boxSize.value);
						getBox = allBooking[i].boxID;
						getBoxBkStart = allBooking[i].boxBkStart;
						getBoxBkEnd = allBooking[i].boxBkEnd;
						console.log('getBoxBkStart= ' + getBoxBkStart);
						console.log('getBoxBkEnd= ' + getBoxBkEnd);
					}
				};
				for (let i = 0; i < allBooking.length; i++) {
					if (allBooking[i].boxID == boxSize.value && bookingDate.value == allBooking[i].bookingDate) {
						bookingMethod()
						console.log('有訂位資料轉送bookingMethod');
						return;
					}
				};
				for (let start = getBoxBkStart; start < getBoxBkEnd; start++) {
					add += '<option value="' + start + '">' + start + ':00' + '</option>';
				}
				bookingStart.innerHTML = add;
				console.log('boxSize結束')
			});
	})

	// 結束時間設定-----------------------------------------------------------------------------------------------
	document.getElementById('bookingStart').addEventListener('change', e => {
		fetch("http://localhost:8081/CGA103G3_07/BookingSet/BookingSet.do")
			.then(res => res.json("allBooking"))
			.then(allBooking => {
				let add = '';
				let end = bookingStart.value; //取得訂位選取起始時間
				let getBookingStart = '';
				let getBookingEnd = '';
				let getBoxBkEnd = '';
				for (let i = 0; i < allBooking.length; i++) {
					if (allBooking[i].boxID == boxSize.value && bookingDate.value == allBooking[i].bookingDate) {
						setBookingEndTime();
						return;
					}
					if (allBooking[i].boxID == boxSize.value) {
						getBoxBkEnd = allBooking[i].boxBkEnd;
						noBooking();
						return;
					}
				}
				function noBooking() {
					add += '<option selected="selected" disabled>選擇結束時間</option>';
					for (let start = ++end; start <= getBoxBkEnd; start++) {
						console.log('這是bookingStart監聽地方');
						add += '<option value="' + start + '">' + start + ':00' + '</option>';
					}
					bookingEnd.innerHTML = add;
				}
			})
	});

	// 設定已有預定的日期包廂之剩餘結束時間
	function setBookingEndTime() {
		fetch("http://localhost:8081/CGA103G3_07/BookingSet/BookingSet.do")
			.then(res => res.json("allBooking"))
			.then(allBooking => {
				let end = bookingStart.value; //取得訂位選取起始時間
				let getBookingStart = '';
				let getBookingEnd = '';
				let getBoxBkEnd = '';
				let add = '';
				console.log('來自setBookingEndTime()的訊息');
				console.log('來自setBookingEndTime()的訊息bookingStart.value= ' + end);

				for (let i = 0; i < allBooking.length; i++) {
					if (allBooking[i].boxID == boxSize.value && bookingDate.value == allBooking[i].bookingDate) {
						getBookingStart = allBooking[i].bookingStart;
						getBookingEnd = allBooking[i].bookingEnd;
						getBoxBkEnd = allBooking[i].boxBkEnd;
					}
				}
				if (end < getBookingStart) { 	// 如果預訂時間在已預訂的開始時間前，預訂起始時間 -> 已預訂的起始時間
					for (let start = ++end; start <= getBookingStart; start++) {
						add += '<option value="' + start + '">' + start + ':00' + '</option>';
					}
					bookingEnd.innerHTML = add;
				} else if (end > getBookingStart) { 	// 如果預訂的時間在已預訂時間之後，預訂的結束時間 -> 包廂設定結束時間
					console.log('來自setBookingEndTime()方法的getBoxBkEnd= ' + getBoxBkEnd);
					for (let getBookingEnd = ++end; getBookingEnd <= getBoxBkEnd; getBookingEnd++) {
						add += '<option value="' + getBookingEnd + '">' + getBookingEnd + ':00' + '</option>';
					}
					bookingEnd.innerHTML = add;
					return;
				}
			})
	}

	//修改中=========================================================================================================================

	// 指定每周固定日
	//	$("#bookingDate").datepicker({
	//		minDate: 0,
	//		maxDate: "15D",
	//		dateFormat: "yy-mm-dd"
	//		// beforeShowDay: nationalDays
	//		// beforeShowDay: function (date1) { return [date1.getDay() != 2, '']; }
	//	});

	// 日期 限定日期不可選
	//https://www.796t.com/content/1550233634.html
	// natDays = [
	// 	[2022, 08, 17], [2022, 08, 20], [2022, 08, 30], [2022, 08, 23]
	// ];

	// function nationalDays(date) {
	// 	for (i = 0; i < natDays.length; i++) {
	// 		if (date.getFullYear() == natDays[i][0] && date.getMonth() == natDays[i][1] - 1
	// 			&& date.getDate() == natDays[i][2]) {
	// 			return [false, natDays[i][0] + '_day'];
	// 		}
	// 	}
	// 	return [true, ''];
	// }

	//指定每周固定日
	//https://jsfiddle.net/RaYZ5/19/
	// $("#bookingDate").datepicker({
	// 	minDate: 0,
	// 	maxDate: "15D",
	// 	dateFormat: "yy-mm-dd",
	// 	// beforeShowDay: nationalDays
	// 	beforeShowDay: function (date1) { return [date1.getDay() != 2, '']; }
	// });

});

//submit
function bookingBtn() {
	if (store.value == '選擇門市' || store.value == 0) {
		alert('未選擇門市');
		return;
	}

	if (boxSize.value == 0 || boxSize.value == '選擇包廂') {
		alert('請選擇包廂');
		return;
	}

	if (bookingStart == 0 || bookingStart == '選擇時間') {
		alert('訂位起始時間有誤，請重新選取');
		return;
	}

	if (bookingEnd == 0 || bookingEnd == '選擇時間') {
		alert('訂位結束時間有誤，請重新選取');
		return;
	}

	if (memID.value == '') {
		alert('請輸入會員資料');
		return;
	}

	if (bookingDate.value == '') {
		alert('請選擇預訂日期');
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
		.then(res => {
			if (response.ok) {
				return response.json();
			} else {
				const { status, statusText } = response;
				throw Error(`${status}: ${statusText}`);
			}
		})
		.then(obj => console.log(obj))
		.catch(({ message }) => div.textContent = message);

	alert('預訂成功');


}