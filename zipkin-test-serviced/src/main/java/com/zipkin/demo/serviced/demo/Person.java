package com.zipkin.demo.serviced.demo;


import lombok.Data;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.Set;

/**
 * @ClassName Person
 * @Description
 * @Author JackZhou
 **/

@Data
public class Person {
   @NotBlank
   private String id;
   @NotBlank
   private String name;
   @Min(16)
   @NotNull
   private Integer age;

   public static void main(String[] args) {
      //单独使用 演示
      Person person = new Person();
      ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
      Validator validator = validatorFactory.getValidator();
      Set<ConstraintViolation<Person>> validate = validator.validate(person, Default.class);
      for(ConstraintViolation<Person> configConstraintViolation : validate){
         System.out.println(configConstraintViolation.getPropertyPath() + configConstraintViolation.getMessage());
      }
   }
}
