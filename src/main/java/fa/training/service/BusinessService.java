package fa.training.service;

import java.util.List;

import fa.training.entities.Clazz;

public interface BusinessService {
	
	void insert();
	
	int getTotalTrainees(String skill,int year);
	
	void pivotTableClazz(int month);
	
	List<Clazz> findByAdmin(String clazzAdmin);
	
	List<Object[]> findAllDetail();
	
}
