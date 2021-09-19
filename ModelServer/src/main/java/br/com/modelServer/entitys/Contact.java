package br.com.modelServer.entitys;

import br.com.modelServer.enums.ContactTypeEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Contact {

	private Long id;

	private String description;

	private ContactTypeEnum type;

}