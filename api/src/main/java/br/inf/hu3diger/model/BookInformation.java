package br.inf.hu3diger.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="bookInformations")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookInformation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	@JsonProperty("title")
	@Column(columnDefinition = "text")
	private String title;
	
	@JsonProperty("description")
	@Column(columnDefinition = "text")
	private String description;
	
	@JsonProperty("imageLinks")
	@OneToOne(cascade=CascadeType.PERSIST)
	private BookImageLinks images;
	
	@JsonProperty("infoLink")
	private String bookUrl;
	
	private Integer flagFav = 0;
	
}
