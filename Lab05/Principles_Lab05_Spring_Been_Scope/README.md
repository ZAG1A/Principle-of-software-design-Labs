# Coffee Menu REST API (Lab 05)

ระบบ RESTful API สำหรับจัดการเมนูกาแฟ (Coffee Menu) พัฒนาด้วย **Spring Boot** และ **Java**

---

## ข้อมูลผู้จัดทำ (Student Information)
* **ชื่อ-นามสกุล:** นายอนันต์เอกก์ ใหญ่พงศกร
* **รหัสนักศึกษา:** 673380430-9
* **กลุ่ม (Section):** 03

---

## ความต้องการของระบบ (Prerequisites)
* **Java JDK:** Version 17 หรือใหม่กว่า (โปรเจกต์นี้ใช้ Java 24)
* **Build Tool:** Maven Wrapper (`mvnw` / `mvnw.cmd` มีมาพร้อมในโปรเจกต์)

---

## วิธีการรันโปรแกรม (How to Run)

เปิด Terminal / PowerShell ในโฟลเดอร์โปรเจกต์ (`coffeemenu`) แล้วรันคำสั่ง:

### สำหรับ Windows (PowerShell / Command Prompt):
```powershell
.\mvnw.cmd spring-boot:run
```

### สำหรับ macOS / Linux (หรือเครื่องที่มี Maven ติดตั้งไว้แล้ว):
```bash
./mvnw spring-boot:run
# หรือ
mvn spring-boot:run
```

> **หมายเหตุ:** เมื่อรันสำเร็จ แอปพลิเคชันจะเปิดทำงานที่ `http://localhost:8080`

---

## สรุปรายการ Endpoint (Endpoint Summary)

| HTTP Method | Endpoint | คำอธิบาย | Expected Status |
| :--- | :--- | :--- | :--- |
| **GET** | `/coffees` | ดึงข้อมูลรายการกาแฟทั้งหมด | `200 OK` |
| **GET** | `/coffees/{id}` | ดึงข้อมูลกาแฟรายรายการตาม ID | `200 OK` |
| **POST** | `/coffees` | เพิ่มรายการกาแฟใหม่ | `201 Created` |
| **PUT** | `/coffees/{id}` | แก้ไขข้อมูลรายการกาแฟตาม ID | `200 OK` |
| **DELETE** | `/coffees/{id}` | ลบรายการกาแฟตาม ID | `200 OK` |

---

## ตัวอย่างการเรียกใช้งาน API (API Call Examples & Test Results)

### 1. ดึงรายการกาแฟทั้งหมด (GET /coffees)
```powershell
curl.exe http://localhost:8080/coffees
```
**ตัวอย่างผลลัพธ์:**
```json
[{"id":1,"name":"Espresso","price":45.0},{"id":2,"name":"Latte","price":55.0}]
```

---

### 2. ดึงรายการกาแฟตาม ID (GET /coffees/{id})
```powershell
curl.exe http://localhost:8080/coffees/1
```
**ตัวอย่างผลลัพธ์:**
```json
{"id":1,"name":"Espresso","price":45.0}
```

---

### 3. เพิ่มรายการกาแฟใหม่ (POST /coffees)
```powershell
Invoke-RestMethod -Method Post -Uri http://localhost:8080/coffees -ContentType "application/json" -Body '{"name":"Cappuccino","price":60.0}'
```
**ตัวอย่างผลลัพธ์:**
```text
id name       price
-- ----       -----
 3 Cappuccino  60.0
```

---

### 4. แก้ไขรายการกาแฟ (PUT /coffees/{id})
```powershell
Invoke-RestMethod -Method Put -Uri http://localhost:8080/coffees/2 -ContentType "application/json" -Body '{"name":"Latte","price":50.0}'
```
**ตัวอย่างผลลัพธ์:**
```text
id name  price
-- ----  -----
 2 Latte  50.0
```

---

### 5. ลบรายการกาแฟ (DELETE /coffees/{id})
```powershell
Invoke-RestMethod -Method Delete -Uri http://localhost:8080/coffees/3
```

---

## ผลการทดสอบ (Verification Summary)

| Endpoint | HTTP Method | Action / Result | Status |
| :--- | :--- | :--- | :--- |
| `/coffees` | **GET** | Retrieved initial coffee list | Passed |
| `/coffees/1` | **GET** | Retrieved single coffee by ID (`1 Espresso`) | Passed |
| `/coffees` | **POST** | Created new coffee (`3 Cappuccino`, `60.0`) | Passed |
| `/coffees/2` | **PUT** | Updated Latte price from 55.0 to 50.0 | Passed |
| `/coffees/3` | **DELETE** | Removed Cappuccino (`id: 3`) | Passed |

---

## Section 5: Discussion (ตอบคำถามท้ายบทเรียน)

### 1. HTTP method แต่ละตัว (GET/POST/PUT/DELETE) ต่างกันอย่างไร ยกตัวอย่างจากโปรเจกต์ตัวเอง
* **GET:** ใช้ดึง/อ่านข้อมูลจาก Server เช่น `GET /coffees` เพื่อดึงรายการกาแฟทั้งหมด
* **POST:** ใช้สร้าง/เพิ่มข้อมูลใหม่ลงใน Server เช่น `POST /coffees` เพื่อเพิ่มเมนู Cappuccino
* **PUT:** ใช้แก้ไข/อัปเดตข้อมูลเดิมที่มีอยู่แล้ว เช่น `PUT /coffees/2` เพื่อแก้ไขราคาของ Latte
* **DELETE:** ใช้ลบข้อมูลออกจาก Server เช่น `DELETE /coffees/3` เพื่อลบเมนูกาแฟ ID 3

### 2. ทำไมต้องแยก Controller กับ Service ออกจากกัน มีข้อดีอย่างไรถ้าโปรแกรมโตขึ้น
* **เหตุผลที่ต้องแยก:** เพื่อทำตามหลัก *Separation of Concerns* โดยให้ **Controller** ทำหน้าที่จัดการเกี่ยวกับ HTTP Request/Response เท่านั้น ส่วน **Service** ทำหน้าที่ประมวลผล Business Logic และจัดการข้อมูล
* **ข้อดีเมื่อโปรแกรมโตขึ้น:** ช่วยให้ Code เป็นระเบียบ อ่านและดูแลรักษาง่าย (Maintainability) สามารถนำ Logic ใน Service ไปใช้ซ้ำได้ง่าย และสามารถเขียน Unit Test ได้สะดวก

### 3. ข้อมูลที่เก็บไว้ใน List ใน memory หายไปตอนไหน และถ้าอยากไม่ให้หายควรทำอย่างไร
* **เวลาที่ข้อมูลหาย:** ข้อมูลจะหายไปเมื่อทำการ **Stop** หรือ **Restart** แอปพลิเคชัน เนื่องจากจัดเก็บไว้ใน RAM ชั่วคราว
* **แนวทางแก้ไข:** เปลี่ยนจากการเก็บใน `List` ไปใช้ฐานข้อมูล (Database) เช่น H2, MySQL, PostgreSQL ร่วมกับ Spring Data JPA เพื่อบันทึกข้อมูลลงดิสก์อย่างถาวร

### 4. `@RestController`, `@GetMapping`, `@PostMapping`, `@PathVariable`, `@RequestBody` แต่ละตัวทำหน้าที่อะไร
* **`@RestController`:** ระบุว่า Class นี้คือ Web Controller สำหรับสร้าง RESTful API โดยแปลง Return Value เป็น JSON โดยอัตโนมัติ
* **`@GetMapping` / `@PostMapping`:** จับคู่ HTTP Request แบบ GET และ POST เข้ากับ Method ที่กำหนด
* **`@PathVariable`:** ดึงค่าพารามิเตอร์จาก URL Path เช่น `id` จาก `/coffees/{id}`
* **`@RequestBody`:** แปลงข้อมูล JSON ที่ส่งมาใน HTTP Request Body ให้กลายเป็น Java Object

---

## โครงสร้างโปรเจกต์ (Project Structure)
```text
coffeemenu/
├── src/
│   └── main/
│       └── java/
│           └── com/example/coffeemenu/
│               ├── CoffeemenuApplication.java
│               ├── Coffee.java
│               └── CoffeeController.java
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```
