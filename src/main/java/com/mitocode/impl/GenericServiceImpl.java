package com.mitocode.impl;

import java.util.List;

import com.mitocode.repository.IGenericRepository;
import com.mitocode.service.IGenericService;

public abstract class GenericServiceImpl<K, ID> implements IGenericService<K, ID> {

	protected abstract IGenericRepository<K, ID> getRepository();
	
	@Override
	public List<K> listar() {
		return getRepository().findAll();
	}

	@Override
	public K listarPorId(ID id) {
		return getRepository().findById(id).orElse(null);
	}

	@Override
	public K registrar(K k) {
		return getRepository().save(k);
	}

	@Override
	public K modificar(K k) {
		return getRepository().save(k);
	}

	@Override
	public void eliminar(ID id) {
		getRepository().deleteById(id);
	}

}
