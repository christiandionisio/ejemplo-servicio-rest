package com.mitocode.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Persona;
import com.mitocode.repository.IGenericRepository;
import com.mitocode.repository.IPersonaRepository;
import com.mitocode.service.IPersonaService;

@Service
public class PersonaServiceImpl extends GenericServiceImpl<Persona, Integer> implements IPersonaService{

	@Autowired
	private IPersonaRepository repo;
	
	@Override
	protected IGenericRepository<Persona, Integer> getRepository() {
		return repo;
	}

}
