package com.dharshiny.application.Repository;

import com.dharshiny.application.Model.SeriesModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends JpaRepository<SeriesModel, Integer>{

}