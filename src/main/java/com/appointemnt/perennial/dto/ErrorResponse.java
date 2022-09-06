package com.appointemnt.perennial.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Error response dto to send error to users whenever exceptions happens
 * @author Mauli satav
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ErrorResponse {
	private String message;
	private LocalDateTime timestamp;
}
