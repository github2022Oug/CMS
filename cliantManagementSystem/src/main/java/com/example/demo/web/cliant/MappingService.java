package com.example.demo.web.cliant;



import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MappingService {

    private final DozerBeanMapper mapper;


}
