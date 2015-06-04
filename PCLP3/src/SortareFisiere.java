import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Scanner ;
public class SortareFisiere 
{
  public static void main(String[] args) 
  { Scanner tastatura=new Scanner(System.in); 
// preluarea numelui directorului intr-un sir de caractere
    System.out.println("Furnizati nume director:") ;
    String numedir=tastatura.nextLine() ;
// transmiterea sirului de caractere constructorului clasei File
    File dir = new File(numedir) ;
// verificarea daca exista acest director
    if (dir.isDirectory())
       {System.out.println("   Se sorteaza fisierele directorului "+dir.getPath()+" astfel:"); 
// stocarea continutului directorului intr-un tablou de caractere
        String s[] = dir.list();
// parcurgerea elementelor matricii  
    	for (int i = 0; i < s.length; i++)
// transmiterea fiecarui element constructorului clasei File
	      { File fis1 = new File(dir.getPath() + "/" + s[i]);
// stabilirea daca elementul matricii este fisier
	        if(fis1.isFile())
// stocarea primului caracter al elementului matricei
// (numelui fisierului) si transformarea in majuscule 
	          { char c0 = s[i].charAt(0);
	  	        c0 = Character.toUpperCase(c0);
// transmiterea sirului de caractere concatenat constructorului clasei File 
	  	        File sdir = new File(numedir + "\\"+c0) ; 
// daca primul caracter nu este numeric si nu este literal 
// se modifica sdir	  	     
                if (c0 < '0' || c0 > 'Z')
 	    	      { sdir = new File(numedir + "\\_MISC_") ; }
// transmiterea caii noului fisier constructorului FILE	
                File fis2 = new File(sdir.getPath() + "/" + s[i]) ;
// verificarea daca nu exista subdirectorul 
 		        if (!sdir.exists())
// crearea subdirectorului cu metoda predefinita mkdir
	 	        {
    	        try
    	             {if (!sdir.mkdir())
    	             System.out.println(" EROARE : subdirectorul "+sdir.getPath()+ " nu s-a creat, "+dir.getPath()+" este securizat !");
    	             }
		        catch(SecurityException se){System.out.println(se);} 	  
	 	        }
 		       if (sdir.exists())
// copierea fisierului, prin metoda creata cpyfis
		       {System.out.println("se copiaza fisierul: " + fis1.getName()+" in subdirectorul "+sdir.getPath()); 
		        cpyfis (fis1 , fis2) ;
		       }
		   }
		}
    }
    	else
    		{System.out.println("Directorul "+dir.getPath() + " nu exista !") ; }
  }
   
  static void cpyfis (File f1  , File f2 )
//copierea fisierului, introducand un buffer de 1024 octeti			      
  {        
  try {		
    InputStream  intr = new FileInputStream(f1);
    OutputStream iesi = new FileOutputStream(f2);
    byte[] buff = new byte[1024];
    int nro ;
//citirea fisierului in buffer
    while((nro = intr.read(buff)) > 0)
   	{ iesi.write(buff, 0, nro); }
    intr.close();
//golirea bufferului, datele scriindu-se in fisier
    iesi.flush();
    iesi.close();
    } 
    catch (IOException ioe) {System.out.println(" EROARE: fisierul "+f1.getName()+" este securizat, nu s-a copiat ! ") ; } 
      }
 }