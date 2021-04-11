import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
class Core {
  
  public static Vector<String> userlist = new Vector<String>();
  public static Scanner input = new Scanner(System.in);
  public static String username = "";
  public static String password = "";
  public static String[] usertype = {"uninitialized","caretaker","admin"};
  public static int usertypeidx = 0; //store usertype index, -1 means user not found


  public static void cls(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }


  //write to users.txt 
  public static void UpdateUserList(){
    try {
      FileWriter myWriter = new FileWriter("users.txt");
      for(int i = 0;i<userlist.size();i++){
          myWriter.write(userlist.get(i)+" ");
          if((i+1)%3==0){
            myWriter.write("\n");
          }
       
      }
      
      myWriter.close();
      System.out.println("\nSuccessfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  
  }

  ///LOAD ALL USER INFO
  public static void UserInit(){
    try{
    FileInputStream fstream = new FileInputStream("users.txt");
    DataInputStream in = new DataInputStream(fstream);
    BufferedReader br = new BufferedReader(new InputStreamReader(in));
    String strLine;
      while ((strLine = br.readLine()) != null)   {
      String[] tokens = strLine.split(" ");

      for(int i = 0;i<tokens.length;i++){
        userlist.add(tokens[i]);
      }
    }
  }
    catch (Exception e){
     System.err.println("Error: " + e.getMessage());
   }

// USER INITIALIZATION
  
  }
  public static int AuthVerify(String username,String password){
  
    int found = -1; 
  
    if (userlist.contains(username)){
      int indexat = userlist.indexOf(username);
      if(userlist.get(indexat+1).equals(password)){
        if(userlist.get(indexat+2).equals("a")){
          found = 2;
        }
        else{
        found = 1;
        }
      }
    }
    return found;
  }
  //VERIFICATION

  public static void UserModule(){
    cls();
    int choice = 0;

    while(choice !=-2){
    System.out.println("Welcome back, "+username+"!"+usertype[usertypeidx]);
    System.out.println("1. View today's task\n 2. View task record\n 3. Logout");
    choice = input.nextInt();
    if(choice == 3)return;
    }
  }

//ADMIN MODULE
  public static void AdminModule(){
    
    int choice = 0; //default
    
    while(choice !=-1){
      cls();
      System.out.println("Welcome back, "+username+"!"+usertype[usertypeidx]);
      System.out.println("----ADMIN MODULE----");
      System.out.println("1. Add new user \n 2. View all users \n 3. Edit assigned tasks \n 4. Logout");
      choice = input.nextInt();
      if(choice == 1){
        System.out.println("Add new username");
        String newname = input.next();
        //check if name is available
        for(int i = 0;i<userlist.size();i++){
          if(userlist.get(i).equals(newname)){
            System.out.println("User is already registered. ");
            return;
          }
        }
        System.out.println("\nEnter password for user: "+newname);
        String newpass = input.next();

        userlist.add(newname);
        userlist.add(newpass); //update the userlist vector
        userlist.add("u");
        UpdateUserList();
        System.out.println("user has been added. ");

      }
      if(choice == 2){
        for(int i = 0;i<userlist.size();i+=3){
          System.out.println(userlist.get(i));
          
        }
      }

      if(choice ==4){
        return;
      }





    }
  }
  
  //USER LOGIN & LOGOUT
  public static int UserLogin(){
    username = "";
    usertypeidx = -1;
    password = "";
    //we reset everything in case logging out

    System.out.println("Enter username ");
    username = input.next();
    System.out.println("Enter Password");
    password = input.next();
    usertypeidx = AuthVerify(username,password);
    System.out.println(usertypeidx);
    if(usertypeidx == -1){
      System.out.println("Wrong creds");
      return -1;
    }
    return 1;
  }
  
  
   public static void main(String[] args) {
    UserInit(); //load the users info
    int i = 0;
    while(i!=-1){

      i = UserLogin();
      cls();
      
      if(usertypeidx==2)AdminModule();
      else{
        UserModule();
      }


    }
    

  }
}