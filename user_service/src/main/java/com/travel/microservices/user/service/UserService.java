package com.travel.microservices.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.travel.microservices.user.exception.RecordNotFoundException;
import com.travel.microservices.user.model.UserEntity;
import com.travel.microservices.user.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    UserRepository repository;
   
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    
    public List<UserEntity> getAllEmployees()
    {
        List<UserEntity> employeeList = repository.findAll();
         
        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<UserEntity>();
        }
    }
    
    public UserEntity createOrUpdateUser(UserEntity entity) throws RecordNotFoundException
    {
        Optional<UserEntity> user = repository.findById(entity.getId());
        if(user.isPresent())
        {
        	UserEntity newEntity = user.get();
            newEntity.setUsername(entity.getUsername());
            newEntity.setPassword(entity.getPassword());
            newEntity = repository.save(newEntity);
            return newEntity;
        } else {
            entity = repository.save(entity);
            LOG.debug("/Users response for findbyname: {}", entity);
            return entity;
        }
    }
    
    public String getUserById(Long id) throws RecordNotFoundException
    {
    	RestTemplate restTemplate = new RestTemplate();

        String response1 = restTemplate
                .exchange("http://lp-5cd7465h0j.hclt.corp.hcl.in:7003/api/review/findAll"
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<String>() {
            }, id).getBody();
     
        return response1;
    }
    
    
}
