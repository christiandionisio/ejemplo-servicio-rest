package com.mitocode.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Producto;
import com.mitocode.repository.IGenericRepository;
import com.mitocode.repository.IProductoRepository;
import com.mitocode.service.IProductoService;

@Service
public class ProductoServiceImpl extends GenericServiceImpl<Producto, Integer> implements IProductoService{

	@Autowired
	private IProductoRepository repo;
	
	@Override
	protected IGenericRepository<Producto, Integer> getRepository() {
		return repo;
	}

}
