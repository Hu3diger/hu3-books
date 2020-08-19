package br.inf.hu3diger.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="books")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long _id;
	
	@JsonProperty("id")
	private String bookId;
	
	@JsonProperty("volumeInfo")
	@OneToOne(cascade=CascadeType.PERSIST)
	private BookInformation informations;
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonRootName(value = "items")
	public static class BookModel{
		
		@OneToMany
		@JsonProperty("items")
		private List<Book> books;
	}
	
}
