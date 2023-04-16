package fa.training.dao;

import java.util.List;

public interface Dao<T> {
	
	void save(T t);
	
	T getById(int id);
	
	List<T> getAll();
	
	void update(int id);
	
	void delete(int id);
}
