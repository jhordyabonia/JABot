package eyes;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
  
public class Eyes implements Runnable
{ 
    public interface Asynchtask {
        /**
        * ESta funcion retorna los datos devueltos por el ws
        * 
        * @param result
        */
        public void processFinish(String result);

    }
    public static String uriBase = "https://westcentralus.api.cognitive.microsoft.com/vision/v2.1/";
    public static String methods [] = {
        "analyze",
        "detect",
        "recognizeText",
        "read/core/asyncBatchAnalyze",
        "recognizeText"
    };
    public static String HELP;
    static{
        StringBuilder builder = new StringBuilder();
        for(int t=0;t<methods.length;t++){
            builder.append("\t\ta"+t+" = "+methods[t]+"\n");
        }
        HELP = builder.toString();
    }
    
    public static String subscriptionKey = "f7f45ffb741841bfb45f6b9873710f5d";
    public static String arrayParams[] = {"Adult","Categories","Color","Description","Faces","ImageType","Tags"};
     
	private URL connectURL;
    private String fileName;
    private FileInputStream fileInputStream = null;
	private String urlString;
	private String response;
    private Asynchtask callBack;
    private int API = 0;
    public Eyes setApi(int a){ 
        this.API = a;
        return this;
    }
    public Eyes(int api, Asynchtask callBack, String fileName ) throws FileNotFoundException
    {
    	this.API = api;
        this.callBack = callBack;
        this.fileName = fileName;          
    	fileInputStream = new FileInputStream(fileName);
    } 
    public Eyes(int api,String fileName ) throws FileNotFoundException
    {
    	this.API = api;
        this.fileName = fileName;   
    	fileInputStream = new FileInputStream(fileName);
        this.callBack = new Asynchtask(){
	        @Override
        	public void processFinish(String result){

            }
        };
    } 
    public String response()
    {	return response;}
    private void thirdTry() 
    {
        String exsistingFileName = fileName;
 
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        try
        {
            //------------------ CLIENT REQUEST 
            // Abrimos una conexión http con la URL 
            HttpURLConnection conn = (HttpURLConnection) connectURL.openConnection();
 
            // Permitimos Inputs
            conn.setDoInput(true);
 
            // Permitimos Outputs
            conn.setDoOutput(true); 
            // Deshabilitamos el uso de la copia cacheada.
            conn.setUseCaches(false); 
            // Usamos el método post esto podemos cambiarlo.
            conn.setRequestMethod("POST"); 
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Accept", "application/json");
            //conn.setRequestProperty("Content-Type", "application/octet-stream");
            conn.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey); 
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);

            DataOutputStream dos = new DataOutputStream( conn.getOutputStream() );
 
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + exsistingFileName +"\"" + lineEnd);
            dos.writeBytes(lineEnd);
 
            // creamos un buffer con el tamaño maximo de archivo, lo pondremos en 1MB
 
            int bytesAvailable = fileInputStream.available();
            int maxBufferSize = 1024;
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
            byte[] buffer = new byte[bufferSize];
 
            int bytesRead = fileInputStream.read(buffer, 0, bufferSize);
 
            while (bytesRead > 0){
	            dos.write(buffer, 0, bufferSize);
	            bytesAvailable = fileInputStream.available();
	            bufferSize = Math.min(bytesAvailable, maxBufferSize);
	            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }
 
            // enviar multipart form data 
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
 
            // cerramos
            fileInputStream.close();
            dos.flush();
 
            InputStream is = conn.getInputStream();
            // retrieve the response from server
            int ch;
 
            StringBuffer b =new StringBuffer();
            while( ( ch = is.read() ) != -1 )
            {b.append( (char)ch );}
            response=b.toString();
            callBack.processFinish(response);
            dos.close();
         }
        catch (MalformedURLException ex){}
        catch (IOException ioe){}
    } 
	
	@Override
	public void run() 
	{
		try{
            if(this.API==0){
                this.urlString = this.methods[this.API]+"?visualFeatures="+String.join(",",this.arrayParams)+"&details";
            }else if (API == 2){
                this.urlString = this.methods[this.API]+"?mode=Printed";
            }else{
                this.urlString = this.methods[this.API];
            }
			this.connectURL = new URL(this.uriBase+this.urlString);
            this.thirdTry();
            System.out.println(this.uriBase+this.urlString);
		} catch (MalformedURLException e) {}
		
	}
}