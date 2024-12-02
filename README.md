
# User Management API

User Management API adalah aplikasi berbasis Spring Boot yang menyediakan fitur manajemen pengguna, termasuk autentikasi menggunakan JWT, pendaftaran pengguna, serta pengelolaan data pengguna.

## Fitur
- **Autentikasi dengan JWT**:
  - Login menghasilkan token JWT.
  - Endpoint dilindungi menggunakan JWT.
- **Manajemen Pengguna**:
  - Mendapatkan daftar semua pengguna.
  - Menambahkan pengguna baru.
  - Mengubah informasi pengguna.
  - Menghapus pengguna.
- **Pengelolaan Role**:
  - Mendukung peran pengguna seperti `ADMIN` dan `USER`.

## Teknologi yang Digunakan
- **Kotlin**: Bahasa dasar pengembangan aplikasi.
- **Spring Boot**: Framework inti untuk pengembangan aplikasi.
- **Spring Security**: Untuk autentikasi dan otorisasi.
- **JWT (JSON Web Token)**: Untuk token autentikasi.
- **MongoDB**: Database untuk penyimpanan data pengguna.
- **Redis**: Redis untuk penyimpanan token.
- **Postman**: Untuk pengujian API.

---

## Instalasi dan Menjalankan Proyek

### Prasyarat
- **Java 17** atau versi lebih baru
- **Maven** (opsional, jika menggunakan IDE dengan Maven bawaan)
- **MongoDB** yang berjalan di mesin Anda atau di server lain
- **Postman** untuk pengujian API (opsional)

### Langkah-Langkah Instalasi
1. Clone repositori ini:
   ```bash
   git clone https://github.com/DoniOctopus/user-management.git
   cd user-management
   ```
2. Pastikan MongoDB berjalan. Gunakan port default (`27017`) atau sesuaikan konfigurasi.
3. Jalankan aplikasi:
   ```bash
   ./mvnw spring-boot:run
   ```
   atau
   ```bash
   mvn spring-boot:run
   ```

---

## Dokumentasi API

### 1. **Login**
**Endpoint**: `POST /auth/login`  
**Request Body**:
```json
{
  "username": "string",
  "password": "string"
}
```
**Response**:
- **200 OK** (Jika berhasil login):
  ```json
  {
    "message": "Success login",
    "token": "string"
  }
  ```
- **401 Unauthorized** (Jika username/password salah):
  ```json
  {
    "message": "Invalid username or password"
  }
  ```

---

### 2. **Mendapatkan Semua Pengguna**
**Endpoint**: `GET /users`  
**Header**:  
```http
Authorization: Bearer <token>
```
**Response**:
- **200 OK**:
  ```json
  [
    {
      "id": "string",
      "username": "string",
      "roles": ["string"]
    }
  ]
  ```

---

### 3. **Membuat Pengguna Baru**
**Endpoint**: `POST /users`  
**Header**:  
```http
Authorization: Bearer <token>
```
**Request Body**:
```json
{
  "username": "string",
  "password": "string",
  "roles": ["USER"]
}
```
**Response**:
- **201 Created**:
  ```json
  {
    "id": "string",
    "username": "string",
    "roles": ["string"]
  }
  ```

---

### 4. **Mengubah Informasi Pengguna**
**Endpoint**: `PUT /users/{id}`  
**Header**:  
```http
Authorization: Bearer <token>
```
**Request Body**:
```json
{
  "username": "string",
  "password": "string",
  "roles": ["string"]
}
```
**Response**:
- **200 OK**:
  ```json
  {
    "id": "string",
    "username": "string",
    "roles": ["string"]
  }
  ```

---

### 5. **Menghapus Pengguna**
**Endpoint**: `DELETE /users/{id}`  
**Header**:  
```http
Authorization: Bearer <token>
```
**Response**:
- **204 No Content**


---

### 6. **Logout**
**Endpoint**: `POST /auth/logout`  
**Header**:  
```http
Authorization: Bearer <token>
```
**Response**:
- **200 OK** (Jika logout berhasil):
  ```json
  {
    "message": "Logout successful"
  }
  ```

---

### 7. **Register Admin**
**Endpoint**: `POST /auth/register/admin`  
**Request Body**:
```json
{
  "username": "string",
  "password": "string",
  "roles": ["ADMIN"]
}
```
**Response**:
- **201 Created**:
  ```json
  {
    "id": "string",
    "username": "string",
    "roles": ["ADMIN"]
  }
  ```
- **400 Bad Request** (Jika username sudah terdaftar):
  ```json
  {
    "message": "Username is already taken"
  }
  ```

---

### 8. **Register User**
**Endpoint**: `POST /auth/register/user`  
**Request Body**:
```json
{
  "username": "string",
  "password": "string",
  "roles": ["USER"]
}
```
**Response**:
- **201 Created**:
  ```json
  {
    "id": "string",
    "username": "string",
    "roles": ["USER"]
  }
  ```
- **400 Bad Request** (Jika username sudah terdaftar):
  ```json
  {
    "message": "Username is already taken"
  }
  ```

---
