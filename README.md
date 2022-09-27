## 負責項目：購物車(套件名:cart) / 訂單管理(套件名:orderlist、orderdetail)/ 優惠券(套件名:coupontype、memcoupon)

### [專案負責部分pdf介紹](https://drive.google.com/file/d/1o_waNAihJx0Qv4za-GUu9IJDhaYL5xXG/view?usp=sharing)
![個人專題pdf qrcode](https://user-images.githubusercontent.com/108620186/192589174-9e5f7514-ffba-4886-ac7a-1fe5ce9f1dbb.png)
### [專案負責部分影片介紹](https://www.youtube.com/watch?v=ucJHwV3ladU)
![專題youtube QRCODE](https://user-images.githubusercontent.com/108620186/192589190-b66b1ec7-b227-4411-b16b-47a14547a204.png)
## 購物車
* 使用 Cookie 存放 SessionId，讓使用者在登入前就能夠將商品加入購物車，即使離開網站，下次回訪依然可以保留購物車的內容。
* 使用 Redis 存放購物車清單項目，Key 存放 SessionId (Cookie 的 Value)，Value 存放購物車清單項目。
* 點擊加入購物車，同步更新購物車 icon 數量，使用 Ajax 技術，避免畫面重新渲染。
* 利用Ajax發送請求至後端，並以JSON格式接收資料組裝成購物車明細，點擊購物車圖示，即可快速查看購物車明細
* 運用checkbox 加上邏輯判斷，於購買時使用者可以選擇部分商品購買，結帳後購物車會保留剩餘的商品。
* 按下結帳，做交易控制，同時成立訂單、訂單明細、更新商品庫存、更新優惠券狀態、更新購物車內容、寄送訂單完成通知信，只要有一個步驟失敗，訂單就不會成立。

## 優惠券
* 以排程器自動下架已過期的優惠券。
* 以排程器篩選出到期前三天的優惠券，並發信提醒。

![image](https://user-images.githubusercontent.com/108620186/192571435-0f808cb7-ff6c-4657-847b-6f4f2bcb3f31.png)
--- 
![image](https://user-images.githubusercontent.com/108620186/192571705-25836f8b-b124-4fff-9a80-edcef3476c7f.png)
--- 
![image](https://user-images.githubusercontent.com/108620186/192572036-6057542d-be02-481e-a77d-b3699538ad87.png)
--- 
![image](https://user-images.githubusercontent.com/108620186/192572252-bf529c3c-f9d0-42a5-8c4d-02a9559f66d9.png)
