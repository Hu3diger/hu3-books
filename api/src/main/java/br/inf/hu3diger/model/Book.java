package br.inf.hu3diger.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name="books")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value = "items")
public class Book {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long _id;
	
	@JsonProperty("id")
	private String bookId;
	
	@JsonProperty("volumeInfo")
	@OneToOne
	private BookInformation informations;
	
}
