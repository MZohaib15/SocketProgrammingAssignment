/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap_assign_4;

import java.math.BigInteger;
import java.util.Arrays;

/**
 *
 * @author mzohaib
 */
public class EncryptionAlgo {
    
    public static void main(String[] args){
        //String str=" !@#$%^&*()_+1234567890-=qwertyuiopasdfghjklzxcvbnm;[]\'/.,{}|:?><QWERTYUIOPASDFGHJKLZXCVBNM";
        String str="I am Zohaib12345";
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
        
        String str2=null;
        System.out.println("Str2: "+str2);
        
    }
}
