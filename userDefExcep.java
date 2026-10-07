public class  ChildEmployeeNotAllowedException extends RuntimeException 
{
       private String message;
      public ChildEmployeeNotFoundException(String message)
       {   this.message=message;
       } 
      public String getUserExeMessage()
      {   return message;
      }
}
