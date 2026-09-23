# So Sánh Log Và Thời Gian Thực Thi (@Cacheable)

### 1. Lần Gọi 1 (Cache Miss - Truy vấn DB)
- **Log Console:**
  ```text
  14280 --- [nio-8080-exec-1] com.example.service.RestaurantService : Querying database for menu of restaurant ID: 101...
  ```
- **Thời gian phản hồi:** **3.32 s** (chạy qua `Thread.sleep(3000)` để mô phỏng DB).

### 2. Lần Gọi 2 (Cache Hit - Lấy từ Cache)
- **Log Console:**
  ```text
  (Không có log truy vấn DB nào được in ra)
  ```
- **Thời gian phản hồi:** **8 ms** (lấy trực tiếp từ cache `restaurantMenu`, key `101`).

### 3. Kết Luận
- `@Cacheable` hoạt động đúng: Lần 2 không gọi vào DB/service method, phản hồi từ **3.32s** giảm xuống còn **8ms**.
