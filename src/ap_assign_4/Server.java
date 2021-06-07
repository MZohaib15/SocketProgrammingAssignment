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
public class Server {

    ServerSocket ss;
    int num=0;
    public static void main(String[] args) {
       new Server();
    }
    
    public Server(){
        try {
            ss=new ServerSocket(4999);
            while(true){
                Socket s=ss.accept();
                Thread sc=new ServerConnection(s,this);
                num++;
                System.out.println("Clients Connected: "+num);
                runThread(sc);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void runThread(Thread sc){
        sc.start();
    }
    
}
