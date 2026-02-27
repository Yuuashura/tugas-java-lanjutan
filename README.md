# 👑 E-Commerce Microservices API - Kerajaan Iblis Asura

Proyek ini adalah implementasi **Microservices Architecture** menggunakan **Java Spring Boot**. Sistem ini memisahkan logika bisnis ke dalam dua layanan mandiri (Kerajaan Barang dan Kerajaan Penjualan) yang saling berkomunikasi menggunakan `WebClient`.

## 🛠️ Tech Stack
* **Bahasa:** Java 17+
* **Framework:** Spring Boot 3.x
* **Komunikasi Antar-Layanan:** Spring WebFlux (WebClient)
* **Database:** PostgreSQL (Spring Data JPA)
* **Dokumentasi API:** Springdoc OpenAPI (Swagger UI)

---

## 📖 Dokumentasi Swagger UI (Peta Harta Karun)
Anda dapat menguji semua rute API secara langsung melalui antarmuka Swagger yang terintegrasi. Klik tautan sakti di bawah ini saat server sedang menyala di komputer lokal Anda:

* 🛡️ **[Swagger Layanan Barang (Port 8000)](http://localhost:8000/swagger-ui/index.html)**
* 💰 **[Swagger Layanan Penjualan (Port 8001)](http://localhost:8001/swagger-ui/index.html)**

---

## 🏗️ Arsitektur Sistem

Sistem ini terdiri dari dua *microservice* utama yang berjalan di *port* yang berbeda:

### 1. 🛡️ Layanan Barang (Port `8000`)
Bertugas sebagai gudang utama (Inventory) yang menyimpan data pusaka, mengelola stok, dan harga.

| Method   | Endpoint | Deskripsi | Parameter / Body |
| :---     | :---     | :---      | :---             |
| `GET`    | `/barang/getBarang` | Mengambil detail satu barang spesifik. | `@RequestBody BarangPayloadReq` |
| `GET`    | `/barang/getAllBarang` | Mengambil semua daftar barang (Fitur paginasi segera hadir). | - |
| `GET`    | `/barang/cekStock` | Mengecek sisa stok dan harga barang. | `@RequestParam("idBarang")` |
| `POST`   | `/barang/updateStock` | Memperbarui jumlah stok barang setelah transaksi. | `@RequestBody PenjualanPayloadReq` |
| `DELETE` | `/barang/deleteBarang`| Menghapus data barang dari gudang. | `@RequestBody BarangPayloadReq` |

### 2. 💰 Layanan Penjualan (Port `8001`)
Bertugas menangani transaksi pembelian. Layanan ini tidak menyimpan harga barang, melainkan menelepon **Layanan Barang** via `WebClient` untuk memastikan stok dan harga secara *real-time*.

| Method   | Endpoint | Deskripsi | Parameter / Body |
| :---     | :---     | :---      | :---             |
| `GET`    | `/penjualan/getStockByBarang` | Mengecek stok barang dengan memanggil API Port 8000. | `@RequestParam("idBarang")` |
| `GET`    | `/penjualan/getPenjualan` | Melihat seluruh riwayat transaksi penjualan. | - |
| `POST`   | `/penjualan/tambah` | Mencatat transaksi baru, menghitung total harga, dan memvalidasi stok. | `@RequestBody PenjualanPayloadReq` |

---

## 🚀 Cara Menjalankan Aplikasi (Local Setup)

1. **Pastikan Database Aktif:**
   Pastikan PostgreSQL sudah berjalan dan konfigurasi `application.properties` (URL, username, password) pada kedua layanan sudah disesuaikan.

2. **Jalankan Layanan Barang Terlebih Dahulu (Wajib):**
   Buka terminal di direktori proyek `barang`, lalu jalankan:
   ```bash
   mvn spring-boot:run