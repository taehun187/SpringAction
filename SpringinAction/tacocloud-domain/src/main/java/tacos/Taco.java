package tacos;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import jakarta.persistence.FetchType;

import org.springframework.data.rest.core.annotation.RestResource;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Data
@Entity
//@RestResource(rel="tacos", path="tacos")
public class Taco {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  
  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;
  
  private Date createdAt;

  @ManyToMany(targetEntity=Ingredient.class, fetch = FetchType.EAGER)
  @Size(min=1, message="You must choose at least 1 ingredient")
  private List<Ingredient> ingredients;

  @PrePersist
  void createdAt() {
    this.createdAt = new Date();
  }
}
