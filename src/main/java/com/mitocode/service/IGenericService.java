package com.mitocode.service;

import java.util.List;

public interface IGenericService<K, ID> {
	
	List<K> listar();
	K listarPorId(ID id);
	K registrar(K k);
	K modificar(K k);
	void eliminar(ID id);

}
