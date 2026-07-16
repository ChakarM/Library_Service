# Spring  + Hibernate  Minimalist Library REST API


### 1. Create a Book
- **Method:** `POST`
- **URL:** `http://localhost:8080/SpringRestPrac/library/books`
- **Body (`application/json`):**
  ```json
  {
      "title": "The Fellowship of the Ring",
      "price": 24.99,
      "author": {
          "name": "J.R.R.",
          "surname": "Tolkien"
      }
  }
  ```
  *(Note: If the author does not exist in the database, the service automatically persists the new author aggregate first before inserting the book record).*

### 2. Retrieve All Books
- **Method:** `GET`
- **URL:** `http://localhost:8080/SpringRestPrac/library/books`

### 3. Retrieve a Specific Book by Title
- **Method:** `GET`
- **URL:** `http://localhost:8080/SpringRestPrac/library/books/{title}`

### 4. Search Books by Author Full Name
- **Method:** `GET`
- **URL:** `http://localhost:8080/SpringRestPrac/library/authors/books?name=J.R.R.&surname=Tolkien`

### 5. Retrieve All Authors
- **Method:** `GET`
- **URL:** `http://localhost:8080/SpringRestPrac/library/authors`

### 6. Delete Book by Title
- **Method:** `DELETE`
- **URL:** `http://localhost:8080/SpringRestPrac/library/authors/books/{title}`

### 7. Update Book Price 
- **Method:** `PUT`
- **URL:** `http://localhost:8080/SpringRestPrac/library/books/{title}?newPrice=25.5`
---

## Global Exception Management
When business invariants or lookup paths fail (e.g., fetching a non-existent title), a custom `ServiceException` is triggered. The request is caught globally out of runtime by an exception interceptor and safely reformatted to protect the servlet engine from crashing:

**Response Status Code:** `404 Not Found`
```json
{
    "error": "Book not found: The Silmarillion"
}
```
