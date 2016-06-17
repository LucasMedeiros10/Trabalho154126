package br.univel.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import br.univel.excecoes.SerializadorException;
import br.univel.interfaces.Serializador;

public class SerializadorImpl<T> implements Serializador<T>{

	@Override
	public void gravar(T t, File file) throws SerializadorException {
		Class<?>[] vet = t.getClass().getInterfaces();
		
		boolean achou = false;
		
		for(Class<?> c: vet){
			if(c.equals(Serializable.class)){
				achou = true;
				break;
			}			
		}
		
		if(!achou){
			throw new SerializadorException("Classe não implementa Serializable.");
		}
		
		try(FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos)){
			
			oos.writeObject(t);
			
		}catch(Exception e){
			throw new SerializadorException(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T ler(File file) throws SerializadorException {
	
		try(FileInputStream fis   = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis)) {
				
			Object object = ois.readObject();
			
			Class<?> clGenType = (Class<?>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			
			if(!object.getClass().equals(clGenType)){
				throw new SerializadorException("Os tipos são diferentes!");
			}
			
			return (T) object;			
		} catch (Exception e) {
			throw new SerializadorException(e);
		} 	
	}

}
