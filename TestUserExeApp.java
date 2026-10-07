class ChildEmployeeNotAllowedException extends RuntimeException
{     private String message;
      ChildEmployeeNotAllowedException(String message)
	  {    super(message);
	      this.message=message;
	  }
	  String getUserExeMessage()
	  {   return message;
	  }
}
class Employee
{
   private int id;
   private String name;
   private int age;
   private int sal;
   
   public void setId(int id)
   { this.id=id;
   }
   public int getId()
   {  return id;
   }
   public void setName(String name)
   { this.name=name;
   }
   public String getName()
   { return name;
   }
   public void setAge(int age)
   { this.age=age;
   }
   public int getAge()
   { return age;
   }
   public void setSal(int sal)
   {  this.sal=sal;
   }
   public int getSal()
   { return sal;
   }
}
class DeployEmployee 
{
   public void  addNewEmployee(Employee employee)
   {
        if(employee.getAge()>18)
		{  System.out.println("Employee added successully with salary  "+employee.getSal());
		}
		else{
		  ChildEmployeeNotAllowedException ex=new ChildEmployeeNotAllowedException("Employee age should greater than 18");
		  throw ex;
		}
   }
}
public  class TestUserExeApp 
{  public static void main(String x[])
   {
     try{
       DeployEmployee  demp =new DeployEmployee();     
		   Employee emp = new Employee();
		   emp.setId(1);
		   emp.setName("ABC");
		   emp.setAge(16);
		   emp.setSal(100000);
	       demp.addNewEmployee(emp);
		}
		catch(ChildEmployeeNotAllowedException ex)
		{  System.out.println("Error is  "+ex.getUserExeMessage());
		}
   }
}
