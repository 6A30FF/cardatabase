package com.packt.cardatabase.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource/*(path="vehicles")*/
public interface CarRepository extends CrudRepository<Car, Long> {
    // 쿼리 정의 findBy A And,Or B OrderByAsc 가능
    // SQL문 이용 가능
//    @Query("select c from Car c where c.brand like %?1")
    List<Car> findByBrand(@Param("brand") String brand);
    List<Car> findByColor(String color);
    List<Car> findByYear(int year);

}
