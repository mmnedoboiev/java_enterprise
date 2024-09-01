package org.example; // Оголошення пакету, в якому знаходиться клас Book.

public class Book { // Оголошення класу Book, який представляє книгу.
    private int id; // Поле id, яке зберігає унікальний ідентифікатор книги.
    private String name; // Поле name, яке зберігає назву книги.
    private String author; // Поле author, яке зберігає ім'я автора книги.
    private String year; // Поле year, яке зберігає рік видання книги.
    private String genre; // Поле genre, яке зберігає жанр книги.

    // Метод getId повертає значення id книги.
    public int getId() {
        return id;
    }

    // Метод setId встановлює значення id для книги.
    public void setId(int id) {
        this.id = id;
    }

    // Метод getName повертає назву книги.
    public String getName() {
        return name;
    }

    // Метод setName встановлює назву для книги.
    public void setName(String name) {
        this.name = name;
    }

    // Метод getAuthor повертає ім'я автора книги.
    public String getAuthor() {
        return author;
    }

    // Метод setAuthor встановлює ім'я автора для книги.
    public void setAuthor(String author) {
        this.author = author;
    }

    // Метод getYear повертає рік видання книги.
    public String getYear() {
        return year;
    }

    // Метод setYear встановлює рік видання для книги.
    public void setYear(String year) {
        this.year = year;
    }

    // Метод getGenre повертає жанр книги.
    public String getGenre() {
        return genre;
    }

    // Метод setGenre встановлює жанр для книги.
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
