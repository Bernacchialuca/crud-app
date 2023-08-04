package com.demo.crudapp.service;

import com.demo.crudapp.entity.Tarea;
import com.demo.crudapp.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TareaServiceImpl implements TareaService{

    @Autowired
    private TareaRepository tareaRepository;

    @Override
    public void save(Tarea tarea)  {
        this.tareaRepository.save(tarea);
    }
}
