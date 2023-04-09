package com.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;
	
	@NotEmpty
	@Size(min = 4, max = 50)
	private String categoryTitle;
	
	@NotEmpty
	@Size(min = 4, max = 1000)
	private String categoryDescription;
	
}
