# MiniDictionaryApp (JavaFX) — Project UAS Algoritma Pemrograman

Aplikasi **kamus mini** berbasis **Binary Search Tree (BST)** dengan GUI **JavaFX**.  
Project ini dibuat untuk memenuhi tugas UAS: mengimplementasikan 2 studi kasus algoritma, dan aplikasi bersifat interaktif.

✅ Implementasi saat ini mencakup:
1. **Tambah Kata (Insert)**
2. **Cari Kata (Search)**
3. **Update Arti (Update)**

🚧 Disiapkan placeholder untuk:
4. **Delete (hapus kata)**
5. **Traversal (Inorder/Preorder/Postorder)**

---

## 1) Struktur Folder

Lokasi kode utama ada di:

`src/main/java/org/example/minidictionaryapp/`
`org.example.minidictionaryapp/`
`app/`
`MiniDictionaryApp.java`
`model/`
`DictionaryBST.java`
`ui/`
`AddView.java`
`AppView.java`
`SearchView.java`
`TodoView.java`
`UpdateView.java`
`util/`
`AppLogger.java`


---

## 2) Penjelasan Tiap File

### `app/MiniDictionaryApp.java`
**Entry point / main class JavaFX.**  
Menjalankan JavaFX, membuat `Stage` dan `Scene`, lalu memuat root UI (`AppView`).

---

### `model/DictionaryBST.java`
**Implementasi algoritma BST (logika inti, tanpa UI).**  
Berisi:
- Struktur `Node` (word, meaning, left, right)
- Method:
  - `insert(word, meaning)` → Fitur 1
  - `search(word)` → Fitur 2
  - `update(word, newMeaning)` → Fitur 3

**TODO untuk kelompok :**
- `delete(word)` → Fitur 4
- `inorder/preorder/postorder` → Fitur 5

---

### `ui/AppView.java`
**Root UI aplikasi (halaman utama).**  
Membuat:
- `TabPane` berisi tab:
  - AddView (Tambah)
  - SearchView (Cari)
  - UpdateView (Update)
  - TodoView (Delete placeholder)
  - TodoView (Traversal placeholder)
- Area **Log/Status**

`AppView` juga membuat **1 instance** `DictionaryBST` dan membagikannya ke semua tab supaya data kamus konsisten.

---

### `ui/AddView.java`
UI untuk **Fitur 1 — Insert**.  
Input: kata + arti, tombol Tambah → memanggil `DictionaryBST.insert(...)`.

---

### `ui/SearchView.java`
UI untuk **Fitur 2 — Search**.  
Input: kata, tombol Cari → memanggil `DictionaryBST.search(...)`.

---

### `ui/UpdateView.java`
UI untuk **Fitur 3 — Update**.  
Input: kata + arti baru, tombol Update → memanggil `DictionaryBST.update(...)`.

---

### `ui/TodoView.java`
Placeholder UI untuk fitur yang belum dibuat (Delete & Traversal).  
Nanti bisa diganti menjadi `DeleteView` / `TraversalView`.

---

### `util/AppLogger.java`
Helper untuk menulis log ke TextArea **Log/Status** dengan format konsisten.

---

## 3) Cara Menjalankan

### Opsi A — Jalankan via IntelliJ IDEA
1. Buka project
2. Jalankan class:
   `org.example.minidictionaryapp.app.MiniDictionaryApp`

---

### Opsi B — Jalankan via Maven
Pastikan `pom.xml` sudah mengarah ke main class ini:

`org.example.minidictionaryapp.app.MiniDictionaryApp`

Lalu jalankan:

```bash
mvn clean javafx:run
