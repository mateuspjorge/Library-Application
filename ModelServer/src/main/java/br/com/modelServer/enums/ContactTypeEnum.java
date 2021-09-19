package br.com.modelServer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ContactTypeEnum {

	EMAIL(1L, "E-mail"),
	HOME_PHONE(2L, "Home Phone"),
	CELL_PHONE(3L, "Cell phone"),
	COMERCIAL_PHONE(4L, "Commercial phone");

	private Long code;

	private String description;

	public static ContactTypeEnum getContactTypeEnum(Long cod) {
		for (ContactTypeEnum value : ContactTypeEnum.values()) {
			if (value.getCode().equals(cod))
				return value;
		}
		return null;
	}

	protected void setCode(Long code) {
		this.code = code;
	}

	protected void setDescription(String description) {
		this.description = description;
	}

}