# URL Shortener
A simple URL shortener application built with **Spring Boot**. This project allows users to shorten long URLs into a short, manageable format and retrieve the original URL using the shortened link.
## Features

- **Shorten URLs**: Converts long URLs into short ones using an MD5 hash.
- **Redirect**: Redirects users to the original URL using the short URL.
- **Database Integration**: Stores original and shortened URLs in a MySQL database.
- **REST API**: Exposes endpoints for URL shortening and redirection.

---

## Tech Stack

- **Backend**: Spring Boot, Java
- **Database**: MySQL
- **ORM**: Spring Data JPA (Hibernate)
- **Hashing**: MD5 algorithm for URL hashing
- **Tools**: IntelliJ IDEA, Postman for API testing

---

## Installation and Usage

### Prerequisites
- Java 17 or later
- MySQL Server
- Maven
