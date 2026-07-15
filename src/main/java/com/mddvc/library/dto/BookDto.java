package com.mddvc.library.dto;

import java.math.BigDecimal;

public record BookDto(String title, AuthorDto author, BigDecimal price) {
}
