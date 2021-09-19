package br.com.modelServer.entitys;

import java.util.Calendar;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Loan {

	private Long id;

	private Long userId;

	private Long bookId;

	private Calendar startDate;

	private Calendar endDate;

}