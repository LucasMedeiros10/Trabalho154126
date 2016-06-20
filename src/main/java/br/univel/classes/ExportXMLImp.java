package br.univel.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;

import br.univel.interfaces.ExportXML;

public class ExportXMLImp<T> implements ExportXML<T> {

	@Override
	public boolean ExportarXml(T t, File file) {
		boolean resultado = false;
		
		StringWriter out = new StringWriter();
		JAXBContext context = null;

		try {
			context = JAXBContext.newInstance(t.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(t, new StreamResult(out));
		
			
			String  xml = out.toString();
			FileWriter fw = new FileWriter(file);
			fw.write(xml);
			fw.close();		
			resultado = true;
			
		} catch (PropertyException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		

		return resultado;
	}
			
	

	@Override
	public T ImportarXml(T t, File file) {
		
		String xml = null;

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			StringBuilder sb = new StringBuilder();
			String line = null;

			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");

			}

			xml = sb.toString();
			br.close();
			fr.close();
			
			StringReader in = new StringReader(xml);

			JAXBContext context = JAXBContext.newInstance(t.getClass());
			Unmarshaller unmarshaller = context.createUnmarshaller();

			t = (T) unmarshaller.unmarshal(in);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;

	}
}
