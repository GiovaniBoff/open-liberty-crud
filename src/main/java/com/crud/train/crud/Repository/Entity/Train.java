package com.crud.train.crud.Repository.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "TRAIN")
public class Train implements Serializable {
  private static final long serialVersionUID = 1L;

  @TableGenerator(name = "id_generator", table = "ID_GEN", pkColumnName = "gen_name", valueColumnName = "gen_value",
  pkColumnValue="user_gen", initialValue=1000, allocationSize=10)
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")
  private Long id;

  @NotEmpty(message = "Model must no be empty")
  @Column(name = "LOCOMOTIVE_MODEL")
  private String locomotiveModel;

  @NotEmpty
  @Column(name = "WAGONS_QUANTITY")
  private Integer qtdWagons;
}



