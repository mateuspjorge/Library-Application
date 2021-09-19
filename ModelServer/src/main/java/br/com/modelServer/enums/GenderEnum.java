package br.com.modelServer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum GenderEnum {

	MALE(1L, "Male"), FEMALE(2L, "Female");

	private Long code;

	private String description;

	public static GenderEnum getGenderEnum(Long cod) {
		for (GenderEnum value : GenderEnum.values()) {
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