//Mohammad Zohaib Abbas, 17L-6305, l176305@lhr.nu.edu.pk, AP-8A

package ap_assign_4;

import java.net.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mzohaib
 */
public class ServerConnection extends Thread{
    Socket socket;
    Server server;
    InputStreamReader in;
    BufferedReader bf;
    PrintWriter pr;
    DB_Crud db;
    boolean shouldRun=true;
    
    public ServerConnection(Socket socket, Server server){
        super("ServerConnectionThread");
        this.socket=socket;
        this.server=server;
        db=DB_Crud.getInstance();
    }
    
    public void sendMessageToClient(String Text){
        
    }
    
    @Override
    public void run(){
        try {
            System.out.println("ya");
        
            in=new InputStreamReader(socket.getInputStream());
            bf=new BufferedReader(in);
            pr=new PrintWriter(socket.getOutputStream());
            
            while(shouldRun){
                while(bf.readLine()==null){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                String str=bf.readLine();
                StringBuffer stringBuffer=new StringBuffer();
                performOperation(str,stringBuffer);
            }
            
            in.close();
            bf.close();
            pr.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void performOperation(String str, StringBuffer stringBuffer){
            switch(str.charAt(0)){
                        case '1': // login request
                            int i=2;
                            for(;str.charAt(i)!=' ';i++){
                                stringBuffer.append(str.charAt(i));
                            }
                            String uname=stringBuffer.toString();
                            stringBuffer=null;
                            stringBuffer=new StringBuffer();
                            i++; 
                            int n=str.length();
                            for(; i<n;i++){
                                stringBuffer.append(str.charAt(i));
                            }
                            String pass=stringBuffer.toString();
                            pass=encrypt(stringBuffer.toString());
                            User u=new User(uname,pass);
                            boolean flag=db.loginUsers(u);
                            if(flag){
                                pr.println("Login Successfull!");
                                pr.flush();
                            }
                            else{
                                pr.println("Error! Login Unsuccessfull");
                                pr.flush();
                            }
                            break;
                       case '2': // signup request
                            i=2;
                            for(;str.charAt(i)!=' ';i++){
                                stringBuffer.append(str.charAt(i));
                            }
                            uname=stringBuffer.toString();
                            stringBuffer=null;
                            stringBuffer=new StringBuffer();
                            i++; 
                            n=str.length();
                            for(; i<n;i++){
                                stringBuffer.append(str.charAt(i));
                            }
                            pass=stringBuffer.toString();
                            pass=encrypt(stringBuffer.toString());
                            u=new User(uname,pass);
                            int check=db.insertUser(u);
                            if(check==1){
                                pr.println("Sign Up Successfull!");
                                pr.flush();
                            }
                            else if(check==0){
                                pr.println("Error Occured while registering user in database");
                                pr.flush();
                            }
                            else{ 
                                pr.println("Error! This Username already exists in database");
                                pr.flush();
                            }
                            break; 
                       case '3': // change username request
                            i=2;
                            for(;str.charAt(i)!=' ';i++){ 
                                stringBuffer.append(str.charAt(i));
                            }
                            uname=stringBuffer.toString();
                            stringBuffer=null;
                            stringBuffer=new StringBuffer();
                            i++; 
                            n=str.length();
                            for(;str.charAt(i)!=' ';i++){
                                stringBuffer.append(str.charAt(i));
                            }
                            String newUname=stringBuffer.toString();
                            stringBuffer=null;
                            stringBuffer=new StringBuffer();
                            i++; 
                            for(;i<n;i++){
                                stringBuffer.append(str.charAt(i));
                            }
                            pass=encrypt(stringBuffer.toString());
                            u=new User(uname,pass);
                            check=db.updateAccountUsername(u,newUname);
                            if(check==1){
                                pr.println("Username Changed Successfully!");
                                pr.flush();
                            }
                            else if(check==-1){
                                pr.println("Error! This Username is Not Available");
                                pr.flush();
                            }
                            else{
                                pr.println("Error Updating Username");
                                pr.flush();
                            }
                            break;
                       case '4': // change password request
                            i=2;
                            for(;str.charAt(i)!=' ';i++){ 
                                stringBuffer.append(str.charAt(i));
                            }
                            uname=stringBuffer.toString();
                            stringBuffer=null;
                            stringBuffer=new StringBuffer();
                            i++; 
                            n=str.length();
                            for(;str.charAt(i)!=' ';i++){
                                stringBuffer.append(str.charAt(i));
                            }
                            String newPass=encrypt(stringBuffer.toString());
                            stringBuffer=null;
                            stringBuffer=new StringBuffer();
                            i++; 
                            for(;i<n;i++){
                                stringBuffer.append(str.charAt(i));
                            }
                            pass=encrypt(stringBuffer.toString());
                            u=new User(uname,pass);
                            flag=db.updateAccountPassword(u,newPass);
                            if(flag){
                                pr.println("Password Changed Successfully!");
                                pr.flush();
                            }
                            else{
                                pr.println("Error! Password Could NOT be Updated");
                                pr.flush();
                            }
                            break;     
                    }

        }
    
    public String encrypt(String str){

        BigInteger bigInteger=new BigInteger(str.getBytes());
        System.out.println(bigInteger.toByteArray());
        System.out.println(Arrays.toString(bigInteger.toByteArray()));
        byte byteArray[]=bigInteger.toByteArray();
        int len=byteArray.length;
        int intArray[]=new int[len];
        for(int i=0;i<len;i++){
            int a=byteArray[i];
            if(a<=63){
                a=a<<1;
            }
            intArray[i]=a^12;
            System.out.println("Integer: "+intArray[i]+" Binary: "+Integer.toBinaryString(intArray[i]));
        }
        
        
        System.out.println();
        StringBuilder stringBuilder=new StringBuilder();
        
        for(int i=0;i<len;i++){
            int val=intArray[i];
            if(val>=32&&val<=126){
                char convertedChar = (char)val;
                stringBuilder.append(convertedChar);
            }
            else{
                stringBuilder.append(String.valueOf(val));
            }
        }
        
        System.out.println("Encrypted Password: "+stringBuilder);
        return stringBuilder.toString();
        
    }
    
}
