package me.code.uppgift3projekt.dto;

import java.time.LocalDate;

public record PostDTO (String content, String title, String username, LocalDate createdAt) {};
