package Registro;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Clase MultiOutputStream, envia un flujo de datos hacia los flujos definidos
 * @version 0.2 24/11/2014
 * @author Grupo Talos { Jorge Bote Albal�, Juan Jose Ram�n Rodr�guez }
 */
public class MultiOutputStream extends OutputStream
{
	OutputStream[] outputStreams;
	
	public MultiOutputStream(OutputStream... outputStreams)
	{
		this.outputStreams= outputStreams; 
	}
	
	@Override
	public void write(int b) throws IOException
	{
		for (OutputStream out: outputStreams)
			out.write(b);			
	}
	
	@Override
	public void write(byte[] b) throws IOException
	{
		for (OutputStream out: outputStreams)
			out.write(b);
	}
 
	@Override
	public void write(byte[] b, int off, int len) throws IOException
	{
		for (OutputStream out: outputStreams)
			out.write(b, off, len);
	}
 
	@Override
	public void flush() throws IOException
	{
		for (OutputStream out: outputStreams)
			out.flush();
	}
 
	@Override
	public void close() throws IOException
	{
		for (OutputStream out: outputStreams)
			out.close();
	}	
}