package service;

import java.lang.reflect.Field;
import java.util.List;

import org.json.simple.JSONObject;

/**
 * 
 * Interface that includes utility methods for the service
 *
 */
public interface Utility {
	
	public abstract List<Field> getAllFields(List<Field> fields, Class<?> type);
	
	public abstract double max(List<Double> p);
	
	public abstract double min(List<Double> p) ;
	
	public abstract double std(List<Double> p, double avg);
	
	public abstract boolean opPrezzo(double price, String op,String dati);
	
	public abstract boolean opString(String s, String op,String dati);
	
	public abstract void printJsonObject(JSONObject jsonObj);
}
