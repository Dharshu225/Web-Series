package com.dharshiny.application.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dharshiny.application.Model.SeriesModel;
import com.dharshiny.application.Repository.SeriesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController{

    @Autowired
    private SeriesRepository seriesRepository;

    @PostMapping(value="/addSeries")
    public SeriesModel addSeries(@RequestBody SeriesModel series){
        return seriesRepository.save(series);
    }

    @GetMapping(value="/series")
    public List<SeriesModel> getAllSeries(){
        List<SeriesModel> series=new ArrayList<>();
        seriesRepository.findAll().forEach(series::add);
        return series;
    }

    @GetMapping(value="/series/id/{id}")
    public Optional<SeriesModel> getById(@PathVariable(value="id") int sid){
        return seriesRepository.findById(sid);
    }

    @GetMapping(value="/series/name/{name}")
    public SeriesModel getByName(@PathVariable(value="name") String sname){
        SeriesModel series=new SeriesModel();
        seriesRepository.findAll().forEach((temp)-> {
            if(sname.equals(temp.getName())){
                series.setId(temp.getId());
                series.setName(temp.getName());
                series.setSeasons(temp.getSeasons());
                series.setEpisodes(temp.getEpisodes());
                series.setRating(temp.getRating());
            }});
        
        return series;
    }

    @PutMapping(value="/update")
    public SeriesModel update(@RequestBody SeriesModel series){
        return seriesRepository.save(series);
    }

    @DeleteMapping(value="/delete/{id}")
    public String delete(@PathVariable(value="id") int sid){
        try{
            seriesRepository.deleteById(sid);
            return "Series Removed ! "+sid;
        }catch(Exception e){
            return "Series cannot be deleted";
        }
    }

}