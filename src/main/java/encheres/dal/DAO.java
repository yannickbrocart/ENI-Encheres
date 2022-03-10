package encheres.dal;

import java.util.List;

import encheres.BusinessException;

public interface DAO<T> {

	public int insertWithId(T s) throws BusinessException;

	public void insert(T s) throws BusinessException;

	public void update(int identifiant, T s) throws BusinessException;

	public T selectById(int identifiant) throws BusinessException;

	public List<T> selectAll() throws BusinessException;

	public void delete(int identifiant) throws BusinessException;

}